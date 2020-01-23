package com.tetris.database.hibernate.repositories;

import com.tetris.database.entity.DbFigure;
import com.tetris.database.entity.DbFigureType;
import com.tetris.database.entity.DbGame;
import com.tetris.database.entity.DbMove;
import com.tetris.database.hibernate.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.Deque;

@Slf4j
public class FigureDAO {
    public static void addFigure(DbFigure figure){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(figure);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    public static void addMove(DbMove move){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(move);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    public static void addGame(DbGame game){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(game);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    public static DbGame getGameById(int id){
        DbGame game = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
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
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            type = (DbFigureType) session.load(DbFigureType.class, id);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return type;
    }

}
