package cat.itacademy.s05.blackjack.player.domain;

import cat.itacademy.s05.blackjack.domain.player.Player;

import java.util.Comparator;

public class PlayerRanking {
    public static final Comparator<Player> BY_RANK =
            Comparator.comparingInt(Player::getWins).reversed()
                    .thenComparingInt(Player::getTotalGames).reversed();
}

