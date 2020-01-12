package com.tetris.model;

import com.tetris.builder.FigureBuilderFactory;
import com.tetris.database.GameData;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.tetris.builder.FigureBuilderFactory.BuilderType.DB;

public class Player {
    private final GameData game;
    private final PlayerMoveEventPool moveEventPool = new PlayerMoveEventPool();

    public Player(GameData game){
        this.game = game;
    }

    private void doDbMoves(Board board){
        List<String> moves = game.getAllMoves();
        while(moves.size()>0){
            board.doPlayerMove(moveEventPool.pool.get(moves.get(0)));
            moves.remove(0);
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(7, 10, new FigureBuilderFactory(game).getBuilder(DB));
        doDbMoves(board);
        while (true) {
            System.out.println(board.getStringState());
            try
            {
                MoveEvent moveEvent = moveEventPool.pool.get(scanner.nextLine());
                game.writeMove(moveEvent);
                board.doPlayerMove(moveEvent);
            }catch(NullPointerException e){
                System.out.println("Please input correct letter");
            }
        }
    }
}
