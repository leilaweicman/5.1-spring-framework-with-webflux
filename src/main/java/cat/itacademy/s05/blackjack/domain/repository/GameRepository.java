package cat.itacademy.s05.blackjack.domain.repository;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameRepository {

    Mono<Game> save(Game game);

    Mono<Game> findById(GameId id);

    Flux<Game> findByPlayerId(PlayerId playerId);

    Mono<Void> deleteById(GameId id);
}
