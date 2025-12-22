package cat.itacademy.s05.blackjack.domain.strategy;

import cat.itacademy.s05.blackjack.domain.exception.InvalidGameActionException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.service.GameEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StandActionStrategy implements PlayerActionStrategy {

    private final GameEngine engine;

    @Override
    public Game execute(Game game) {
        if (!game.isInProgress()) throw new InvalidGameActionException("Cannot STAND: game is already finished");

        game.playDealerTurn();

        if (game.isDealerBust()) {
            game.finishWithDealerBust();
            return game;
        }

        game.resolveWinner();

        return game;
    }
}

