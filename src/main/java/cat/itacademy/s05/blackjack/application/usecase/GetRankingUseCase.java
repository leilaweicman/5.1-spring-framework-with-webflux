package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerRanking;
import reactor.core.publisher.Flux;

public interface GetRankingUseCase {
    Flux<Player> getRanking();
}
