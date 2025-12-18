package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import reactor.core.publisher.Mono;

public interface CreatePlayerUseCase {
    Mono<Player> create(PlayerName name);
}

