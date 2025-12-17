package cat.itacademy.s05.blackjack.player.service;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerRanking;
import cat.itacademy.s05.blackjack.domain.exception.PlayerAlreadyExistsException;
import cat.itacademy.s05.blackjack.domain.exception.PlayerNotFoundException;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> createPlayer(String name) {
        return playerRepository.existsByName(name)
                .flatMap(exists -> {
                    if (exists) return Mono.error(new PlayerAlreadyExistsException(name));

                    Player newPlayer = Player.builder()
                            .name(name)
                            .wins(0)
                            .losses(0)
                            .totalGames(0)
                            .build();

                    return playerRepository.save(newPlayer);
                });
    }

    @Override
    public Mono<Player> updatePlayerName(Long playerId, String name) {
        return playerRepository.findById(playerId)
                .switchIfEmpty((Mono.error(new PlayerNotFoundException(playerId))))
                .flatMap(player -> {
                    player.setName(name);
                    return playerRepository.save(player);
                });
    }

    @Override
    public Mono<Player> findById(Long playerId) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)));
    }

    @Override
    public Flux<Player> getRanking() {
        return playerRepository.findAll()
                .sort(PlayerRanking.BY_RANK);
    }
}
