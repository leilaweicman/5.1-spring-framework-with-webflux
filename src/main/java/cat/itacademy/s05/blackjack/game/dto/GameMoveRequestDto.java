package cat.itacademy.s05.blackjack.game.dto;

import jakarta.validation.constraints.NotBlank;

public record GameMoveRequestDto(
        @NotBlank String action
) {}
