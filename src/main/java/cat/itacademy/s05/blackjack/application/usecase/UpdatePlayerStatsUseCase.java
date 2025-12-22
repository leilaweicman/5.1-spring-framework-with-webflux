package cat.itacademy.s05.blackjack.application.usecase;

import reactor.core.publisher.Mono;

public interface UpdatePlayerStatsUseCase {

    Mono<Void> updateStats(Long playerId, boolean playerWon);
}
