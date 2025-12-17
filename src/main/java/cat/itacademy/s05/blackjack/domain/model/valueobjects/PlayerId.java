package cat.itacademy.s05.blackjack.domain.model.valueobjects;

public record PlayerId(Long value) {

    public PlayerId {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("PlayerId must be positive");
    }
}

