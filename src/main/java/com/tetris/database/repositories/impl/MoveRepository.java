package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.repositories.Repository;
import com.tetris.game.handler.MoveEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveRepository implements Repository {

    public void saveNewMoveEvent(int gameId, MoveEvent event){
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into action(game_id, action_name) values(?,?)");
            int i = 0;
            statement.setInt(++i, gameId);
            statement.setString(++i, event.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> getAllMoves(int gameId) {
        List<String> moves = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from action where game_id = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                moves.add(resultSet.getString("action_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return moves;
    }
}
