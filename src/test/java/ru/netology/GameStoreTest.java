package ru.netology;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    String playerName = "gamer";
    String playerName1 = "username";
    String playerName2 = "newbie";

    @Test
    public void shouldAddGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddTime() {
        GameStore store = new GameStore();
        store.addPlayTime(playerName, 33);
        store.addPlayTime(playerName, 44);
        store.addPlayTime(playerName, 34);
        Map<String, Integer> playedTime = null;
        try {
            Field field = store.getClass().getDeclaredField("playedTime");
            field.setAccessible(true);
            playedTime = (Map<String, Integer>) field.get(store);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        int expected = 33 + 44 + 34;
        int actual = playedTime.get(playerName);
        assertEquals(expected, actual);
    }

    @Test
    public void getMostPlayerTest() {
        GameStore store = new GameStore();
        store.addPlayTime(playerName, 33);
        store.addPlayTime(playerName1, 55);
        store.addPlayTime(playerName2, 99);
        String expected = playerName2;
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void get2BecomesTheMostPlayed() {
        GameStore store = new GameStore();
        store.addPlayTime(playerName, 33);
        store.addPlayTime(playerName1, 33);
        store.addPlayTime(playerName2, 10);
        String expected = (playerName + " " + playerName1);
        String actual = store.getMostPlayer();
        assertEquals(expected, actual);
    }

    @Test
    public void getMostPlayedReturnNull() {
        GameStore store = new GameStore();
        String actual = store.getMostPlayer();
        assertNull(actual);
    }

    @Test
    public void shouldSumPlayedTime() {
        GameStore store = new GameStore();
        store.addPlayTime(playerName, 33);
        store.addPlayTime(playerName1, 44);
        store.addPlayTime(playerName, 34);
        store.addPlayTime(playerName2, 99);
        int expected = 33 + 44 + 34 + 99;
        int actual = store.getSumPlayedTime();
        assertEquals(expected, actual);
    }
}