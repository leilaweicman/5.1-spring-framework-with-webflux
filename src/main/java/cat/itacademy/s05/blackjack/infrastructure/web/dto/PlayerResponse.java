package cat.itacademy.s05.blackjack.infrastructure.web.dto;

public record PlayerResponse(
        Long id,
        String name,
        int wins,
        int losses,
        int totalGames
) {}
