package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.UpdatePlayerStatsUseCase;
import cat.itacademy.s05.blackjack.domain.exception.PlayerNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Profile("docker")
public class UpdatePlayerStatsUseCaseImpl implements UpdatePlayerStatsUseCase {

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Void> updateStats(Long playerId, boolean playerWon) {
        return playerRepository.findById(new PlayerId(playerId))
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player -> {

                    player.incrementTotalGames();

                    if (playerWon) player.incrementWins();
                    else player.incrementLosses();

                    return playerRepository.save(player);
                }).then();
    }
}
