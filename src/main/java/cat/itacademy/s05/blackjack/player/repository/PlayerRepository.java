package cat.itacademy.s05.blackjack.player.repository;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends R2dbcRepository<Player, Long> {

    Mono<Boolean> existsByName(String name);
}

