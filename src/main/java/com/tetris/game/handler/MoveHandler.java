package com.tetris.game.handler;

import com.tetris.database.repositories.hiberimpl.HiberGameRepository;
import com.tetris.game.Game;
import com.tetris.game.TickThread;

import java.util.*;

import static com.tetris.model.GameState.ACTIVE;

public abstract class MoveHandler {
    public Game game;
    public Thread tickThread;
    public abstract MoveEvent getNewMoveEvent();
    List<com.tetris.game.EventListener> listeners = new ArrayList<>();

    public void subscribe(com.tetris.game.EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(com.tetris.game.EventListener listener) {
        listeners.remove(listener);
    }

    public void notifySubscribers() {
        for (com.tetris.game.EventListener listener : listeners) {
            listener.update();
        }
    }


}
