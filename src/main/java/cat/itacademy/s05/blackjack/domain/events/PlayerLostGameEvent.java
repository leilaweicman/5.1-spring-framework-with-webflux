package cat.itacademy.s05.blackjack.domain.events;

public record PlayerLostGameEvent(Long playerId, String gameId) {}

