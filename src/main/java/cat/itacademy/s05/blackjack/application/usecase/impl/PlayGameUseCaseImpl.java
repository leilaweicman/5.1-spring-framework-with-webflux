package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerAction;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import cat.itacademy.s05.blackjack.domain.service.GameEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayGameUseCaseImpl implements PlayGameUseCase {

    private final GameRepository gameRepository;
    private final GameEngine engine;

    @Override
    public Mono<Game> play(String gameId, PlayerAction action) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)))
                .flatMap(game -> {

                    switch (action) {
                        case HIT -> engine.playerHits(game);
                        case STAND -> engine.playerStands(game);
                    }

                    return gameRepository.save(game);
                });
    }
}
