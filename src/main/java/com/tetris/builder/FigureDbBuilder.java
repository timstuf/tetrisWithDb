package com.tetris.builder;

import com.tetris.database.GameData;
import com.tetris.model.Figure;
import com.tetris.model.Point;

import java.util.List;

public class FigureDbBuilder implements FigureBuilder {
    private List<Figure> figures;
    private GameData gameData;

    FigureDbBuilder(GameData gameData){
        this.gameData = gameData;
        figures = gameData.getAllFigures();
    }
    @Override
    public Figure next(Point boardStartPoint) {
        if(figures.size()>0) {
            Figure figure = figures.get(0);
            figures.remove(0);
            return new Figure(figure.getPoints(), figure.getPivot(), new Point(boardStartPoint.getX(), boardStartPoint.getY() + 1));
        }
        else{
            Figure figure = new FigureTestBuilder().next(boardStartPoint);
            gameData.writeFigure(figure);
            return figure;
        }
    }

}
