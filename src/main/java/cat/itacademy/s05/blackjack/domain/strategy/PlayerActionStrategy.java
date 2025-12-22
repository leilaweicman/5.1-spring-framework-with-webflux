package cat.itacademy.s05.blackjack.domain.strategy;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;

public interface PlayerActionStrategy {
    Game execute(Game game);
}
