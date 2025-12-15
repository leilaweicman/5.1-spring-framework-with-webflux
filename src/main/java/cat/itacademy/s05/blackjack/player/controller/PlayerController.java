package cat.itacademy.s05.blackjack.player.controller;

import cat.itacademy.s05.blackjack.player.dto.PlayerRequestDto;
import cat.itacademy.s05.blackjack.player.dto.PlayerResponseDto;
import cat.itacademy.s05.blackjack.player.mapper.PlayerMapper;
import cat.itacademy.s05.blackjack.player.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PlayerResponseDto> createPlayer(@RequestBody @Valid PlayerRequestDto request) {
        return playerService.createPlayer(request.name()).map(mapper::toResponse);
    }

    @PutMapping("/{id}")
    public Mono<PlayerResponseDto> updatePlayerName(@PathVariable Long id, @RequestBody @Valid PlayerRequestDto request) {
        return playerService.updatePlayerName(id, request.name()).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<PlayerResponseDto> getPlayer(@PathVariable Long id) {
        return playerService.findById(id).map(mapper::toResponse);
    }
}
