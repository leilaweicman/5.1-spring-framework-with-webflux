package cat.itacademy.s05.blackjack.infrastructure.persistence.mongodb;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Profile("docker")
public class GameRepositoryAdapter implements GameRepository {

    private final GameMongoRepository repository;
    private final GameEntityMapper mapper;

    @Override
    public Mono<Game> save(Game game) {
        return repository.save(mapper.toDocument(game))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Game> findById(GameId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Game> findByPlayerId(PlayerId playerId) {
        return repository.findByPlayerId(playerId.value())
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(GameId id) {
        return repository.deleteById(id.value());
    }
}


