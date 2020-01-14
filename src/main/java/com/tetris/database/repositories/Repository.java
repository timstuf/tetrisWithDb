package com.tetris.database.repositories;

import com.tetris.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Repository {
    default Connection getConnection(){
        return ConnectionFactory.getConnection();
    }


}
