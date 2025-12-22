package cat.itacademy.s05.blackjack.domain.strategy;

import cat.itacademy.s05.blackjack.application.events.PlayerLostGameEvent;
import cat.itacademy.s05.blackjack.application.events.PlayerWonGameEvent;
import cat.itacademy.s05.blackjack.domain.exception.InvalidGameActionException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameStatus;
import cat.itacademy.s05.blackjack.domain.mother.CardMother;
import cat.itacademy.s05.blackjack.domain.mother.DeckMother;
import cat.itacademy.s05.blackjack.domain.mother.GameMother;
import cat.itacademy.s05.blackjack.domain.mother.HandMother;
import cat.itacademy.s05.blackjack.domain.service.GameEngine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandActionStrategyTest {

    private final StandActionStrategy strategy = new StandActionStrategy(new GameEngine());

    @Test
    void stand_whenGameAlreadyFinished_shouldThrowException() {
        Game game = GameMother.finishedGamePlayerWins();

        assertThrows(
                InvalidGameActionException.class,
                () -> strategy.execute(game)
        );
    }

    @Test
    void stand_whenDealerBusts_shouldSetDealerBustAndEmitPlayerWonEvent() {
        Game game = GameMother.gameInProgress();

        game.setPlayerHand(
                HandMother.withCards(
                        CardMother.tenOfHearts(),
                        CardMother.twoOfClubs()
                )
        );

        game.setDealerHand(
                HandMother.withCards(
                        CardMother.tenOfHearts(),
                        CardMother.fiveOfSpades()
                )
        );

        game.setDeck(DeckMother.deckWithNextCard(CardMother.tenOfHearts()));

        Game result = strategy.execute(game);

        assertEquals(GameStatus.DEALER_BUST, result.getStatus());

        List<Object> events = result.pullEvents();
        assertFalse(events.isEmpty(), "Expected at least one domain event");
        assertInstanceOf(PlayerWonGameEvent.class, events.getFirst(), "Expected PlayerWonGameEvent when dealer busts");
    }

    @Test
    void stand_whenPlayerScoreHigher_shouldSetPlayerWinsAndEmitEvent() {
        Game game = GameMother.gameInProgress();

        game.setPlayerHand(HandMother.score(20));
        game.setDealerHand(HandMother.score(18));

        game.setDeck(DeckMother.emptyDeck());

        Game result = strategy.execute(game);

        assertEquals(GameStatus.PLAYER_WINS, result.getStatus());

        List<Object> events = result.pullEvents();
        assertFalse(events.isEmpty(), "Expected event for player win");
        assertInstanceOf(PlayerWonGameEvent.class, events.getFirst());
    }

    @Test
    void stand_whenDealerScoreHigher_shouldSetDealerWinsAndEmitEvent() {
        Game game = GameMother.gameInProgress();

        game.setPlayerHand(HandMother.score(18));
        game.setDealerHand(HandMother.score(20));
        game.setDeck(DeckMother.emptyDeck());

        Game result = strategy.execute(game);

        assertEquals(GameStatus.DEALER_WINS, result.getStatus());

        List<Object> events = result.pullEvents();
        assertFalse(events.isEmpty(), "Expected event for player loss");
        assertInstanceOf(PlayerLostGameEvent.class, events.getFirst());
    }

    @Test
    void stand_whenSameScore_shouldSetTieWithoutEvents() {
        Game game = GameMother.gameInProgress();

        game.setPlayerHand(HandMother.score(18));
        game.setDealerHand(HandMother.score(18));
        game.setDeck(DeckMother.emptyDeck());

        Game result = strategy.execute(game);

        assertEquals(GameStatus.TIE, result.getStatus());

        List<Object> events = result.pullEvents();
        assertTrue(events.isEmpty(), "Tie should not emit win/loss events");
    }
}
