package cat.itacademy.s05.blackjack.infrastructure.web.exception;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}
