package cat.itacademy.s05.blackjack.player.service;

import cat.itacademy.s05.blackjack.domain.player.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlayerServiceImpl implements PlayerService {
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
        return null;
    }

    @Override
    public Flux<Player> getRanking() {
        return null;
    }

    @Override
    public Mono<Player> save(Player player) {
        return null;
    }
}
