package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void exceptionIfTheGameIsNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Sergey");
        assertThrows(RuntimeException.class, () -> {
            player.play(game, 10);

        });
    }

    @Test
    public void addingTheSameGameMultipleTimes() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Counter-Strike", "Шутер");
        Game game1 = store.publishGame("Counter-Strike", "Шутер");

        Player player = new Player("Katya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 10);
        player.play(game1, 15);

        int expected = 10;
        int actual = player.sumGenre("Шутер");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGames() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Counter-Strike", "Шутер");
        Game game1 = store.publishGame("Stalker", "Шутер");

        Player player = new Player("Petya");

        player.installGame(game);
        player.installGame(game1);
        player.play(game, 3);
        player.play(game1, 10);

        int expected = 13;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void compareHoursByGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Counter-Strike", "Шутер");
        Game game1 = store.publishGame("Stalker", "Шутер");

        Player player = new Player("Petya");

        player.installGame(game);
        player.installGame(game1);

        player.play(game, 30);
        player.play(game1, 40);


        Game expected = game1;
        Game actual = player.mostPlayerByGenre(expected.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void notPlayedInThisGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Counter-Strike", "Шутер");
        Game game1 = store.publishGame("Stalker", "Шутер");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Katya");

        player.installGame(game);
        player.installGame(game1);

        player.play(game, 30);
        player.play(game1, 40);

        String expected = null;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);

    }
}

