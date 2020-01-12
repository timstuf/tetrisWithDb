package com.tetris.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tetris.model.MoveEvent.MoveEventType.*;

public class PlayerMoveEventPool {
    public final Map<String, MoveEvent> pool;

    public PlayerMoveEventPool() {
        Map<String, MoveEvent> pool = new HashMap<>();
        pool.put("a", new MoveEvent(MOVE_LEFT));
        pool.put("s", new MoveEvent(MOVE_DOWN));
        pool.put("d", new MoveEvent(MOVE_RIGHT));
        pool.put("q", new MoveEvent(LEFT_ROTATE));
        pool.put("e", new MoveEvent(RIGHT_ROTATE));
        pool.put("MOVE_LEFT", new MoveEvent(MOVE_LEFT));
        pool.put("MOVE_DOWN", new MoveEvent(MOVE_DOWN));
        pool.put("MOVE_RIGHT", new MoveEvent(MOVE_RIGHT));
        pool.put("LEFT_ROTATE", new MoveEvent(LEFT_ROTATE));
        pool.put("RIGHT_ROTATE", new MoveEvent(RIGHT_ROTATE));
        this.pool = Collections.unmodifiableMap(pool);
    }
}
