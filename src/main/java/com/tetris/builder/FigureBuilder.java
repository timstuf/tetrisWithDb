package com.tetris.builder;

import com.tetris.database.repositories.impl.FigureRepository;
import com.tetris.game.Figure;
import com.tetris.model.Point;

public interface FigureBuilder {

    Figure next(Point boardStartPoint);
}
