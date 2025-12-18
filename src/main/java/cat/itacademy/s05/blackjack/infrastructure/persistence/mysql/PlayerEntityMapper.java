package cat.itacademy.s05.blackjack.infrastructure.persistence.mysql;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import org.springframework.stereotype.Component;

@Component
public class PlayerEntityMapper {

    public PlayerEntity toEntity(Player player) {
        return PlayerEntity.builder()
                .id(player.getId() != null ? player.getId().value() : null)
                .name(player.getName().value())
                .wins(player.getWins())
                .losses(player.getLosses())
                .totalGames(player.getTotalGames())
                .build();
    }

    public Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .id(new PlayerId(entity.getId()))
                .name(new PlayerName(entity.getName()))
                .wins(entity.getWins())
                .losses(entity.getLosses())
                .totalGames(entity.getTotalGames())
                .build();
    }
}
