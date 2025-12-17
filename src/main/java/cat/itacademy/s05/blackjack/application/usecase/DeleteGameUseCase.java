package cat.itacademy.s05.blackjack.application.usecase;


import reactor.core.publisher.Mono;

public interface DeleteGameUseCase {
    Mono<Void> delete(String gameId);
}
