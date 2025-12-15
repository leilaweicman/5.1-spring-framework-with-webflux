package cat.itacademy.s05.blackjack.player.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerRequestDto(
        @NotBlank(message = "Player name is required")
        String name
) {}
