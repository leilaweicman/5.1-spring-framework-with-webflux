package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Card;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Rank;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Suit;

public class CardMother {

    public static Card aceOfSpades() {
        return new Card(Rank.ACE, Suit.SPADES);
    }

    public static Card tenOfHearts() {
        return new Card(Rank.TEN, Suit.HEARTS);
    }

    public static Card randomCard() {
        return new Card(Rank.values()[0], Suit.values()[0]);
    }
}

