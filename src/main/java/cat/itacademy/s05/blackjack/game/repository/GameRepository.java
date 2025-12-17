package cat.itacademy.s05.blackjack.game.repository;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
}
