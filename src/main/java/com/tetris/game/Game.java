package com.tetris.game;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.tetris.database.repositories.impl.MoveRepository;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.model.GameState;
import lombok.AllArgsConstructor;

import static com.tetris.model.GameState.ACTIVE;

@AllArgsConstructor
public class Game {
    private final MoveHandler moveHandler;
    private final  Board board;

    public void start(MoveEvent moveEvent){
        GameState state = ACTIVE;
        while(state == ACTIVE){
            board.doGame(moveHandler.getNewMoveEvent());
        }
    }
}
