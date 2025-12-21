package cat.itacademy.s05.blackjack.domain.strategy;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HitActionStrategy implements PlayerActionStrategy {

    @Override
    public Game execute(Game game) {
        if (!game.isInProgress()) return game;

        game.hitPlayer();

        if (game.isPlayerBust()) {
            game.finishWithPlayerBust();
        }

        return game;
    }
}
