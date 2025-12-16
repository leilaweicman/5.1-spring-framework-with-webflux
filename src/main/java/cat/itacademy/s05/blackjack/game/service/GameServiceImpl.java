package cat.itacademy.s05.blackjack.game.service;

import cat.itacademy.s05.blackjack.domain.engine.BlackJackEngine;
import cat.itacademy.s05.blackjack.domain.game.Game;
import cat.itacademy.s05.blackjack.domain.game.GameStatus;
import cat.itacademy.s05.blackjack.game.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.game.exception.InvalidGameActionException;
import cat.itacademy.s05.blackjack.game.model.MoveAction;
import cat.itacademy.s05.blackjack.game.repository.GameRepository;
import cat.itacademy.s05.blackjack.player.exception.PlayerNotFoundException;
import cat.itacademy.s05.blackjack.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final BlackJackEngine engine;

    @Override
    public Mono<Game> createGame(Long playerId) {
        return playerService.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player -> {
                    Game game = engine.startNewGame(playerId);
                    return gameRepository.save(game);
                });
    }

    @Override
    public Mono<Game> getGame(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }

    @Override
    public Mono<Game> playMove(String gameId, String action) {
        MoveAction act = MoveAction.from(action);

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)))
                .flatMap(game -> {

                    if (game.getStatus() != GameStatus.IN_PROGRESS) {
                        return Mono.error(new InvalidGameActionException("Game is already finished"));
                    }

                    switch (act) {
                        case HIT   -> engine.playerHits(game);
                        case STAND -> engine.playerStands(game);
                    }

                    return gameRepository.save(game);
                });
    }

    @Override
    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)))
                .flatMap(game -> gameRepository.deleteById(gameId));
    }
}
