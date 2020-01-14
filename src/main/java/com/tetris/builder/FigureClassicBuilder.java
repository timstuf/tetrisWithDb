package com.tetris.builder;

import com.tetris.database.repositories.impl.FigureRepository;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import lombok.AllArgsConstructor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class FigureClassicBuilder extends AbstractFigureBuilder {
    private List<Figure> classic = new ArrayList<>();
    @Override
    public Figure next(Point boardStartPoint) {
        repository.saveFigure(getGameId(), 0);
        throw new NotImplementedException();
    }
    private void fillFigures(){
        List<Point> points = new ArrayList<>();
        //####
        points.add(new Point(0,0));
        points.add(new Point(0,1));
        points.add(new Point(0,2));
        points.add(new Point(0,3));
        classic.add( Figure.builder()
                .points(points)
                .pivot(new Point(0, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());
        points.clear();

        //###
        // #
        points.add(new Point(1,0));
        points.add(new Point(1,1));
        points.add(new Point(1,2));
        points.add(new Point(0,1));
        classic.add( Figure.builder()
                .points(points)
                .pivot(new Point(1, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());
        points.clear();
        //###
        //  #
        points.add(new Point(1,0));
        points.add(new Point(1,1));
        points.add(new Point(1,2));
        points.add(new Point(0,2));
        classic.add( Figure.builder()
                .points(points)
                .pivot(new Point(1, 2))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());
        points.clear();
        //##
        //##
        points.add(new Point(0,2));
        points.add(new Point(1,1));
        points.add(new Point(1,2));
        points.add(new Point(0,1));
        classic.add( Figure.builder()
                .points(points)
                .pivot(new Point(1, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());
        points.clear();
    }
}

