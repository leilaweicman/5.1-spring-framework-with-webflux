package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Card;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Rank;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Suit;

public final class CardMother {

    private CardMother() {
    }

    public static Card tenOfHearts() {
        return new Card(Rank.TEN, Suit.HEARTS);
    }

    public static Card kingOfClubs() {
        return new Card(Rank.KING, Suit.CLUBS);
    }

    public static Card eightOfDiamonds() {
        return new Card(Rank.EIGHT, Suit.DIAMONDS);
    }

    public static Card nineOfDiamonds() {
        return new Card(Rank.NINE, Suit.DIAMONDS);
    }

    public static Card twoOfClubs() {
        return new Card(Rank.TWO, Suit.CLUBS);
    }

    public static Card fiveOfSpades() {
        return new Card(Rank.FIVE, Suit.SPADES);
    }

    public static Card aceOfSpades() {
        return new Card(Rank.ACE, Suit.SPADES);
    }

    public static Card randomCard() {
        return new Card(Rank.values()[0], Suit.values()[0]);
    }
}

