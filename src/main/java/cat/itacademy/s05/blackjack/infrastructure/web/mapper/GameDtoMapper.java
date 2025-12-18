package cat.itacademy.s05.blackjack.infrastructure.web.mapper;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.GameResponse;
import org.springframework.stereotype.Component;

@Component
public class GameDtoMapper {

    public GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getId().value(),
                game.getPlayerId().value(),
                game.getStatus(),
                game.getPlayerHand().getCards(),
                game.getDealerHand().getCards(),
                game.getPlayerHand().calculateScore(),
                game.getDealerHand().calculateScore()
        );
    }
}

