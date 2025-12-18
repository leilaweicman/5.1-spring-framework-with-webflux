package cat.itacademy.s05.blackjack.infrastructure.persistence.mongodb;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameEntityMapper {

    public GameDocument toDocument(Game game) {

        return GameDocument.builder()
                .id(game.getId() != null ? game.getId().value() : null)
                .playerId(game.getPlayerId().value())
                .deck(toCardDocuments(game.getDeck()))
                .playerHand(toCardDocuments(game.getPlayerHand()))
                .dealerHand(toCardDocuments(game.getDealerHand()))
                .status(game.getStatus().name())
                .build();
    }

    public Game toDomain(GameDocument doc) {

        Deck deck = new Deck();
        deck.getCards().clear();
        deck.getCards().addAll(toCards(doc.getDeck()));

        Hand playerHand = new Hand();
        toCards(doc.getPlayerHand()).forEach(playerHand::addCard);

        Hand dealerHand = new Hand();
        toCards(doc.getDealerHand()).forEach(dealerHand::addCard);

        return Game.builder()
                .id(new GameId(doc.getId()))
                .playerId(new PlayerId(doc.getPlayerId()))
                .deck(deck)
                .playerHand(playerHand)
                .dealerHand(dealerHand)
                .status(GameStatus.valueOf(doc.getStatus()))
                .build();
    }

    private List<CardDocument> toCardDocuments(Deck deck) {
        return deck.getCards().stream()
                .map(c -> new CardDocument(c.rank().name(), c.suit().name()))
                .toList();
    }

    private List<CardDocument> toCardDocuments(Hand hand) {
        return hand.getCards().stream()
                .map(c -> new CardDocument(c.rank().name(), c.suit().name()))
                .toList();
    }

    private List<Card> toCards(List<CardDocument> docs) {
        return docs.stream()
                .map(d -> new Card(
                        Rank.valueOf(d.getRank()),
                        Suit.valueOf(d.getSuit())
                ))
                .toList();
    }

}







