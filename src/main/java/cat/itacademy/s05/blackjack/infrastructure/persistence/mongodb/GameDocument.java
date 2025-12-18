package cat.itacademy.s05.blackjack.infrastructure.persistence.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDocument {

    @Id
    private String id;

    private Long playerId;

    private List<CardDocument> deck;
    private List<CardDocument> playerHand;
    private List<CardDocument> dealerHand;

    private String status;
}


