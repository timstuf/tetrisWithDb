package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.entity.DbGame;
import com.tetris.database.hibernate.repositories.FigureDAO;
import com.tetris.database.repositories.Repository;
import com.tetris.model.GameState;

import java.sql.*;
import java.util.Optional;

public class GameRepository implements Repository {

    public boolean isActiveGameExists() {
        String status = "";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from game where game_id = (select max(game_id) from game)");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                status = resultSet.getString(2);
            return status.equals(String.valueOf(GameState.ACTIVE));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Optional<Integer> getActiveGameId() {
        int id = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select max(game_id) from game");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                id = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.of(id);
    }
    public int createNewGame() {
        DbGame game = new DbGame(String.valueOf(GameState.ACTIVE));
        FigureDAO.addGame(game);
        /*try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO game (GAME_STATUS) values(?)");
            statement.setString(1, String.valueOf(GameState.ACTIVE));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        return getActiveGameId().get();
    }
    public static void finishGame(int gameId) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE game SET game_status = ? WHERE game_id = ?");
            statement.setString(1, "FINISHED");
            statement.setInt(2, gameId);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
