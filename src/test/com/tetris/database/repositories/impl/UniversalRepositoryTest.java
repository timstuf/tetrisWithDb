package com.tetris.database.repositories.impl;

import com.tetris.database.entity.DbFigure;
import com.tetris.database.entity.DbFigureType;
import com.tetris.database.entity.DbGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UniversalRepositoryTest {
    private UniversalRepository universalRepository = new UniversalRepository();

    @Test
    void saveGame() {
        DbGame dbGame = new DbGame(26, "FINISHED");
        universalRepository.saveToRepository(dbGame);
    }
    @Test
    void saveFigureType() {
        DbFigureType dbFigureType = new DbFigureType(7, "some string");
        universalRepository.saveToRepository(dbFigureType);
    }
    @Test
    void saveFigure() {
        DbFigure dbFigure = new DbFigure(10, 2);
        universalRepository.saveToRepository(dbFigure);
    }
}