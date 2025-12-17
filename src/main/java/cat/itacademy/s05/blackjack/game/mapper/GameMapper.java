package cat.itacademy.s05.blackjack.game.mapper;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.application.dto.GameResponse;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameResponse toResponse(Game game) {
        return GameResponse.builder()
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

