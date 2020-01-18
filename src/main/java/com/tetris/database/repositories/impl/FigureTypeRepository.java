package com.tetris.database.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tetris.game.Figure;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tetris.database.ConnectionFactory.getConnection;

@Slf4j
public class FigureTypeRepository {

    public void fillJsonRepository(Map<Integer, Figure> figurePool){
        clearRepository();
        ObjectMapper objectMapper = new ObjectMapper();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("insert into figure_type(figure_id, figure) values(?,?)");
        figurePool.forEach((k,v)->{
            try {
                statement.setInt(1, k);
                statement.setString(2, objectMapper.writeValueAsString(v));
            } catch (SQLException | JsonProcessingException e) {
                log.error(e.getMessage());
            }
        });}catch (SQLException e){
            log.error(e.getMessage());
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
