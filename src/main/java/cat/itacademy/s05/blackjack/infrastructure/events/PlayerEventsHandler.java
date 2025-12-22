package cat.itacademy.s05.blackjack.infrastructure.events;

import cat.itacademy.s05.blackjack.application.events.PlayerLostGameEvent;
import cat.itacademy.s05.blackjack.application.events.PlayerWonGameEvent;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.repository.PlayerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PlayerEventsHandler {

    private final PlayerRepository playerRepository;

    public PlayerEventsHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @EventListener
    public Mono<Void> onPlayerWin(PlayerWonGameEvent event) {
        return playerRepository.findById(new PlayerId(event.playerId()))
                .flatMap(player -> {
                    player.incrementWins();
                    player.incrementTotalGames();
                    return playerRepository.save(player);
                })
                .then();
    }

    @EventListener
    public Mono<Void> onPlayerLoss(PlayerLostGameEvent event) {
        return playerRepository.findById(new PlayerId(event.playerId()))
                .flatMap(player -> {
                    player.incrementLosses();
                    player.incrementTotalGames();
                    return playerRepository.save(player);
                })
                .then();
    }
}
