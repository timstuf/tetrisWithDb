package com.tetris.database.repositories.hiberimpl;

import com.tetris.database.ConnectionFactory;
import com.tetris.database.entity.DbGame;
import com.tetris.database.repositories.Repository;
import com.tetris.model.GameState;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@Slf4j
public class HiberGameRepository implements Repository {
    public boolean isActiveGameExists() {
        DbGame game = null;
        try (Session session = ConnectionFactory.sessionFactory.openSession()) {
            List<DbGame> gameList = session.createQuery("from DbGame where gameId = (select max(gameId) from DbGame)").list();
            game = gameList.get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (game != null) {
            return game.getGameStatus() == GameState.ACTIVE;
        }
        return false;
    }

    public Optional<Integer> getActiveGameId() {
        DbGame game = null;
        try (Session session = ConnectionFactory.sessionFactory.openSession()) {
            List<DbGame> listGames = session.createQuery("from DbGame where gameId = (select max(gameId) from DbGame)").list();
            game = listGames.get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (game != null) {
            return Optional.of(game.getGameId());
        } else return Optional.empty();
    }

    public int createNewGame() {
        DbGame game = new DbGame(GameState.ACTIVE);
        save(game);
        Optional<Integer> id = getActiveGameId();
        return id.orElse(0);
    }

    public static void finishGame(int gameId) {
        DbGame game = null;
        try (Session session = ConnectionFactory.sessionFactory.openSession()) {
            session.beginTransaction();
            game = session.get(DbGame.class, gameId);
            game.setGameStatus(GameState.FINISHED);
            session.update(game);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
