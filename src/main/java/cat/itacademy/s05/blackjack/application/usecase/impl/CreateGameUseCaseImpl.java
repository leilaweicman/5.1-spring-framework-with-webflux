package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.CreateGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.PlayerNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import cat.itacademy.s05.blackjack.domain.service.GameEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateGameUseCaseImpl implements CreateGameUseCase {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final GameEngine engine;

    @Override
    public Mono<Game> create(PlayerId playerId) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId.value())))
                .flatMap(p -> {
                    Game game = engine.startNewGame(playerId.value());
                    return gameRepository.save(game);
                });
    }
}

