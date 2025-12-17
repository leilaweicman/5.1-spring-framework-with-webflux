package cat.itacademy.s05.blackjack.game.dto;

import cat.itacademy.s05.blackjack.domain.card.Card;
import cat.itacademy.s05.blackjack.domain.game.GameStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record GameResponseDto(
        String id,
        Long playerId,
        GameStatus status,
        List<Card> playerHand,
        List<Card> dealerHand,
        Integer playerScore,
        Integer dealerScore
) {}

