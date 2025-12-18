package cat.itacademy.s05.blackjack.infrastructure.persistence.mongodb;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDocument {
    private String rank;
    private String suit;
}
