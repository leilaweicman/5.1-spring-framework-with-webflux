package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Card;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Hand;

public final class HandMother {

    private HandMother() {
    }

    public static Hand withCards(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.addCard(card);
        }
        return hand;
    }

    public static Hand score(int value) {
        return switch (value) {
            case 20 -> withCards(
                    CardMother.tenOfHearts(),
                    CardMother.kingOfClubs()
            );
            case 18 -> withCards(
                    CardMother.tenOfHearts(),
                    CardMother.eightOfDiamonds()
            );
            case 19 -> withCards(
                    CardMother.tenOfHearts(),
                    CardMother.nineOfDiamonds()
            );
            case 22 -> withCards(
                    CardMother.tenOfHearts(),
                    CardMother.kingOfClubs(),
                    CardMother.twoOfClubs()
            );
            case 15 -> withCards(
                    CardMother.tenOfHearts(),
                    CardMother.fiveOfSpades()
            );
            default ->
                    throw new IllegalArgumentException("Unsupported score in HandMother.score: " + value);
        };
    }

    public static Hand blackjackHand() {
        Hand hand = new Hand();
        hand.addCard(CardMother.aceOfSpades());
        hand.addCard(CardMother.tenOfHearts());
        return hand;
    }

    public static Hand bustHand() {
        Hand hand = new Hand();
        hand.addCard(CardMother.tenOfHearts());
        hand.addCard(CardMother.tenOfHearts());
        hand.addCard(CardMother.tenOfHearts());
        return hand;
    }

    public static Hand emptyHand() {
        return new Hand();
    }
}
