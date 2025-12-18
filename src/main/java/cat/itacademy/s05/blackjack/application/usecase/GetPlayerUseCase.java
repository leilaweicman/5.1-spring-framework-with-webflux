package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import reactor.core.publisher.Mono;

public interface GetPlayerUseCase {
    Mono<Player> get(PlayerId id);
}

