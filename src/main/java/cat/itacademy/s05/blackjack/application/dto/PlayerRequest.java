package cat.itacademy.s05.blackjack.application.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerRequest(
        @NotBlank(message = "Player name is required")
        String name
) {}
