package cat.itacademy.s05.blackjack.domain.mother;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;

import java.util.ArrayList;
import java.util.List;

public class DeckMother {

    public static Deck randomDeck() {
        return new Deck();
    }

    public static Deck deckWithFixedOrder(List<Card> cards) {
        return new Deck(new ArrayList<>(cards));
    }

    public static Deck deckWithNextCard(Card next) {
        List<Card> cards = new ArrayList<>();
        cards.add(next);
        return new Deck(cards);
    }

    public static Deck emptyDeck() {
        return new Deck(new ArrayList<>());
    }
}

