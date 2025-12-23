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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
@Profile("docker")
@Tag(
        name = "Players",
        description = "Endpoints for managing Blackjack players and ranking"
)
public class PlayerController {

    private final CreatePlayerUseCase createPlayerUseCase;
    private final GetPlayerUseCase getPlayerUseCase;
    private final UpdatePlayerNameUseCase updatePlayerNameUseCase;
    private final GetRankingUseCase getRankingUseCase;
    private final PlayerDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new player",
            description = "Creates a new Blackjack player if the chosen name is available.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Player created successfully"),
                    @ApiResponse(responseCode = "409", description = "Player name already exists"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body")
            }
    )
    public Mono<ResponseEntity<PlayerResponse>> createPlayer(
            @Valid @RequestBody PlayerRequest request) {

        return createPlayerUseCase.create(new PlayerName(request.name()))
                .map(mapper::toResponse)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get player by ID",
            description = "Retrieves an existing player based on its identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Player found"),
                    @ApiResponse(responseCode = "404", description = "Player not found")
            }
    )
    public Mono<PlayerResponse> getPlayer(
            @Parameter(description = "Player identifier")
            @PathVariable Long id) {
        return getPlayerUseCase.get(new PlayerId(id))
                .map(mapper::toResponse);
    }

    @PutMapping("/{id}/name")
    @Operation(
            summary = "Update player's name",
            description = "Updates the display name of an existing player.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Player name updated"),
                    @ApiResponse(responseCode = "404", description = "Player not found"),
                    @ApiResponse(responseCode = "409", description = "New name already taken")
            }
    )
    public Mono<PlayerResponse> updateName(
            @Parameter(description = "Player identifier")
            @PathVariable Long id,
            @Valid @RequestBody PlayerRequest request) {

        return updatePlayerNameUseCase.update(new PlayerId(id), new PlayerName(request.name()))
                .map(mapper::toResponse);
    }

    @GetMapping("/ranking")
    @Operation(
            summary = "Get player ranking",
            description = "Returns an ordered list of players based on wins and total games played.",
            responses = { @ApiResponse(responseCode = "200", description = "Ranking retrieved") }
    )
    public Flux<PlayerResponse> getRanking() {
        return getRankingUseCase.getRanking()
                .map(mapper::toResponse);
    }
}


