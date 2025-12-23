package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.CreatePlayerUseCase;
import cat.itacademy.s05.blackjack.domain.exception.PlayerAlreadyExistsException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Profile("docker")
public class CreatePlayerUseCaseImpl implements CreatePlayerUseCase {

    private final PlayerRepository repository;

    @Override
    public Mono<Player> create(PlayerName name) {
        return repository.existsByName(name)
                .flatMap(exists -> {
                    if (exists) return Mono.error(new PlayerAlreadyExistsException(name.value()));
                    Player player = Player.builder()
                            .name(name)
                            .wins(0)
                            .losses(0)
                            .totalGames(0)
                            .build();
                    return repository.save(player);
                });
    }
}

