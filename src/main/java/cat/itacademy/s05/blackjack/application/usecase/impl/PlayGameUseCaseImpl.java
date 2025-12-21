package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import cat.itacademy.s05.blackjack.domain.strategy.PlayerActionStrategy;
import cat.itacademy.s05.blackjack.domain.strategy.PlayerActionStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayGameUseCaseImpl implements PlayGameUseCase {

    private final GameRepository gameRepository;
    private final PlayerActionStrategyFactory strategyFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Mono<Game> play(GameId gameId, MoveAction action) {
        PlayerActionStrategy strategy = strategyFactory.get(action);

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId.value())))
                .map(strategy::execute)
                .flatMap(gameRepository::save)
                .doOnSuccess(game ->
                        game.pullEvents().forEach(eventPublisher::publishEvent)
                );
    }
}
