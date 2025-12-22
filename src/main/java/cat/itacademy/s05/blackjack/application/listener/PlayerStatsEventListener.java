package cat.itacademy.s05.blackjack.application.listener;

import cat.itacademy.s05.blackjack.application.usecase.UpdatePlayerStatsUseCase;
import cat.itacademy.s05.blackjack.application.events.PlayerLostGameEvent;
import cat.itacademy.s05.blackjack.application.events.PlayerWonGameEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerStatsEventListener {

    private final UpdatePlayerStatsUseCase updateStats;

    @EventListener
    public void handle(PlayerWonGameEvent event) {
        updateStats.updateStats(event.playerId(), true)
                .subscribe();
    }

    @EventListener
    public void handle(PlayerLostGameEvent event) {
        updateStats.updateStats(event.playerId(), false)
                .subscribe();
    }

}

