package cat.itacademy.s05.blackjack.game.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String id) {
        super("Game with ID " + id + " not found");
    }
}
