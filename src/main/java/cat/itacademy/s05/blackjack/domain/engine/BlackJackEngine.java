package cat.itacademy.s05.blackjack.domain.engine;

import cat.itacademy.s05.blackjack.domain.game.Game;

public class BlackJackEngine {

    /**
     * Initializes a new Blackjack game:
     * - Creates a new shuffled deck
     * - Deals two cards to player
     * - Deals two cards to dealer
     * - Determines initial game status (blackjack or in progress)
     */
    public Game startNewGame(Long playerId) {
        throw new UnsupportedOperationException("Not implemented yet");
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
}
