package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.GetPlayerUseCase;
import cat.itacademy.s05.blackjack.domain.exception.PlayerNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Profile("docker")
public class GetPlayerUseCaseImpl implements GetPlayerUseCase {

    private final PlayerRepository repository;

    @Override
    public Mono<Player> get(PlayerId id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(id.value())));
    }
}

