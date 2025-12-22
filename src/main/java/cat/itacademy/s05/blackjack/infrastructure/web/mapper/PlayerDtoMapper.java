package cat.itacademy.s05.blackjack.infrastructure.web.mapper;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.PlayerResponse;
import org.springframework.stereotype.Component;

@Component
public class PlayerDtoMapper {

    public PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId().value(),
                player.getName().value(),
                player.getWins(),
                player.getLosses(),
                player.getTotalGames()
        );
    }
}

