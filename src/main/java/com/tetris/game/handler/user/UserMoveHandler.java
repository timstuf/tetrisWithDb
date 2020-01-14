package com.tetris.game.handler.user;

import com.tetris.builder.FigureBuilderFactory;
import com.tetris.database.repositories.impl.GameRepository;
import com.tetris.database.repositories.impl.MoveRepository;
import com.tetris.game.Board;
import com.tetris.game.handler.MoveEvent;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.PlayerMoveEventPool;

import java.util.List;
import java.util.Scanner;

import static com.tetris.builder.FigureBuilderFactory.BuilderType.RESTORE;

public class UserMoveHandler implements MoveHandler {
    private final int gameId;
    private final MoveRepository moveRepository = new MoveRepository();
    private final PlayerMoveEventPool moveEventPool = new PlayerMoveEventPool();
    public UserMoveHandler(int gameId) {
        this.gameId = gameId;
    }

    private void doDbMoves(Board board) {
        List<String> moves = game.getAllMoves();
        while (moves.size() > 0) {
            board.doGame(moveEventPool.pool.get(moves.get(0)));
            moves.remove(0);
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(7, 10, new FigureBuilderFactory(game).getBuilder(RESTORE));
        doDbMoves(board);
        while (true) {
            System.out.println(board.getStringState());
            try {
                MoveEvent moveEvent = moveEventPool.pool.get(scanner.nextLine());
                game.writeMove(moveEvent);
                board.doGame(moveEvent);
            } catch (NullPointerException e) {
                System.out.println("Please input correct letter");
            }
        }
    }

    @Override
    public MoveEvent getNewMoveEvent() {
        Scanner scanner = new Scanner(System.in);

        MoveEvent event;
        do {
            event = moveEventPool.pool.get(scanner.nextLine());
            moveRepository.saveNewMoveEvent(gameId, event);
        } while (event == null);
        return event;
    }
}
