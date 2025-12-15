package cat.itacademy.s05.blackjack.domain.deck;

import cat.itacademy.s05.blackjack.domain.card.Card;
import cat.itacademy.s05.blackjack.domain.card.Rank;
import cat.itacademy.s05.blackjack.domain.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = generateDeck();
        shuffle();
    }

    private List<Card> generateDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public List<Card> getCardsSnapshot() {
        return new ArrayList<>(cards);
    }
}

