package com.tetris.game.handler.user;

import com.tetris.database.repositories.hiberimpl.HiberMoveRepository;
import com.tetris.game.Game;
import com.tetris.game.TickThread;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.PlayerMoveEventPool;
import lombok.Getter;

import java.util.List;
import java.util.Scanner;

@Getter
public class UserMoveHandler extends MoveHandler {
    private final Game game;
    private final HiberMoveRepository moveRepository = new HiberMoveRepository();
    private final PlayerMoveEventPool moveEventPool = new PlayerMoveEventPool();

    public UserMoveHandler(Game game) {
        this.tickThread = new Thread(new TickThread(game, false, false));
        this.game = game;
    }

    @Override
    public MoveEvent getNewMoveEvent() {
        Scanner scanner = new Scanner(System.in);
        MoveEvent event;
        String ev = scanner.nextLine();
        if(!tickThread.isAlive())tickThread.start();
        event = moveEventPool.pool.get(ev);
        moveRepository.saveNewMoveEvent(game.getGameId(), event);
        return event;
    }


}
