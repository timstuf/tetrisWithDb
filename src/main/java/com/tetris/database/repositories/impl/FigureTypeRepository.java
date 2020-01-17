package com.tetris.database.repositories.impl;

import com.tetris.game.Figure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tetris.database.ConnectionFactory.getConnection;

public class FigureTypeRepository {
    private String figurePointsToString(Figure figure) {
        return figure.getPoints().stream().map(point -> String.valueOf(point.getX()) + point.getY()).collect(Collectors.joining());
    }
    private String getPivotInString(Figure figure){
        String x = String.valueOf(figure.getPivot().getX());
        String y = String.valueOf(figure.getPivot().getY());
        return x.concat(y);
    }

    public void fillRepository(Map<Integer, Figure> figurePool){
        clearRepository();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into figure_type(figure_id, figure_points, figure_pivot) values(?,?,?)");
            figurePool.forEach((k, v) -> {
                try {
                    statement.setInt(1, k);
                    statement.setString(2, figurePointsToString(v));
                    statement.setString(3, getPivotInString(v));
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void clearRepository(){
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from figure_type");
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
