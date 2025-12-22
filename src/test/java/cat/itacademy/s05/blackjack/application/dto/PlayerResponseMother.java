package cat.itacademy.s05.blackjack.application.dto;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.PlayerResponse;

public class PlayerResponseMother {

    public static PlayerResponse from(Player player) {
        return new PlayerResponse(
                player.getId().value(),
                player.getName().value(),
                player.getWins(),
                player.getLosses(),
                player.getTotalGames()
        );
    }
}
