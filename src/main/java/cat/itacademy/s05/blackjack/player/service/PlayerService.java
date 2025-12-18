//package cat.itacademy.s05.blackjack.player.service;
//
//import cat.itacademy.s05.blackjack.domain.model.aggregates.Player;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//public interface PlayerService {
//
//    Mono<Player> createPlayer(String name);
//
//    Mono<Player> updatePlayerName(Long playerId, String name);
//
//    Mono<Player> findById(Long playerId);
//
//    Flux<Player> getRanking();
//}
