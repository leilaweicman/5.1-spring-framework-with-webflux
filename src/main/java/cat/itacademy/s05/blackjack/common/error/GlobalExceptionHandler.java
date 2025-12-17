package cat.itacademy.s05.blackjack.common.error;

import cat.itacademy.s05.blackjack.game.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.game.exception.InvalidGameActionException;
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
    public ResponseEntity<ErrorResponseDto> handlePlayerNotFound(PlayerNotFoundException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlePlayerAlreadyExists(PlayerAlreadyExistsException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.CONFLICT, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleGameNotFound(GameNotFoundException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidGameActionException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidMove(InvalidGameActionException ex, ServerHttpRequest request) {
        var error = buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(WebExchangeBindException ex, ServerHttpRequest request) {

        String message = ex.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Invalid request");

        var error = buildError(HttpStatus.BAD_REQUEST, message, request);
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
