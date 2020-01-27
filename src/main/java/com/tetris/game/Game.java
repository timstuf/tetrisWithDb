package com.tetris.game;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.tetris.database.repositories.hiberimpl.HiberGameRepository;
import com.tetris.game.handler.MoveHandler;
import com.tetris.model.GameState;
import lombok.AllArgsConstructor;

import static com.tetris.model.GameState.ACTIVE;

@AllArgsConstructor
public class Game {
    private final MoveHandler moveHandler;
    private final  Board board;
    private final int gameId;

    public void start(){
        GameState state = ACTIVE;
        while(state == ACTIVE){
            System.out.println(board.getStringState());
            state = board.doGame(moveHandler.getNewMoveEvent());
        }
        HiberGameRepository.finishGame(gameId);
    }
}
