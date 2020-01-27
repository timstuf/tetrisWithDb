package com.tetris.database.repositories.hiberimpl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.entity.DbFigureType;
import com.tetris.database.entity.DbGame;
import com.tetris.database.repositories.Repository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

@Slf4j
public class HiberFigureRepository implements Repository {
    public void saveFigure(int gameId, int figureId) {
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            DbGame game = session.get(DbGame.class, gameId);
            DbFigureType figureType = session.get(DbFigureType.class, figureId );
            game.getFigures().add(figureType);
            figureType.getGames().add(game);
            session.update(game);
            session.update(figureType);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    public Deque<Integer> getAllFigures(int id){
        Deque<Integer> figures = new ArrayDeque<>();
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            DbGame game = session.load(DbGame.class, id);
            figures =  game.getFigures().stream().map(DbFigureType::getFigureId).collect(Collectors.toCollection(ArrayDeque::new));
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return figures;
    }
}