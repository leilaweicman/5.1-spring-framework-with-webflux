package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;

public class GameMother {

    public static Game gameInProgress() {
        return Game.builder()
                .id(new GameId("test-1"))
                .playerId(new PlayerId(5L))
                .status(GameStatus.IN_PROGRESS)
                .deck(DeckMother.randomDeck())
                .playerHand(HandMother.withCards(
                        CardMother.tenOfHearts(),
                        CardMother.eightOfDiamonds()
                ))
                .dealerHand(HandMother.withCards(
                        CardMother.nineOfDiamonds(),
                        CardMother.fiveOfSpades()
                ))
                .build();
    }

    public static Game blackjackGame() {
        return Game.builder()
                .id(new GameId("test-bj"))
                .playerId(new PlayerId(5L))
                .status(GameStatus.IN_PROGRESS)
                .deck(DeckMother.randomDeck())
                .playerHand(HandMother.blackjackHand())
                .dealerHand(HandMother.emptyHand())
                .build();
    }
}

