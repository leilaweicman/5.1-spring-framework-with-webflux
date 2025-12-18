package cat.itacademy.s05.blackjack.infrastructure.web.controller;

import cat.itacademy.s05.blackjack.application.usecase.CreatePlayerUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetPlayerUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetRankingUseCase;
import cat.itacademy.s05.blackjack.application.usecase.UpdatePlayerNameUseCase;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerName;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.PlayerRequest;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.PlayerResponse;
import cat.itacademy.s05.blackjack.infrastructure.web.mapper.PlayerDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final CreatePlayerUseCase createPlayerUseCase;
    private final GetPlayerUseCase getPlayerUseCase;
    private final UpdatePlayerNameUseCase updatePlayerNameUseCase;
    private final GetRankingUseCase getRankingUseCase;
    private final PlayerDtoMapper mapper;

    @PostMapping
    public Mono<ResponseEntity<PlayerResponse>> createPlayer(
            @Valid @RequestBody PlayerRequest request) {

        return createPlayerUseCase.create(new PlayerName(request.name()))
                .map(mapper::toResponse)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    // GET PLAYER BY ID
    @GetMapping("/{id}")
    public Mono<PlayerResponse> getPlayer(@PathVariable Long id) {
        return getPlayerUseCase.get(new PlayerId(id))
                .map(mapper::toResponse);
    }

    @PutMapping("/{id}")
    public Mono<PlayerResponse> updateName(
            @PathVariable Long id,
            @Valid @RequestBody PlayerRequest request) {

        return updatePlayerNameUseCase.update(new PlayerId(id), new PlayerName(request.name()))
                .map(mapper::toResponse);
    }

    @GetMapping("/ranking")
    public Flux<PlayerResponse> getRanking() {
        return getRankingUseCase.getRanking()
                .map(mapper::toResponse);
    }
}


