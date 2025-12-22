package cat.itacademy.s05.blackjack.domain.model.valueobjects;

import cat.itacademy.s05.blackjack.domain.exception.InvalidGameActionException;

public enum MoveAction {
    HIT,
    STAND;

    public static MoveAction from(String value) {
        try {
            return MoveAction.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new InvalidGameActionException("Invalid action: " + value);
        }
    }
}