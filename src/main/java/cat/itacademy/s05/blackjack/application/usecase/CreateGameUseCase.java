package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import reactor.core.publisher.Mono;

public interface CreateGameUseCase {
    Mono<Game> create(Long playerId);
}
