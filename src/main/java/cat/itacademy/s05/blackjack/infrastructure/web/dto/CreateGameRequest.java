package cat.itacademy.s05.blackjack.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;

public record CreateGameRequest(
        @NotNull Long playerId
) {}
