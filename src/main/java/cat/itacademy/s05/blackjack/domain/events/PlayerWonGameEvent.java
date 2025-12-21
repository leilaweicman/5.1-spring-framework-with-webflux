package cat.itacademy.s05.blackjack.domain.events;

public record PlayerWonGameEvent(Long playerId, String gameId) {}

