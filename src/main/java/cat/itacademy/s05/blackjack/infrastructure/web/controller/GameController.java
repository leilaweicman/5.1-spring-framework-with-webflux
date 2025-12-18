package cat.itacademy.s05.blackjack.infrastructure.web.controller;

import cat.itacademy.s05.blackjack.application.usecase.CreateGameUseCase;
import cat.itacademy.s05.blackjack.application.usecase.DeleteGameUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetGameDetailsUseCase;
import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.CreateGameRequest;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.GameResponse;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.MoveRequest;
import cat.itacademy.s05.blackjack.infrastructure.web.mapper.GameDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final GetGameDetailsUseCase getGameUseCase;
    private final PlayGameUseCase playGameUseCase;
    private final DeleteGameUseCase deleteGameUseCase;

    private final GameDtoMapper mapper;


    @PostMapping
    public Mono<GameResponse> create(@RequestBody CreateGameRequest request) {
        return createGameUseCase.create(new PlayerId(request.playerId()))
                .map(mapper::toResponse);
    }


    @GetMapping("/{id}")
    public Mono<GameResponse> get(@PathVariable String id) {
        return getGameUseCase.get(new GameId(id))
                .map(mapper::toResponse);
    }


    @PostMapping("/{id}/move")
    public Mono<GameResponse> move(
            @PathVariable String id,
            @RequestBody MoveRequest request) {

        return playGameUseCase.play(
                        new GameId(id),
                        MoveAction.valueOf(request.action().toUpperCase())
                )
                .map(mapper::toResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return deleteGameUseCase.delete(new GameId(id));
    }
}


