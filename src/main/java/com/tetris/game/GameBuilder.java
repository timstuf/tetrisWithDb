package com.tetris.game;

import com.tetris.builder.FigureBuilder;
import com.tetris.builder.FigureBuilderFactory;
import com.tetris.database.repositories.hiberimpl.HiberGameRepository;
import com.tetris.game.handler.db.DbMoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;
import com.tetris.model.GameState;

import java.util.Optional;

public class GameBuilder {
    private static int HEIGHT = 15;
    private static int WIDTH = 10;

    private static final HiberGameRepository gameRepository = new HiberGameRepository();
    public static Game build() {
        if(gameRepository.isActiveGameExists()){
            Optional<Integer> activeGameId = gameRepository.getActiveGameId();
            if(activeGameId.isPresent())
                return restoreGame(activeGameId.get());
            else throw new NullPointerException("Active game exists but get active name id returns null");
        }
        else return buildNewGame();
    }

    private static Game restoreGame(int id) {
        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getRestoreBuilder(id));
        return new Game(board, id, GameState.ACTIVE);
    }

    private static Game buildNewGame() {
        int gameId = gameRepository.createNewGame();
        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getClassicBuilder(gameId));
        return new Game(board, gameId, GameState.ACTIVE);
    }
}
