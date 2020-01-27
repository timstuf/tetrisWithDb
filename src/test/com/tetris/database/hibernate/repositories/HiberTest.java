package com.tetris.database.hibernate.repositories;

import com.tetris.database.entity.DbGame;
import com.tetris.database.repositories.hiberimpl.HiberMoveRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class HiberTest {

    @Test
    void save() {
    }

    @Test
    void getGameById() {
        DbGame game = Hiber.getGameById(10);
        assertEquals(10, game.getGameId());
        System.out.println(game.getGameId());
    }

    @Test
    void getFigureTypeById() {
    }

    @Test
    void getAllMoves() {
        Queue<String> expected = new ArrayDeque<>();
        expected.add("MOVE_DOWN");
        Queue<String> actual = new HiberMoveRepository().getAllMoves(12);
        assertEquals(expected, actual);
    }

    @Test
    void getAllFigures() {
    }
}