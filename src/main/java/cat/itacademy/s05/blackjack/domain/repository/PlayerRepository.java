package cat.itacademy.s05.blackjack.domain.repository;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerRepository {

    Mono<Player> save(Player player);

    Mono<Player> findById(PlayerId id);

    Mono<Player> findByName(PlayerName name);

    Mono<Boolean> existsByName(PlayerName name);

    Flux<Player> findAll();

    Mono<Void> deleteById(PlayerId id);
}

