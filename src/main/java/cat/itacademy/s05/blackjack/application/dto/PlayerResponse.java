package cat.itacademy.s05.blackjack.application.dto;

public record PlayerResponse(
        Long id,
        String name,
        int wins,
        int losses,
        int totalGames
) {}
