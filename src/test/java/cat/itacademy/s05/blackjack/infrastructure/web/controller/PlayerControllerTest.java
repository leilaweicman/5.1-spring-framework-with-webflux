package cat.itacademy.s05.blackjack.infrastructure.web.controller;

import cat.itacademy.s05.blackjack.application.dto.PlayerResponseMother;
import cat.itacademy.s05.blackjack.application.usecase.CreatePlayerUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetPlayerUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetRankingUseCase;
import cat.itacademy.s05.blackjack.application.usecase.UpdatePlayerNameUseCase;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import cat.itacademy.s05.blackjack.domain.mother.PlayerMother;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.PlayerResponse;
import cat.itacademy.s05.blackjack.infrastructure.web.mapper.PlayerDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreatePlayerUseCase createPlayerUseCase;

    @MockBean
    private GetPlayerUseCase getPlayerUseCase;

    @MockBean
    private GetRankingUseCase getRankingUseCase;

    @MockBean
    private UpdatePlayerNameUseCase updatePlayerNameUseCase;

    @MockBean
    private PlayerDtoMapper mapper;

    @Test
    void createPlayer_shouldReturnCreatedPlayer() {
        Player player = PlayerMother.validPlayer("Alice");
        PlayerResponse response = PlayerResponseMother.from(player);

        when(createPlayerUseCase.create(any(PlayerName.class)))
                .thenReturn(Mono.just(player));

        when(mapper.toResponse(any(Player.class)))
                .thenReturn(response);

        webTestClient.post()
                .uri("/player")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                    {
                      "name": "Alice"
                    }
                """)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(player.getId().value())
                .jsonPath("$.name").isEqualTo("Alice");
    }

    @Test
    void getPlayer_shouldReturnPlayer() {
        Player player = PlayerMother.validPlayer("Alice");
        PlayerResponse response = PlayerResponseMother.from(player);

        when(getPlayerUseCase.get(new PlayerId(player.getId().value())))
                .thenReturn(Mono.just(player));

        when(mapper.toResponse(player))
                .thenReturn(response);

        webTestClient.get()
                .uri("/player/{id}", player.getId().value())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(player.getId().value())
                .jsonPath("$.name").exists();
    }

    @Test
    void getRanking_shouldReturnOrderedPlayers() {
        Player p1 = PlayerMother.playerWithStatistics(5, 0, 5);
        Player p2 = PlayerMother.playerWithStatistics(2, 0, 2);

        when(getRankingUseCase.getRanking())
                .thenReturn(Flux.just(p1, p2));

        when(mapper.toResponse(any(Player.class)))
                .thenAnswer(inv -> PlayerResponseMother.from(inv.getArgument(0)));

        webTestClient.get()
                .uri("/player/ranking")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].wins").isEqualTo(5)
                .jsonPath("$[1].wins").isEqualTo(2);
    }
}

