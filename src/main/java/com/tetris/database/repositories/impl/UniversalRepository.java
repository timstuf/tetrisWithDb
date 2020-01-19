package com.tetris.database.repositories.impl;

import com.tetris.database.entity.annotations.Column;
import com.tetris.database.entity.annotations.Table;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.tetris.database.ConnectionFactory.getConnection;

@Slf4j
public class UniversalRepository {
    public <T> void saveToRepository(T parameter) {
        String statementInString = getStatement(parameter);
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(statementInString);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }


    private <T> String countFieldsAndMakeQuestionMarks(T parameter) {
        int length =  parameter.getClass().getDeclaredFields().length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("?,");
        }
        return builder.deleteCharAt(builder.length()-1).toString();
    }
    private <T> String getFullStatement(Field[] fields, PreparedStatement statement, T parameter){
        AtomicInteger i = new AtomicInteger();
        Arrays.stream(fields).forEach(field->setType(statement, field, i.incrementAndGet(), parameter));
        return statement.toString();
    }
    private <T> void setType(PreparedStatement statement, Field field, int i, T parameter){
        field.setAccessible(true);
        Type type = field.getType();
        if(type.getTypeName().equals("java.lang.String")){
            try {
                statement.setString(i,field.get(parameter).toString());
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else if(type.getTypeName().equals("int")){
            try {
                statement.setInt(i, (Integer) field.get(parameter));
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    private <T> String getStatement(T parameter){
        String tableName = parameter.getClass().getAnnotation(Table.class).value();
        String questionMarks = countFieldsAndMakeQuestionMarks(parameter);
        Field[] fields = parameter.getClass().getDeclaredFields();
        String columnNames = Arrays.stream(fields).map(field->String.valueOf(field.getAnnotation(Column.class).value())).collect(Collectors.joining(", "));
        //String columnValues = Arrays.stream(fields).map(field->field.get.collect(Collectors.joining(", "));
        StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO ").append(tableName).append(" (").append(columnNames).
                append(" ) values (").append(questionMarks).append(")");
        try(Connection connection = getConnection()){
            return getFullStatement(fields, connection.prepareStatement(statement.toString()), parameter);
        }catch (SQLException s){
            log.error(s.getMessage());
        }
        return "";
    }
}
