package cat.itacademy.s05.blackjack.game.controller;

import cat.itacademy.s05.blackjack.game.dto.GameCreateRequestDto;
import cat.itacademy.s05.blackjack.game.dto.GameMoveRequestDto;
import cat.itacademy.s05.blackjack.game.dto.GameResponseDto;
import cat.itacademy.s05.blackjack.game.mapper.GameMapper;
import cat.itacademy.s05.blackjack.game.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameMapper mapper;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GameResponseDto> createNewGame(@Valid @RequestBody GameCreateRequestDto request) {
        return gameService.createGame(request.playerId())
                .map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<GameResponseDto> getGame(@PathVariable String id) {
        return gameService.getGame(id)
                .map(mapper::toResponse);
    }

    @PostMapping("/{id}/play")
    public Mono<GameResponseDto> playMove(@PathVariable String id, @Valid @RequestBody GameMoveRequestDto request) {
        return gameService.playMove(id, request.action())
                .map(mapper::toResponse);
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id);
    }
}
