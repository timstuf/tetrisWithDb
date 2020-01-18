package com.tetris.game.handler.user;

import com.tetris.game.handler.MoveEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tetris.game.handler.MoveEvent.*;


public class PlayerMoveEventPool {
    public static Map<String, MoveEvent> pool = new HashMap<>();

    PlayerMoveEventPool() {
        pool.put("a", MOVE_LEFT);
        pool.put("s", MOVE_DOWN);
        pool.put("d", MOVE_RIGHT);
        pool.put("q", LEFT_ROTATE);
        pool.put("e", RIGHT_ROTATE);
        pool = Collections.unmodifiableMap(pool);
    }
}
