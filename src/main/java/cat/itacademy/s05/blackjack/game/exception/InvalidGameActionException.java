package cat.itacademy.s05.blackjack.game.exception;

public class InvalidGameActionException extends RuntimeException {
    public InvalidGameActionException(String message) {
        super(message);
    }
}

