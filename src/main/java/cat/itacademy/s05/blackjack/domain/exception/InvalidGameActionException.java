package cat.itacademy.s05.blackjack.domain.exception;

public class InvalidGameActionException extends RuntimeException {
    public InvalidGameActionException(String message) {
        super(message);
    }
}

