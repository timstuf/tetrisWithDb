package com.tetris.builder;

import com.tetris.database.repositories.impl.GameRepository;
import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.util.List;

public class FigureRestoreBuilder extends AbstractFigureBuilder {
    private List<Figure> figures;
    private final FigureBuilder builder;

    public FigureRestoreBuilder(int gameId, FigureBuilder builder) {
        super(gameId);
        this.builder = builder;
    }
    @Override
    public Figure next(Point boardStartPoint) {
        if(figures.size()>0) {
            Figure figure = figures.get(0);
            figures.remove(0);
            return new Figure(figure.getPoints(), figure.getPivot(), new Point(boardStartPoint.getX(), boardStartPoint.getY() + 1));
        }
        else{
            Figure figure = new FigureClassicBuilder(getGameId()).next(boardStartPoint);
            repository.saveFigure(getGameId(), figure);
            return figure;
        }
    }

}
