package cat.itacademy.s05.blackjack.domain.model.valueobjects;

public record GameId(String value) {

    public GameId {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("GameId cannot be null");
    }
}

