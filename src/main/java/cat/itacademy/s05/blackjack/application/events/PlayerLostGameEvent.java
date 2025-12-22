package cat.itacademy.s05.blackjack.application.events;

public record PlayerLostGameEvent(Long playerId, String gameId) {}

