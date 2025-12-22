package cat.itacademy.s05.blackjack.infrastructure.web.controller;

import cat.itacademy.s05.blackjack.application.dto.GameResponseMother;
import cat.itacademy.s05.blackjack.application.usecase.CreateGameUseCase;
import cat.itacademy.s05.blackjack.application.usecase.DeleteGameUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetGameDetailsUseCase;
import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.mother.GameMother;
import cat.itacademy.s05.blackjack.infrastructure.web.mapper.GameDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = GameController.class)
@Import(GameController.class)
class GameControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreateGameUseCase createGameUseCase;

    @MockBean
    private GetGameDetailsUseCase getGameDetailsUseCase;

    @MockBean
    private PlayGameUseCase playGameUseCase;

    @MockBean
    private DeleteGameUseCase deleteGameUseCase;

    @MockBean
    private GameDtoMapper gameDtoMapper;

    @Test
    void createGame_shouldReturnCreatedGame() {
        Game game = GameMother.gameInProgress();

        when(createGameUseCase.create(any(PlayerId.class)))
                .thenReturn(Mono.just(game));

        when(gameDtoMapper.toResponse(any(Game.class)))
                .thenReturn(GameResponseMother.basic());

        webTestClient.post()
                .uri("/game/new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                {
                  "playerId": 5
                }
            """)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.playerId").isEqualTo(5)
                .jsonPath("$.status").isEqualTo("IN_PROGRESS");
    }

    @Test
    void getGame_shouldReturnGameDetails() {
        Game game = GameMother.gameInProgress();

        when(getGameDetailsUseCase.get(any(GameId.class)))
                .thenReturn(Mono.just(game));

        when(gameDtoMapper.toResponse(any(Game.class)))
                .thenReturn(GameResponseMother.basic());

        webTestClient.get()
                .uri("/game/{id}", game.getId().value())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.playerScore").isNumber()
                .jsonPath("$.dealerScore").isNumber();
    }

    @Test
    void playMove_hit_shouldReturnUpdatedGame() {
        Game game = GameMother.gameInProgress();

        when(playGameUseCase.play(any(GameId.class), eq(MoveAction.HIT)))
                .thenReturn(Mono.just(game));

        when(gameDtoMapper.toResponse(any(Game.class)))
                .thenReturn(GameResponseMother.basic());

        webTestClient.post()
                .uri("/game/{id}/move", game.getId().value())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                {
                  "action": "HIT"
                }
            """)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").exists();
    }

    @Test
    void deleteGame_shouldReturnNoContent() {
        when(deleteGameUseCase.delete(new GameId("test-1")))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/game/{id}", "test-1")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void getGame_whenNotFound_shouldReturn404() {
        when(getGameDetailsUseCase.get(new GameId("not-found")))
                .thenReturn(Mono.error(new GameNotFoundException("not-found")));

        webTestClient.get()
                .uri("/game/{id}", "not-found")
                .exchange()
                .expectStatus().isNotFound();
    }
}

