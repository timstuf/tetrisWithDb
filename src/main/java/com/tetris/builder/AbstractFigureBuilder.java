package com.tetris.builder;

import com.tetris.database.repositories.Repository;
import com.tetris.database.repositories.hiberimpl.HiberFigureRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractFigureBuilder implements FigureBuilder{
     final HiberFigureRepository figureRepository = new HiberFigureRepository();
    private final int gameId;

}
