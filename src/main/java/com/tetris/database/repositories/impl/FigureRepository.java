package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.repositories.Repository;
import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FigureRepository implements Repository {

    public void saveFigure(int gameId, int figureId){
       try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into figure(game_id, figure_id) values(?,?)");
            int i = 0;
            statement.setInt(++i, gameId);
            statement.setInt(++i, figureId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Deque<Integer> getFiguresByGameId(int gameId){
         Deque<Integer> figures = new ArrayDeque<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select figure_id from figure where game_id = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                figures.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return figures;
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



}
