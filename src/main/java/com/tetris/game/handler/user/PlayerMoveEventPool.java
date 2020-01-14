package com.tetris.game.handler.user;

import com.tetris.game.handler.MoveEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tetris.game.handler.MoveEvent.*;


public class PlayerMoveEventPool {
    public final Map<String, MoveEvent> pool;

    public PlayerMoveEventPool() {
        Map<String, MoveEvent> pool = new HashMap<>();
        pool.put("a", MOVE_LEFT);
        pool.put("s", MOVE_DOWN);
        pool.put("d", MOVE_RIGHT);
        pool.put("q", LEFT_ROTATE);
        pool.put("e", RIGHT_ROTATE);
        pool.put("MOVE_LEFT", MOVE_LEFT);
        pool.put("MOVE_DOWN", MOVE_DOWN);
        pool.put("MOVE_RIGHT", MOVE_RIGHT);
        pool.put("LEFT_ROTATE", LEFT_ROTATE);
        pool.put("RIGHT_ROTATE",RIGHT_ROTATE);
        this.pool = Collections.unmodifiableMap(pool);
    }
}
