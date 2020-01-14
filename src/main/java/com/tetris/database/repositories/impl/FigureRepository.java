package com.tetris.database.repositories.impl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.repositories.Repository;
import com.tetris.game.Figure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FigureRepository implements Repository {

    public void saveFigure(int gameId, int figureId){

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

    private Figure getFigureFromString(String figure_points, String figure_pivot) {
        return null;
    }
}
