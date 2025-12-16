package cat.itacademy.s05.blackjack.game.service;

import cat.itacademy.s05.blackjack.domain.engine.BlackJackEngine;
import cat.itacademy.s05.blackjack.domain.game.Game;
import cat.itacademy.s05.blackjack.game.repository.GameRepository;
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
        return null;
    }

    @Override
    public Mono<Game> getGame(String id) {
        return null;
    }

    @Override
    public Mono<Game> playMove(String id, String action) {
        return null;
    }

    @Override
    public Mono<Void> deleteGame(String id) {
        return null;
    }
}
