package com.tetris.model;

import com.tetris.builder.FigureBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.tetris.model.MoveEvent.MoveEventType.MOVE_DOWN;

@Slf4j
public class Board {
    private final int height;
    private final int width;
    private final List<Point> fillPoints = new ArrayList<>();
    private Figure activeFigure;
    private final FigureBuilder figureBuilder;
    private final Point startFigurePoint;

    public Board(int height, int width, FigureBuilder figureBuilder) {
        this.height = height;
        this.width = width;
        this.figureBuilder = figureBuilder;
        this.startFigurePoint = new Point(width / 2, 0);
        this.activeFigure = figureBuilder.next(startFigurePoint);
    }

    public void doPlayerMove(MoveEvent moveEvent) {

                Figure nextFigure = activeFigure.getNewFigureByMoveEventType(moveEvent.getType());

                if (!isValidFigureCoordinates(nextFigure) && !isValidFigurePoints(nextFigure) && moveEvent.getType() == MOVE_DOWN) {
                    log.debug("Change figure state on the board. Current state {}", activeFigure);
                    addFigurePointsToFillPoints(activeFigure);
                    activeFigure = figureBuilder.next(startFigurePoint);
                    log.debug("Change figure state on the board. New state {}", activeFigure);
                    return;
                }
                if (isValidFigureCoordinates(nextFigure) || !isValidFigurePoints(nextFigure)) {
                    log.debug("Add figure to fill points {}", activeFigure);
                    addFigurePointsToFillPoints(activeFigure);
                    activeFigure = figureBuilder.next(startFigurePoint);
                    return;
                }
                activeFigure = nextFigure;
    }


    // TODO: 11/26/2019 rename
    //this doesn't work because active figure contains points that next figure wants to fill
    private boolean isValidFigurePoints(Figure figure) {
        return figure.getPointsByBoardCoordinates().stream().noneMatch(fillPoints::contains);
    }

    // TODO: 11/26/2019 rename
    private boolean isValidFigureCoordinates(Figure figure) {
        return figure.getPointsByBoardCoordinates().stream().
                allMatch(point -> point.getX() < 0 || point.getX() > width - 1 || point.getY() > height - 1);
    }

    private void addFigurePointsToFillPoints(Figure figure) {
        fillPoints.addAll(figure.getPointsByBoardCoordinates());
    }


    public String getStringState() {
        char[][] charBoard = new char[height][width];
        fillPoints.forEach(point -> charBoard[point.getX()][point.getY()] = '#');
        activeFigure.getPointsByBoardCoordinates().forEach(point -> charBoard[point.getY()][point.getX()] = '*');
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            builder.append('-');
        }
        builder.append('\n');
        Arrays.stream(charBoard).forEach(chars -> {
            builder.append('|');
            for (char character : chars) {
                builder.append(character);
            }
            builder.append('|');
            builder.append('\n');
        });
        for (int i = 0; i < height; i++) {
            builder.append('-');
        }
        builder.append("--------------------------------------------------------------");
        return builder.toString();
    }
}
