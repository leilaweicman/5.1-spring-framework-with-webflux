package cat.itacademy.s05.blackjack.player.mapper;

import cat.itacademy.s05.blackjack.domain.player.Player;
import cat.itacademy.s05.blackjack.player.dto.PlayerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerResponseDto toResponse(Player player) {
        return new PlayerResponseDto(
                player.getId(),
                player.getName(),
                player.getWins(),
                player.getLosses(),
                player.getTotalGames()
        );
    }
}
