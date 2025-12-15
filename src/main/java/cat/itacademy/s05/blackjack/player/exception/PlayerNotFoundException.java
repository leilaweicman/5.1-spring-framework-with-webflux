package cat.itacademy.s05.blackjack.player.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Player not found with ID: " + id);
    }
}
