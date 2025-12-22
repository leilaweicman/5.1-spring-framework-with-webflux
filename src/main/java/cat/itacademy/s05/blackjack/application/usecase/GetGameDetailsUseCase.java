package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import reactor.core.publisher.Mono;

public interface GetGameDetailsUseCase {
    Mono<Game> get(GameId gameId);
}
