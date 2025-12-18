package cat.itacademy.s05.blackjack.infrastructure.persistence.mysql;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SpringDataPlayerRepository extends ReactiveCrudRepository<PlayerEntity, Long> {

    Mono<PlayerEntity> findByName(String name);

    Mono<Boolean> existsByName(String name);
}

