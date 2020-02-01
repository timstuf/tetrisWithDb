package com.tetris.game.handler.db;

import com.tetris.database.repositories.hiberimpl.HiberGameRepository;
import com.tetris.database.repositories.hiberimpl.HiberMoveRepository;
import com.tetris.game.Game;
import com.tetris.game.TickThread;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.PlayerMoveEventPool;
import com.tetris.game.handler.user.UserMoveHandler;
import lombok.AllArgsConstructor;

import java.util.Deque;

import static com.tetris.model.GameState.ACTIVE;


public class DbMoveHandler extends MoveHandler {
    private final UserMoveHandler userMoveHandler;
    private final Deque<String> dbMoves;

    public DbMoveHandler(UserMoveHandler userMoveHandler, Game game) {
        this.userMoveHandler = userMoveHandler;
        this.game = game;
        this.dbMoves = getDbMoves();
    }

    @Override
    public MoveEvent getNewMoveEvent() {
        if(dbMoves.size()>0){
            return MoveEvent.get(dbMoves.pop());
        }
        else {
            notifySubscribers();
            return userMoveHandler.getNewMoveEvent();
        }
    }
    private Deque<String> getDbMoves() {
        return HiberMoveRepository.getAllMoves(userMoveHandler.getGame().getGameId());
    }

    public void startGame(){
        while(game.getState() == ACTIVE){
            game.doMove(getNewMoveEvent());
        }
    }
}
