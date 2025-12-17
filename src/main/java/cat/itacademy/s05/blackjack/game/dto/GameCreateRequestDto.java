package cat.itacademy.s05.blackjack.game.dto;

import jakarta.validation.constraints.NotNull;

public record GameCreateRequestDto(
        @NotNull Long playerId
) {}
