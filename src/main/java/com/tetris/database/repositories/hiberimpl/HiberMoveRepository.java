package com.tetris.database.repositories.hiberimpl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.entity.DbGame;
import com.tetris.database.entity.DbMove;
import com.tetris.database.hibernate.repositories.Hiber;
import com.tetris.database.repositories.Repository;
import com.tetris.game.handler.MoveEvent;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

@Slf4j
public class HiberMoveRepository implements Repository {
    public void saveNewMoveEvent(int gameId, MoveEvent event){
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            DbGame game = session.get(DbGame.class, gameId);
            DbMove move = new DbMove(game, event);
            session.save(move);
            game.getMoves().add(move);
            session.update(game);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    public static Deque<String> getAllMoves(int id){
        Deque<String> moves = new ArrayDeque<>();
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            DbGame game = session.load(DbGame.class, id);
            moves =  game.getMoves().stream().map(move->move.getMoveName().toString()).collect(Collectors.toCollection(ArrayDeque::new));
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return moves;
    }
}
