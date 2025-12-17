package cat.itacademy.s05.blackjack.game.mapper;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.game.dto.GameResponseDto;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameResponseDto toResponse(Game game) {
        return GameResponseDto.builder()
                .id(game.getId())
                .playerId(game.getPlayerId())
                .status(game.getStatus())
                .playerHand(game.getPlayerHand().getCards())
                .dealerHand(game.getDealerHand().getCards())
                .playerScore(game.getPlayerHand().calculateScore())
                .dealerScore(game.getDealerHand().calculateScore())
                .build();
    }
}

