package cat.itacademy.s05.blackjack.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerRequest(
        @NotBlank(message = "Player name is required")
        String name
) {}
