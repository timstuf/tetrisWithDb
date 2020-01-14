package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.repositories.Repository;
import com.tetris.game.Figure;
import com.tetris.game.handler.MoveEvent;
import com.tetris.model.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameRepository implements Repository {

    public boolean isActiveGameExists(){
        return false;
    }

    public Optional<Integer> getActiveGameId() {
        return Optional.empty();
    }


    public int createNewGame() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into game (GAME_STATUS) values(?)");
            statement.setString(1, "in progress");
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getLastGameId();
    }

    private int getLastGameId(){
        int id = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select max(game_id) from game");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                id =  resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void finishGame(int gameId) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update game set GAME_STATUS = ? where game_id = ?");
            statement.setString(1, "finished");
            statement.setInt(2, gameId);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
