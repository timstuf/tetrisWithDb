package com.tetris.game.handler.db;

import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DbMoveHandler implements MoveHandler {
    private final UserMoveHandler userMoveHandler;
    @Override
    public MoveEvent getNewMoveEvent() {
        return null;
    }
}
