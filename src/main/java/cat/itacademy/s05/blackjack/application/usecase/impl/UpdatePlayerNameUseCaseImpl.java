package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.UpdatePlayerNameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.PlayerNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Profile("docker")
public class UpdatePlayerNameUseCaseImpl implements UpdatePlayerNameUseCase {

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> update(PlayerId id, PlayerName newName) {

        return playerRepository.findById(id)
                .switchIfEmpty((Mono.error(new PlayerNotFoundException(id.value()))))
                .flatMap(player -> {
                    player.setName(newName);
                    return playerRepository.save(player);
                });
    }
}
