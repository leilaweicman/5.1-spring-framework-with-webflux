package cat.itacademy.s05.blackjack.domain.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("players")
public class Player {

    @Id
    private Long id;

    private String name;

    private Integer wins;
    private Integer losses;
    private Integer totalGames;
}
