package cat.itacademy.s05.blackjack.infrastructure.persistence.mysql;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Profile("docker")
public class PlayerRepositoryAdapter implements PlayerRepository {

    private final SpringDataPlayerRepository repository;
    private final PlayerEntityMapper mapper;

    @Override
    public Mono<Player> save(Player player) {
        return repository.save(mapper.toEntity(player))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Player> findById(PlayerId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Player> findByName(PlayerName name) {
        return repository.findByName(name.value())
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByName(PlayerName name) {
        return repository.existsByName(name.value());
    }

    @Override
    public Flux<Player> findAll() {
        return repository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(PlayerId id) {
        return repository.deleteById(id.value());
    }
}

