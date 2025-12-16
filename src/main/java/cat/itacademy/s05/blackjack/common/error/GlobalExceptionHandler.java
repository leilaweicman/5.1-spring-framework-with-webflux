package cat.itacademy.s05.blackjack.common.error;

import cat.itacademy.s05.blackjack.player.exception.PlayerAlreadyExistsException;
import cat.itacademy.s05.blackjack.player.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;


import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponseDto buildError(HttpStatus status, String message, ServerHttpRequest request) {
        return new ErrorResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getPath().value()
        );
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(PlayerNotFoundException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleConflict(PlayerAlreadyExistsException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.CONFLICT, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(WebExchangeBindException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.BAD_REQUEST, ex.getFieldErrors().toString(), request);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidInput(ServerWebInputException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.BAD_REQUEST, ex.getReason(), request);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralError(Exception ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
