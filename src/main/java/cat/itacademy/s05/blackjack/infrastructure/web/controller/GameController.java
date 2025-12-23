package cat.itacademy.s05.blackjack.infrastructure.web.controller;

import cat.itacademy.s05.blackjack.application.usecase.CreateGameUseCase;
import cat.itacademy.s05.blackjack.application.usecase.DeleteGameUseCase;
import cat.itacademy.s05.blackjack.application.usecase.GetGameDetailsUseCase;
import cat.itacademy.s05.blackjack.application.usecase.PlayGameUseCase;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.GameId;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.MoveAction;
import cat.itacademy.s05.blackjack.domain.model.valueobjects.PlayerId;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.CreateGameRequest;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.GameResponse;
import cat.itacademy.s05.blackjack.infrastructure.web.dto.MoveRequest;
import cat.itacademy.s05.blackjack.infrastructure.web.mapper.GameDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Profile("docker")
@Tag(
        name = "Game",
        description = "Endpoints for creating and playing Blackjack games"
)
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final GetGameDetailsUseCase getGameUseCase;
    private final PlayGameUseCase playGameUseCase;
    private final DeleteGameUseCase deleteGameUseCase;

    private final GameDtoMapper mapper;


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Start a new game",
            description = "Creates a new Blackjack game for the given player.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Game created"),
                    @ApiResponse(responseCode = "404", description = "Player not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body")
            }
    )
    public Mono<GameResponse> create(@RequestBody CreateGameRequest request) {
        return createGameUseCase.create(new PlayerId(request.playerId()))
                .map(mapper::toResponse);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get game details",
            description = "Returns the full state of an existing game.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Game found"),
                    @ApiResponse(responseCode = "404", description = "Game not found")
            }
    )
    public Mono<GameResponse> getGameDetails(
            @Parameter(description = "Game identifier")
            @PathVariable String id) {
        return getGameUseCase.get(new GameId(id))
                .map(mapper::toResponse);
    }


    @PostMapping("/{id}/move")
    @Operation(
            summary = "Play a move",
            description = "Executes a HIT or STAND action for an ongoing Blackjack game.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Move executed"),
                    @ApiResponse(responseCode = "404", description = "Game not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid move")
            }
    )
    public Mono<GameResponse> playMove(
            @Parameter(description = "Game identifier")
            @PathVariable String id,
            @RequestBody MoveRequest request) {

        return playGameUseCase.play(
                        new GameId(id),
                        MoveAction.valueOf(request.action().toUpperCase())
                )
                .map(mapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a game",
            description = "Removes an existing game from the system.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Game deleted"),
                    @ApiResponse(responseCode = "404", description = "Game not found")
            }
    )
    public Mono<Void> deleteGame(
            @Parameter(description = "Game identifier")
            @PathVariable String id) {
        return deleteGameUseCase.delete(new GameId(id));
    }
}


