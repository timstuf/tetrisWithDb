package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.entity.DbMove;
import com.tetris.database.hibernate.repositories.Hiber;
import com.tetris.database.repositories.Repository;
import com.tetris.game.handler.MoveEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class MoveRepository implements Repository {

    public void saveNewMoveEvent(int gameId, MoveEvent event){
       try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into move(game_id, move_name) values(?,?)");
            int i = 0;
            statement.setInt(++i, gameId);
            statement.setString(++i, event.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Deque<String> getAllMoves(int gameId) {
        Deque<String> moves = new ArrayDeque<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from move where game_id = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                moves.add(resultSet.getString("move_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return moves;
    }
}
