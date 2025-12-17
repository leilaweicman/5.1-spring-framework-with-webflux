package cat.itacademy.s05.blackjack.player.mapper;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.application.dto.PlayerResponse;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId(),
                player.getName(),
                player.getWins(),
                player.getLosses(),
                player.getTotalGames()
        );
    }
}
