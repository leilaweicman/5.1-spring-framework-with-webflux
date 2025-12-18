package cat.itacademy.s05.blackjack.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayGameRequest(
        @NotBlank String action
) {}
