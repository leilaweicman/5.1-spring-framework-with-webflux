package cat.itacademy.s05.blackjack.player.service;

import cat.itacademy.s05.blackjack.domain.player.Player;
import cat.itacademy.s05.blackjack.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> createPlayer(String name) {
        return null;
    }

    @Override
    public Mono<Player> updatePlayerName(Long playerId, String newName) {
        return null;
    }

    @Override
    public Mono<Player> findById(Long playerId) {
        return playerRepository.findById(playerId);
    }

    @Override
    public Flux<Player> getRanking() {
        return null;
    }

    @Override
    public Mono<Player> save(Player player) {
        return playerRepository.save(player);
    }
}
