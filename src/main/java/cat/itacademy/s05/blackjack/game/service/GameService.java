package cat.itacademy.s05.blackjack.game.service;

import cat.itacademy.s05.blackjack.domain.model.aggregates.Game;
import reactor.core.publisher.Mono;

public interface GameService {
    Mono<Game> createGame(Long playerId);
    Mono<Game> getGame(String gameId);
    Mono<Game> playMove(String gameId, String action);
    Mono<Void> deleteGame(String gameId);
}
