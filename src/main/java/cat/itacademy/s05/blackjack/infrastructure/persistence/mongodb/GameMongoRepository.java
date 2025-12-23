package cat.itacademy.s05.blackjack.infrastructure.persistence.mongodb;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@Profile("docker")
public interface GameMongoRepository extends ReactiveMongoRepository<GameDocument, String> {

    Flux<GameDocument> findByPlayerId(Long playerId);
}
