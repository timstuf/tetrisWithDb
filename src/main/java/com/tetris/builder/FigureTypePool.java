package com.tetris.builder;

import com.tetris.database.repositories.impl.FigureTypeRepository;
import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tetris.database.ConnectionFactory.getConnection;

public class FigureTypePool {
    static Map<Integer, Figure> figurePool;
    private FigureTypeRepository figureTypeRepository = new FigureTypeRepository();

    public FigureTypePool() {
        figurePool = new HashMap<>();
        fillMap();
        figurePool = Collections.unmodifiableMap(figurePool);
        figureTypeRepository.fillRepository(figurePool);
    }
    private void fillMap(){
        List<Point> points0 = new ArrayList<>();
        List<Point> points1 = new ArrayList<>();
        List<Point> points2 = new ArrayList<>();
        List<Point> points3 = new ArrayList<>();
        List<Point> points4 = new ArrayList<>();
        //####
        points0.add(new Point(0, 0));
        points0.add(new Point(0, 1));
        points0.add(new Point(0, 2));
        points0.add(new Point(0, 3));
        figurePool.put(0, Figure.builder()
                .points(points0)
                .pivot(new Point(0, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());

        //###
        // #
        points1.add(new Point(1, 0));
        points1.add(new Point(1, 1));
        points1.add(new Point(1, 2));
        points1.add(new Point(0, 1));
        figurePool.put(1, Figure.builder()
                .points(points1)
                .pivot(new Point(1, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());

        //###
        //  #
        points2.add(new Point(1, 0));
        points2.add(new Point(1, 1));
        points2.add(new Point(1, 2));
        points2.add(new Point(0, 2));
        figurePool.put(2, Figure.builder()
                .points(points2)
                .pivot(new Point(1, 2))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());

        //##
        //##
        points3.add(new Point(0, 2));
        points3.add(new Point(1, 1));
        points3.add(new Point(1, 2));
        points3.add(new Point(0, 1));
        figurePool.put(3, Figure.builder()
                .points(points3)
                .pivot(new Point(1, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());

        // ##
        //##
        points4.add(new Point(0, 0));
        points4.add(new Point(1, 0));
        points4.add(new Point(1, 1));
        points4.add(new Point(2, 1));
        figurePool.put(4, Figure.builder()
                .points(points4)
                .pivot(new Point(1, 1))
                .currentCoordinateOnBoard(new Point(0, 1))
                .build());
    }

    public static Integer getFigureIdByFigure(Figure value) {
        return figurePool.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    public static List<Figure> getFiguresByType(List<Integer> types){
        List<Figure> figures = new ArrayList<>();
        types.forEach((k)-> figures.add(figurePool.get(k)));
        return figures;
    }
}
