package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import reactor.core.publisher.Mono;

public interface PlayGameUseCase {
    Mono<Game> play(GameId gameId, MoveAction action);
}
