package cat.itacademy.s05.blackjack.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface GameMongoRepository extends ReactiveMongoRepository<GameDocument, String> {

    Flux<GameDocument> findByPlayerId(Long playerId);
}
