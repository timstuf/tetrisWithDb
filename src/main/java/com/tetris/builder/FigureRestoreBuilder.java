package com.tetris.builder;

import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.util.List;

public class FigureRestoreBuilder extends AbstractFigureBuilder {

    FigureTypePool figureTypePool = new FigureTypePool();
    private List<Figure> figures;
    private final FigureBuilder builder;

    public FigureRestoreBuilder(int gameId, FigureBuilder builder) {
        super(gameId);
        this.builder = builder;
        List<Integer> figuresId = getFigureRepository().getFiguresByGameId(gameId);
        figures = FigureTypePool.getFiguresByType(figuresId);
    }
    @Override
    public Figure next(Point boardStartPoint) {
        if(figures.size()>0) {
            Figure figure = figures.get(0);
            figures.remove(0);
            return new Figure(figure.getPoints(), figure.getPivot(), new Point(boardStartPoint.getX(), boardStartPoint.getY() + 1));
        }
        else{
            return builder.next(boardStartPoint);
        }
    }

}
