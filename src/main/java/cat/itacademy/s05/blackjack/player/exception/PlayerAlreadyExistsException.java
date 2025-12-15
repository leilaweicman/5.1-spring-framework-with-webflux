package cat.itacademy.s05.blackjack.player.exception;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String name) {
        super("Player already exists with name: " + name);
    }
}
