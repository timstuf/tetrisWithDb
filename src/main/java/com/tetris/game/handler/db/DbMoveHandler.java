package com.tetris.game.handler.db;

import com.tetris.database.repositories.impl.MoveRepository;
import com.tetris.game.Board;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.PlayerMoveEventPool;
import com.tetris.game.handler.user.UserMoveHandler;
import lombok.AllArgsConstructor;

import java.util.List;


public class DbMoveHandler implements MoveHandler {
    private final UserMoveHandler userMoveHandler;
    private final List<String> dbMoves;

    public DbMoveHandler(UserMoveHandler userMoveHandler) {
        this.userMoveHandler = userMoveHandler;
        this.dbMoves = getDbMoves();
    }

    @Override
    public MoveEvent getNewMoveEvent() {
        if(dbMoves.size()>0){
            MoveEvent event = PlayerMoveEventPool.pool.get(dbMoves.get(0));
            dbMoves.remove(0);
            return event;
        }
        else return userMoveHandler.getNewMoveEvent();
    }
    private List<String> getDbMoves() {
        return userMoveHandler.getMoveRepository().getAllMoves(userMoveHandler.getGameId());
    }
}
