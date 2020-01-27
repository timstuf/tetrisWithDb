package com.tetris.database.repositories.impl;

import com.tetris.database.entity.DbFigureType;
import com.tetris.database.entity.DbGame;
import com.tetris.model.GameState;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

class UniversalRepositoryTest {
    private UniversalRepository universalRepository = new UniversalRepository();

    @Ignore
    @Test
    void saveGame() {
        DbGame dbGame = new DbGame(GameState.FINISHED);
        universalRepository.saveToRepository(dbGame);
    }
    @Ignore
    @Test
    void saveFigureType() {
        DbFigureType dbFigureType = new DbFigureType();
        universalRepository.saveToRepository(dbFigureType);
    }
    @Test
    void saveFigure() {
       // DbFigure dbFigure = new DbFigure(10, 2);
        //universalRepository.saveToRepository(dbFigure);
    }
}