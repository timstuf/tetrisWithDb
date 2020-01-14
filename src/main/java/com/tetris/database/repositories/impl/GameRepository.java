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


    public List<String> getAllMoves() {
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

    public void writeFigure(Figure figure) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into figure(game_id, figure_points, figure_pivot) values(?,?,?)");
            int i = 0;
            statement.setInt(++i, gameId);
            statement.setString(++i, figureToString(figure));
            statement.setString(++i, getPivot(figure));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void writeMove(MoveEvent moveEvent) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into action(game_id, action_name) values(?,?)");
            int i = 0;
            statement.setInt(++i, gameId);
            statement.setString(++i, moveEvent.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<int> getActiveGameId() {
        return Optional.empty();
        if (gameId != 0) return gameId;
        int id=0;
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

    private Figure getFigureFromString(String spoints, String pivot) {
        List<Point> figurePoints = new ArrayList<>();
        while (spoints.length() > 0) {

            figurePoints.add(new Point(Integer.parseInt(String.valueOf(spoints.charAt(0))), Integer.parseInt(String.valueOf(spoints.charAt(1)))));
            spoints = spoints.substring(2);
        }
        return Figure.builder()
                .points(figurePoints)
                .pivot(new Point(Integer.parseInt(String.valueOf(pivot.charAt(0))), Integer.parseInt(String.valueOf(pivot.charAt(1)))))
                .currentCoordinateOnBoard(new Point(0, 0))
                .build();
    }

    private String figureToString(Figure figure) {
        return figure.getPoints().stream().map(point -> String.valueOf(point.getX()) + point.getY()).collect(Collectors.joining());
    }
    private String getPivot(Figure figure){
        String x = String.valueOf(figure.getPivot().getX());
        String y = String.valueOf(figure.getPivot().getY());
        return x.concat(y);
    }
    public int addGame() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into game (GAME_STATUS) values(?)");
            statement.setString(1, "in progress");
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getGameId();
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
