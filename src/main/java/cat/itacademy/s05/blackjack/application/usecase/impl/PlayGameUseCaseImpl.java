package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.exception.InvalidGameActionException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import cat.itacademy.s05.blackjack.domain.service.GameEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayGameUseCaseImpl implements PlayGameUseCase {

    private final GameRepository gameRepository;
    private final GameEngine engine;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Mono<Game> play(GameId gameId, MoveAction action) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId.value())))
                .flatMap(game -> handleAction(game, action))
                .flatMap(game -> publishEvents((Game) game).thenReturn(game))
                .flatMap(gameRepository::save);
    }

    private Mono<Game> handleAction(Game game, MoveAction action) {
        switch (action) {
            case HIT -> engine.playerHits(game);
            case STAND -> engine.playerStands(game);
            default -> throw new InvalidGameActionException("Unsupported action: " + action);
        }
        return Mono.just(game);
    }

    private Mono<Void> publishEvents(Game game) {
        game.pullEvents().forEach(eventPublisher::publishEvent);
        return Mono.empty();
    }
}
