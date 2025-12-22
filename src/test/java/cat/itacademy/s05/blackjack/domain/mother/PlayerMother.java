package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;

public class PlayerMother {
    public static Player validPlayer() {
        return Player.builder()
                .id(new PlayerId(5L))
                .name(new PlayerName("Stats Player"))
                .wins(0)
                .losses(0)
                .totalGames(0)
                .build();
    }

    public static Player playerWithStatistics(int wins, int losses, int totalGames) {
        return Player.builder()
                .id(new PlayerId(10L))
                .name(new PlayerName("Stats Player"))
                .wins(wins)
                .losses(losses)
                .totalGames(totalGames)
                .build();
    }
}
