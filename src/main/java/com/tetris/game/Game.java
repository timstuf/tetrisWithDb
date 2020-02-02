package com.tetris.game;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.tetris.database.repositories.hiberimpl.HiberGameRepository;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.db.DbMoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;
import com.tetris.model.GameState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.tetris.model.GameState.ACTIVE;

@Getter
@Setter
@AllArgsConstructor
public class Game {
    private final  Board board;
    private final int gameId;
    private volatile GameState state;

    public void start(){
        new DbMoveHandler(new UserMoveHandler(this), this).startGame();
        HiberGameRepository.finishGame(gameId);
    }

    public synchronized void  doMove(MoveEvent event){
        System.out.println(board.getStringState());
        state = board.doGame(event);
    }
    public void doMoveWithoutConsole(MoveEvent event){
        board.doGame(event);
    }
}
