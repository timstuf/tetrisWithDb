package com.tetris.game.handler.user;

import com.tetris.builder.FigureBuilderFactory;
import com.tetris.database.repositories.impl.GameRepository;
import com.tetris.database.repositories.impl.MoveRepository;
import com.tetris.game.Board;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.PlayerMoveEventPool;
import lombok.Getter;

import java.util.List;
import java.util.Scanner;

@Getter
public class UserMoveHandler implements MoveHandler {
    private final int gameId;
    private final MoveRepository moveRepository = new MoveRepository();
    private final PlayerMoveEventPool moveEventPool = new PlayerMoveEventPool();
    public UserMoveHandler(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public MoveEvent getNewMoveEvent() {
        Scanner scanner = new Scanner(System.in);
        MoveEvent event;
        event = moveEventPool.pool.get(scanner.nextLine());
        moveRepository.saveNewMoveEvent(gameId, event);
        return event;
    }






}
