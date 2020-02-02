package com.tetris.game;

import com.tetris.database.repositories.hiberimpl.HiberMoveRepository;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.user.UserMoveHandler;
import com.tetris.model.GameState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class TickThread implements Runnable, EventListener {
    private UserMoveHandler handler;
    private HiberMoveRepository moveRepository = new HiberMoveRepository();
    private volatile boolean running;
    private volatile boolean waiting;

    public TickThread(UserMoveHandler handler, boolean running, boolean waiting) {
        this.handler = handler;
        this.running = running;
        this.waiting = waiting;
    }


    @Override
    public void run() {
        log.debug("Thread TickThread is running");
        while (handler.getGame().getState() == GameState.ACTIVE) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
           handler.saveEvent(MoveEvent.MOVE_DOWN);
            handler.getGame().doMove(MoveEvent.MOVE_DOWN);
        }
    }



    @Override
    public void update() {

    }
}
