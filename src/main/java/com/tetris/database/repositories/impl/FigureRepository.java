package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.repositories.Repository;
import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FigureRepository implements Repository {

    public void saveFigure(int gameId, Figure figure){
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
    public List<Figure> getFiguresByGameId(int gameId){
        List<Figure> figures = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from figure where game_id = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Figure figure = getFigureFromString(resultSet.getString("figure_points"), resultSet.getString("figure_pivot"));
                figures.add(figure);
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

    private String figureToString(Figure figure) {
        return figure.getPoints().stream().map(point -> String.valueOf(point.getX()) + point.getY()).collect(Collectors.joining());
    }
    private String getPivot(Figure figure){
        String x = String.valueOf(figure.getPivot().getX());
        String y = String.valueOf(figure.getPivot().getY());
        return x.concat(y);
    }
}
