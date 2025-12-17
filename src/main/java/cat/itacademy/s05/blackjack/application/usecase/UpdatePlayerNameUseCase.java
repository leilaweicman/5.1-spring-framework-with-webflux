package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import reactor.core.publisher.Mono;

public interface UpdatePlayerNameUseCase {
    Mono<Player> update(Long playerId, String newName);
}
