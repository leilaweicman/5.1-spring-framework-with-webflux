package cat.itacademy.s05.blackjack.domain.model.valueobjects;

public record PlayerName(String value) {

    public PlayerName {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("PlayerName cannot be empty");

        if (value.length() > 30)
            throw new IllegalArgumentException("PlayerName too long");
    }
}

