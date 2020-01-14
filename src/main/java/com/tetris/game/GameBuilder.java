package com.tetris.game;

import com.tetris.builder.FigureBuilder;
import com.tetris.builder.FigureBuilderFactory;
import com.tetris.database.repositories.impl.GameRepository;
import com.tetris.game.handler.user.UserMoveHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

import static com.tetris.builder.FigureBuilderFactory.BuilderType.CLASSIC;
import static com.tetris.builder.FigureBuilderFactory.BuilderType.RESTORE;



public class GameBuilder {
    private static int HEIGHT = 15;
    private static int WIDTH = 10;

    private static final GameRepository gameRepository = new GameRepository(1);
    public static Game build(){
        Optional<int> activeGameId = gameRepository.getActiveGameId();
        if(activeGameId.isPresent())
            return restoreGame();
            else  return buildNewGame();
    }
    public static Game restoreGame() {
        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getRestoreBuilder());
        return new Game(new UserMoveHandler(ga), board));
    }
    public static Game buildNewGame(){
        int gameId = gameRepository.getActiveGameId()
        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getClassicBuilder());
        return new Game(new UserMoveHandler(gameId), board);
    }
}
