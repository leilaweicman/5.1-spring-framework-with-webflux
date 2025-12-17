package cat.itacademy.s05.blackjack.application.usecase.impl;

import cat.itacademy.s05.blackjack.application.usecase.DeleteGameUseCase;
import cat.itacademy.s05.blackjack.domain.exception.GameNotFoundException;
import cat.itacademy.s05.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteGameUseCaseImpl implements DeleteGameUseCase {

    private final GameRepository gameRepository;

    @Override
    public Mono<Void> delete(String id) {
        return gameRepository.existsById(id)
                .flatMap(exists -> exists
                        ? gameRepository.deleteById(id)
                        : Mono.error(new GameNotFoundException(id)));
    }
}

