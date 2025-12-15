package cat.itacademy.s05.blackjack.domain.game;

import cat.itacademy.s05.blackjack.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("games")
public class Game {

    @Id
    private String id;

    private Long playerId;

    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;

    private GameStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
}

