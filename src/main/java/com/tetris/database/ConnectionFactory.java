package com.tetris.database;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class ConnectionFactory {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/tetris";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "Admin123";


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    private void createDataBases(){
        String dbGame = "CREATE TABLE IF NOT EXISTS game("
                + "GAME_ID SERIAL, "
                + "GAME_STATUS VARCHAR(20) NOT NULL, "
                + "PRIMARY KEY (GAME_ID) "
                + ")";
        String dbAction = "CREATE TABLE IF NOT EXISTS action("
                + "ACTION_ID SERIAL, "
                + "GAME_ID INT NOT NULL, "
                + "ACTION_NAME VARCHAR(20) NOT NULL, "
                + "PRIMARY KEY (ACTION_ID), "
                + "FOREIGN KEY (GAME_ID) REFERENCES game(GAME_ID)"
                + ")";
        String dbFigure = "CREATE TABLE IF NOT EXISTS figure("
                + "FIGURE_ID SERIAL, "
                + "GAME_ID INT NOT NULL, "
                + "FIGURE_POINTS VARCHAR(20) NOT NULL, "
                + "PRIMARY KEY (FIGURE_ID), "
                + "FOREIGN KEY (GAME_ID) REFERENCES game(GAME_ID)"
                + ")";
        try (Connection dbConnection = getConnection();
             Statement statement = dbConnection.createStatement()) {
            // выполнить SQL запрос
            statement.execute(dbGame);
            log.info("Table \"dbGame\" is created!");
            statement.execute(dbAction);
            log.info("Table \"dbAction\" is created!");
            statement.execute(dbFigure);
            log.info("Table \"dbFigure\" is created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void doEverything(){
        createDataBases();
    }

    public void addGame(){
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("insert into game (GAME_STATUS) values(?)");
            statement.setString(1, "finished");
            statement.execute();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void changeGameStatus(){
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("update game (GAME_STATUS) values(?)");
            statement.setString(1, "finished");
            statement.execute();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
