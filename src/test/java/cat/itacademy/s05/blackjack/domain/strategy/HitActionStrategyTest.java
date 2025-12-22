package cat.itacademy.s05.blackjack.domain.strategy;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameStatus;
import cat.itacademy.s05.blackjack.domain.mother.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HitActionStrategyTest {

    private final HitActionStrategy strategy = new HitActionStrategy();

    @Test
    void hitPlayer_shouldDrawCard() {
        Game game = GameMother.gameInProgress();
        game.setPlayerHand(
                HandMother.withCards(
                        CardMother.twoOfClubs(),
                        CardMother.fiveOfSpades()
                )
        );

        int initialCards = game.getPlayerHand().getCards().size();

        game.setDeck(DeckMother.deckWithNextCard(CardMother.tenOfHearts()));

        Game updated = strategy.execute(game);

        assertEquals(initialCards + 1, updated.getPlayerHand().getCards().size());
        assertEquals(GameStatus.IN_PROGRESS, updated.getStatus());
    }

    @Test
    void hitPlayerBust_shouldTriggerLostEvent() {
        Game game = GameMother.gameInProgress();
        game.setPlayerHand(HandMother.score(20));
        game.setDeck(DeckMother.deckWithNextCard(CardMother.tenOfHearts()));

        Game updated = strategy.execute(game);

        assertEquals(GameStatus.PLAYER_BUST, updated.getStatus());
        assertFalse(updated.pullEvents().isEmpty());
    }
}

