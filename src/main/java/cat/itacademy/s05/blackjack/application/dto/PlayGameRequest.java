package cat.itacademy.s05.blackjack.application.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayGameRequest(
        @NotBlank String action
) {}
