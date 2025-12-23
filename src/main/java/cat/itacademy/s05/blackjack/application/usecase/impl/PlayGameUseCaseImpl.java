package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import cat.itacademy.s05.blackjack.domain.strategy.PlayerActionStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Profile("docker")
public class PlayGameUseCaseImpl implements PlayGameUseCase {

    private final GameRepository gameRepository;
    private final PlayerActionStrategyFactory strategyFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Mono<Game> play(GameId gameId, MoveAction action) {

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId.value())))
                .flatMap(game -> {
                    var strategy = strategyFactory.get(action);
                    Game updated = strategy.execute(game);
                    var events = updated.pullEvents();
                    return gameRepository.save(updated)
                            .doOnSuccess(saved -> {
                                events.forEach(eventPublisher::publishEvent);
                            });
                });
    }
}
