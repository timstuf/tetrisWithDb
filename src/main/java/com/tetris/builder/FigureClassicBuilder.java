package com.tetris.builder;

import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.util.*;

public class FigureClassicBuilder extends AbstractFigureBuilder {
    public FigureClassicBuilder(int gameId) {
        super(gameId);
    }
    @Override
    public Figure next(Point boardStartPoint) {
        Figure figure = getRandomClassicFigure();
        figureRepository.saveFigure(getGameId(), FigureTypePool.getFigureIdByFigure(figure));
        return new Figure(figure.getPoints(), figure.getPivot(), new Point(boardStartPoint.getX(), boardStartPoint.getY() + 1));
    }
    private Figure getRandomClassicFigure(){
        Random random = new Random();
        int k = random.nextInt(5);
        return FigureTypePool.figurePool.get(k);
    }
}

