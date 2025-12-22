package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.GetRankingUseCase;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerRanking;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetRankingUseCaseImpl implements GetRankingUseCase {

    private final PlayerRepository playerRepository;

    @Override
    public Flux<Player> getRanking() {
        return playerRepository.findAll()
                .sort(PlayerRanking.BY_RANK);
    }
}
