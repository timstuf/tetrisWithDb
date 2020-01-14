package com.tetris.builder;



public class FigureBuilderFactory {

    public FigureBuilder getClassicBuilder (int gameId){
        return new FigureClassicBuilder();
    }
    public FigureBuilder getRestoreBuilder (int gameId){
        return new FigureRestoreBuilder();
    }

}
