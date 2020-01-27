package com.tetris.database.repositories.hiberimpl;

import com.tetris.database.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class HiberMoveRepositoryTest {
    private HiberMoveRepository moveRepository = new HiberMoveRepository();
    @Test
    public void getAllMovesTest() {
        Deque<String> expected = new ArrayDeque<>();
        expected.add("MOVE_DOWN");
        Deque<String> actual = moveRepository.getAllMoves(12);
        assertEquals(expected, actual);
    }
    @Test
    public void getHiber(){
        ConnectionFactory.sessionFactory.openSession();
    }
}