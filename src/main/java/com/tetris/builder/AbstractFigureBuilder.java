package com.tetris.builder;

import com.tetris.database.repositories.impl.FigureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractFigureBuilder implements FigureBuilder{
     final FigureRepository figureRepository = new FigureRepository();
    private final int gameId;

}
