package cat.itacademy.s05.blackjack.domain.service;

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


//    public Game playerHits(Game game) {
//        if (game.getStatus() != GameStatus.IN_PROGRESS) {
//            return game;
//        }
//        Card newCard = game.getDeck().drawCard();
//        game.getPlayerHand().addCard(newCard);
//
//        if (game.getPlayerHand().isBust()) {
//            game.setStatus(GameStatus.PLAYER_BUST);
//            return game;
//        }
//        return game;
//    }
//
//
//    public Game playerStands(Game game) {
//        if (game.getStatus() != GameStatus.IN_PROGRESS) {
//            return game;
//        }
//
//        dealerTurn(game);
//        resolveWinner(game);
//
//        return game;
//    }
//
//
//    private void dealerTurn(Game game) {
//        if (game.getStatus() == GameStatus.DEALER_BUST) {
//            return;
//        }
//
//        Hand dealerHand = game.getDealerHand();
//        Deck deck = game.getDeck();
//
//        while (dealerHand.calculateScore() < 17) {
//            dealerHand.addCard(deck.drawCard());
//        }
//
//        if (dealerHand.isBust()) {
//            game.setStatus(GameStatus.DEALER_BUST);
//        }
//    }

    public Game resolveWinner(Game game) {
        int playerScore = game.getPlayerHand().calculateScore();
        int dealerScore = game.getDealerHand().calculateScore();

        if (playerScore > dealerScore) game.finishWithPlayerWin();
        else if (dealerScore > playerScore) game.finishWithDealerWin();
        else game.finishTie();

        return game;
    }

    private GameStatus evaluateInitialStatus(Hand player, Hand dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) return GameStatus.TIE;
        if (player.isBlackjack()) return GameStatus.PLAYER_BLACKJACK;
        if (dealer.isBlackjack()) return GameStatus.DEALER_BLACKJACK;
        return GameStatus.IN_PROGRESS;
    }
}
