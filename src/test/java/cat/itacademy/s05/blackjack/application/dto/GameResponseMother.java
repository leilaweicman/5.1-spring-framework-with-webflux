package cat.itacademy.s05.blackjack.application.dto;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameStatus;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.GameResponse;

import java.util.List;

public final class GameResponseMother {

    private GameResponseMother() {}

    public static GameResponse basic() {
        return GameResponse.builder()
                .id("test-game-id")
                .playerId(5L)
                .status(GameStatus.IN_PROGRESS)
                .playerHand(List.of())
                .dealerHand(List.of())
                .playerScore(18)
                .dealerScore(16)
                .build();
    }
}

