package cat.itacademy.s05.blackjack.application.dto;

import jakarta.validation.constraints.NotNull;

public record CreateGameRequest(
        @NotNull Long playerId
) {}
