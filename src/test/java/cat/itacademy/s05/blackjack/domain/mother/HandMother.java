package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Hand;

public class HandMother {

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
