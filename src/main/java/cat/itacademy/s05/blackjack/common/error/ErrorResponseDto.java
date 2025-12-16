package cat.itacademy.s05.blackjack.common.error;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}
