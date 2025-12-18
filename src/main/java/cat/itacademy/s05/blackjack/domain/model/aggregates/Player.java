package cat.itacademy.s05.blackjack.domain.model.aggregates;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private PlayerId id;
    private PlayerName name;
    private Integer wins;
    private Integer losses;
    private Integer totalGames;
}
