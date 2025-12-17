package cat.itacademy.s05.blackjack.domain.model.aggregates;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.Deck;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameStatus;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.Hand;
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

