package cat.itacademy.s05.blackjack.domain.model.aggregates;

import cat.itacademy.s05.blackjack.application.events.PlayerLostGameEvent;
import cat.itacademy.s05.blackjack.application.events.PlayerWonGameEvent;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public void finishWithPlayerWin() {
        this.status = GameStatus.PLAYER_WINS;
        registerEvent(new PlayerWonGameEvent(playerId.value(), id.value()));
    }

    public void finishWithDealerWin() {
        this.status = GameStatus.DEALER_WINS;
        registerEvent(new PlayerLostGameEvent(playerId.value(), id.value()));
    }
}