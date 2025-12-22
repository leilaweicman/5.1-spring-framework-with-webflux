package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import reactor.core.publisher.Mono;

public interface CreateGameUseCase {
    Mono<Game> create(PlayerId playerId);
}
