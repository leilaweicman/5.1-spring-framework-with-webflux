package cat.itacademy.s05.blackjack.domain.model.aggregates;

import cat.itacademy.s05.blackjack.domain.events.PlayerLostGameEvent;
import cat.itacademy.s05.blackjack.domain.events.PlayerWonGameEvent;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private GameId id;
    private PlayerId playerId;
    private GameStatus status;
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;

    private final List<Object> domainEvents = new ArrayList<>();

    public List<Object> pullEvents() {
        var copy = List.copyOf(domainEvents);
        domainEvents.clear();
        return copy;
    }

    private void registerEvent(Object event) {
        domainEvents.add(event);
    }

    public boolean isInProgress() {
        return this.status == GameStatus.IN_PROGRESS;
    }

    public void hitPlayer() {
        playerHand.addCard(deck.drawCard());
    }

    public boolean isPlayerBust() {
        return playerHand.isBust();
    }

    public boolean isDealerBust() {
        return dealerHand.isBust();
    }

    public void playDealerTurn() {
        while (dealerHand.calculateScore() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
    }

    public void finishWithPlayerWin() {
        this.status = GameStatus.PLAYER_WINS;
        registerEvent(new PlayerWonGameEvent(playerId.value(), id.value()));
    }

    public void finishWithDealerWin() {
        this.status = GameStatus.DEALER_WINS;
        registerEvent(new PlayerLostGameEvent(playerId.value(), id.value()));
    }

    public void finishWithPlayerBust() {
        this.status = GameStatus.PLAYER_BUST;
        registerEvent(new PlayerLostGameEvent(playerId.value(), id.value()));
    }

    public void finishWithDealerBust() {
        this.status = GameStatus.DEALER_BUST;
        registerEvent(new PlayerWonGameEvent(playerId.value(), id.value()));
    }

    public void finishTie() {
        this.status = GameStatus.TIE;
    }
}