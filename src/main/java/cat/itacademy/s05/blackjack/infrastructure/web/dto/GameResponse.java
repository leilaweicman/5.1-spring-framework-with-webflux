package cat.itacademy.s05.blackjack.infrastructure.web.dto;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Card;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record GameResponse(
        String id,
        Long playerId,
        GameStatus status,
        List<Card> playerHand,
        List<Card> dealerHand,
        Integer playerScore,
        Integer dealerScore
) {}

