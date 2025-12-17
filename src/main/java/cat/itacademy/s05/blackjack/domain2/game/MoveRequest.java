package cat.itacademy.s05.blackjack.domain2.game;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerAction;

public record MoveRequest(
        PlayerAction action
) {}
