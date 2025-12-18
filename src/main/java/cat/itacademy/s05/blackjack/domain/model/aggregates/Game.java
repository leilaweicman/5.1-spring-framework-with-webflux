package cat.itacademy.s05.blackjack.domain.model.aggregates;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}