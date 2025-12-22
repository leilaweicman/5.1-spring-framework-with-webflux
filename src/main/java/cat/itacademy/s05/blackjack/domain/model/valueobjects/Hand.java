package cat.itacademy.s05.blackjack.domain.model.valueobjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int total = 0;
        int aces = 0;

        for (Card c : cards) {
            total += c.rank().getValue();
            if (c.rank() == Rank.ACE) aces++;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public boolean isBust() { return calculateScore() > 21; }

    public boolean isBlackjack() { return cards.size() == 2 && calculateScore() == 21; }
}

