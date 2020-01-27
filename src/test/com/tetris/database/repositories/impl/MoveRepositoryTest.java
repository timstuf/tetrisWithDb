package com.tetris.database.repositories.impl;

import com.tetris.database.repositories.hiberimpl.HiberMoveRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class MoveRepositoryTest {
    private HiberMoveRepository moveRepository = new HiberMoveRepository();
    @Test
    public void getAllMovesTest() {
        Deque<String> expected = new ArrayDeque<>();
        expected.add("MOVE_DOWN");
        Deque<String> actual = moveRepository.getAllMoves(12);
        assertEquals(expected, actual);
    }
}