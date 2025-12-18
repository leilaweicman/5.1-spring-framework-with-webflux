//package cat.itacademy.s05.blackjack.game.controller;
//
//import cat.itacademy.s05.blackjack.application.dto.CreateGameRequest;
//import cat.itacademy.s05.blackjack.application.dto.PlayGameRequest;
//import cat.itacademy.s05.blackjack.application.dto.GameResponse;
//import cat.itacademy.s05.blackjack.game.mapper.GameMapper;
//import cat.itacademy.s05.blackjack.game.service.GameService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/game")
//@RequiredArgsConstructor
//public class GameController {
//
//    private final GameService gameService;
//    private final GameMapper mapper;
//
//    @PostMapping("/new")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<GameResponse> createNewGame(@Valid @RequestBody CreateGameRequest request) {
//        return gameService.createGame(request.playerId())
//                .map(mapper::toResponse);
//    }
//
//    @GetMapping("/{id}")
//    public Mono<GameResponse> getGame(@PathVariable String id) {
//        return gameService.getGame(id)
//                .map(mapper::toResponse);
//    }
//
//    @PostMapping("/{id}/play")
//    public Mono<GameResponse> playMove(@PathVariable String id, @Valid @RequestBody PlayGameRequest request) {
//        return gameService.playMove(id, request.action())
//                .map(mapper::toResponse);
//    }
//
//    @DeleteMapping("{id}/delete")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> deleteGame(@PathVariable String id) {
//        return gameService.deleteGame(id);
//    }
//}
