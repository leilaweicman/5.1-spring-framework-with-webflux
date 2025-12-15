package cat.itacademy.s05.blackjack.player.dto;

public record PlayerResponseDto(
        Long id,
        String name,
        int wins,
        int losses,
        int totalGames
) {}
