package cat.itacademy.s05.blackjack.domain.game;

import cat.itacademy.s05.blackjack.domain.deck.Deck;
import cat.itacademy.s05.blackjack.domain.hand.Hand;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "games")
public class Game {

    @Id
    private String id;

    private Long playerId;
    private GameStatus status;

    private Deck deck;

    private Hand playerHand;
    private Hand dealerHand;
}

