package com.tetris.game;

import com.tetris.database.repositories.hiberimpl.HiberMoveRepository;
import com.tetris.game.handler.MoveEvent;
import com.tetris.model.GameState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class TickThread implements Runnable, EventListener {
    private Game game;
    private HiberMoveRepository moveRepository = new HiberMoveRepository();
    private volatile boolean running;
    private volatile boolean waiting;

    public TickThread(Game game, boolean running, boolean waiting) {
        this.game = game;
        this.running = running;
        this.waiting = waiting;
    }


    @Override
    public void run() {
        log.debug("Thread TickThread is running");
        while (game.getState() == GameState.ACTIVE) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            moveRepository.saveNewMoveEvent(game.getGameId(), MoveEvent.MOVE_DOWN);
            game.doMoveWithoutConsole(MoveEvent.MOVE_DOWN);
        }
    }



    @Override
    public void update() {

    }
}
