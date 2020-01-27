package com.tetris.database.hibernate.repositories;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.entity.DbFigureType;
import com.tetris.database.entity.DbGame;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;


@Slf4j
public class Hiber {
    public static DbGame getGameById(int id){
        DbGame game = null;
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            game = (DbGame) session.load(DbGame.class, id);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return game;
    }
    public static DbFigureType getFigureTypeById(int id){
        DbFigureType type = null;
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            type = (DbFigureType) session.load(DbFigureType.class, id);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return type;
    }
}
