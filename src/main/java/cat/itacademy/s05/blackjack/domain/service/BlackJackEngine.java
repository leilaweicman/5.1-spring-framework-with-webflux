package cat.itacademy.s05.blackjack.domain.service;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Card;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Deck;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameStatus;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Hand;
import org.springframework.stereotype.Component;

@Component
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
        if (game.getStatus() != GameStatus.IN_PROGRESS) {
            return game;
        }
        Card newCard = game.getDeck().drawCard();
        game.getPlayerHand().addCard(newCard);

        if (game.getPlayerHand().isBust()) {
            game.setStatus(GameStatus.PLAYER_BUST);
            return game;
        }
        return game;
    }

    /**
     * Player stands. Control passes to dealer.
     */
    public Game playerStands(Game game) {
        if (game.getStatus() != GameStatus.IN_PROGRESS) {
            return game;
        }

        dealerTurn(game);
        resolveWinner(game);

        return game;
    }

    /**
     * Dealer draws cards until reaching at least 17 points.
     */
    private void dealerTurn(Game game) {
        if (game.getStatus() == GameStatus.DEALER_BUST) {
            return;
        }

        Hand dealerHand = game.getDealerHand();
        Deck deck = game.getDeck();

        while (dealerHand.calculateScore() < 17) {
            dealerHand.addCard(deck.drawCard());
        }

        if (dealerHand.isBust()) {
            game.setStatus(GameStatus.DEALER_BUST);
        }
    }

    /**
     * Determines the final winner after dealer finishes.
     */
    private void resolveWinner(Game game) {
        int playerScore = game.getPlayerHand().calculateScore();
        int dealerScore = game.getDealerHand().calculateScore();

        if (playerScore > dealerScore) {
            game.setStatus(GameStatus.PLAYER_WINS);
        } else if (dealerScore > playerScore) {
            game.setStatus(GameStatus.DEALER_WINS);
        } else {
            game.setStatus(GameStatus.TIE);
        }
    }

    private GameStatus evaluateInitialStatus(Hand player, Hand dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) return GameStatus.TIE;
        if (player.isBlackjack()) return GameStatus.PLAYER_BLACKJACK;
        if (dealer.isBlackjack()) return GameStatus.DEALER_BLACKJACK;
        return GameStatus.IN_PROGRESS;
    }
}
