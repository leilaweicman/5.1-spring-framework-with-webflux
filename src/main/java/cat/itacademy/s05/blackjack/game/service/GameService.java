package cat.itacademy.s05.blackjack.game.service;

import cat.itacademy.s05.blackjack.domain.game.Game;
import reactor.core.publisher.Mono;

public interface GameService {
    Mono<Game> createGame(Long playerId);
    Mono<Game> getGame(String id);
    Mono<Game> playMove(String id, String action);
    Mono<Void> deleteGame(String id);
}
