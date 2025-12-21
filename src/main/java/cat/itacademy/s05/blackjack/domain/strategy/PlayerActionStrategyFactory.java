package cat.itacademy.s05.blackjack.domain.strategy;

import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerActionStrategyFactory {

    private final HitActionStrategy hitStrategy;
    private final StandActionStrategy standStrategy;

    public PlayerActionStrategy get(MoveAction action) {
        return switch (action) {
            case HIT -> hitStrategy;
            case STAND -> standStrategy;
        };
    }
}

