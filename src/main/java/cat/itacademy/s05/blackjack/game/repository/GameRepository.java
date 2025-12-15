package cat.itacademy.s05.blackjack.game.repository;

import cat.itacademy.s05.blackjack.domain.game.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
}
