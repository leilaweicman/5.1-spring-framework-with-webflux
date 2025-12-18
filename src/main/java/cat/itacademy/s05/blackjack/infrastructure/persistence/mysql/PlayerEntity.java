package cat.itacademy.s05.blackjack.infrastructure.persistence.mysql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity {

    @Id
    private Long id;

    private String name;
    private Integer wins;
    private Integer losses;
    private Integer totalGames;
}

