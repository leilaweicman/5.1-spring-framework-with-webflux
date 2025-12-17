package cat.itacademy.s05.blackjack.domain.model.valueobjects;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;

import java.util.Comparator;

public class PlayerRanking {
    public static final Comparator<Player> BY_RANK =
            Comparator.comparingInt(Player::getWins).reversed()
                    .thenComparingInt(Player::getTotalGames).reversed();
}

