package cat.itacademy.s05.blackjack.domain.engine;

import cat.itacademy.s05.blackjack.domain.deck.Deck;
import cat.itacademy.s05.blackjack.domain.game.Game;
import cat.itacademy.s05.blackjack.domain.game.GameStatus;
import cat.itacademy.s05.blackjack.domain.hand.Hand;

public class BlackJackEngine {

    /**
     * Initializes a new Blackjack game:
     * - Creates a new shuffled deck
     * - Deals two cards to player
     * - Deals two cards to dealer
     * - Determines initial game status (blackjack or in progress)
     */
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
                .playerId(playerId)
                .deck(deck)
                .playerHand(playerHand)
                .dealerHand(dealerHand)
                .status(status)
                .build();
    }

    /**
     * Player requests a new card. If busts → loses immediately.
     */
    public Game playerHits(Game game) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Player stands. Control passes to dealer.
     */
    public Game playerStands(Game game) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Dealer draws cards until reaching at least 17 points.
     */
    private void dealerTurn(Game game) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Determines the final winner after dealer finishes.
     */
    private void resolveWinner(Game game) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private GameStatus evaluateInitialStatus(Hand player, Hand dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) return GameStatus.TIE;
        if (player.isBlackjack()) return GameStatus.PLAYER_BLACKJACK;
        if (dealer.isBlackjack()) return GameStatus.DEALER_BLACKJACK;
        return GameStatus.IN_PROGRESS;
    }
}
