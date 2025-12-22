package cat.itacademy.s05.blackjack.application.usecase;

import cat.itacademy.s05.blackjack.application.usecase.impl.PlayGameUseCaseImpl;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.mother.GameMother;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import cat.itacademy.s05.blackjack.domain.strategy.PlayerActionStrategy;
import cat.itacademy.s05.blackjack.domain.strategy.PlayerActionStrategyFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayGameUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerActionStrategyFactory strategyFactory;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private PlayerActionStrategy hitStrategy;

    @InjectMocks
    private PlayGameUseCaseImpl useCase;

    @Test
    void play_hitAction_shouldUpdateGame() {
        GameId gameId = new GameId("game-1");
        Game game = GameMother.gameInProgress();

        when(gameRepository.findById(gameId)).thenReturn(Mono.just(game));
        when(strategyFactory.get(MoveAction.HIT)).thenReturn(hitStrategy);
        when(hitStrategy.execute(game)).thenReturn(game);
        when(gameRepository.save(game)).thenReturn(Mono.just(game));

        Mono<Game> result = useCase.play(gameId, MoveAction.HIT);

        StepVerifier.create(result)
                .expectNext(game)
                .verifyComplete();

        verify(gameRepository).save(game);
    }

    @Test
    void play_whenGameNotFound_shouldThrowException() {
        GameId gameId = new GameId("missing");

        when(gameRepository.findById(gameId)).thenReturn(Mono.empty());

        Mono<Game> result = useCase.play(gameId, MoveAction.HIT);

        StepVerifier.create(result)
                .expectError(GameNotFoundException.class)
                .verify();
    }
}

