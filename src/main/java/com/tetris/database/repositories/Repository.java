package com.tetris.database.repositories;

import com.tetris.database.ConnectionFactory;
import org.hibernate.Session;

import java.sql.Connection;


public interface Repository {
    default Connection getConnection(){
        return ConnectionFactory.getConnection();
    }
    default public <T> void save(T t){
        try(Session session = ConnectionFactory.sessionFactory.openSession()){
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
