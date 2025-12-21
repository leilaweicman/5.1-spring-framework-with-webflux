package cat.itacademy.s05.blackjack.domain.service;

import cat.itacademy.s05.blackjack.domain.events.PlayerLostGameEvent;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

    public Game startNewGame(Long playerId) {
        Deck deck = new Deck();

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());

        dealerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        GameStatus status = evaluateInitialStatus(playerHand, dealerHand);

        return Game.builder()
                .playerId(new PlayerId(playerId))
                .deck(deck)
                .playerHand(playerHand)
                .dealerHand(dealerHand)
                .status(status)
                .build();
    }

    private GameStatus evaluateInitialStatus(Hand player, Hand dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) return GameStatus.TIE;
        if (player.isBlackjack()) return GameStatus.PLAYER_BLACKJACK;
        if (dealer.isBlackjack()) return GameStatus.DEALER_BLACKJACK;
        return GameStatus.IN_PROGRESS;
    }
}
