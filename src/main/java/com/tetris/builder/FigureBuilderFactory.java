package com.tetris.builder;

import com.tetris.database.GameData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tetris.builder.FigureBuilderFactory.BuilderType.*;

public class FigureBuilderFactory {

    private final Map<BuilderType, FigureBuilder> figureBuildersByType;

    public FigureBuilderFactory(GameData gameData) {
        Map<BuilderType, FigureBuilder> figureBuildersByType = new HashMap<>();
        figureBuildersByType.put(CLASSIC, new FigureClassicBuilder());
        figureBuildersByType.put(RANDOM, new FigureRandomBuilder());
        figureBuildersByType.put(TEST, new FigureTestBuilder());
        figureBuildersByType.put(DB, new FigureDbBuilder(gameData));
        this.figureBuildersByType = Collections.unmodifiableMap(figureBuildersByType);
    }

    public FigureBuilder getBuilder(BuilderType type) {
        return figureBuildersByType.get(type);

    }

    public enum BuilderType {
        CLASSIC,
        RANDOM,
        TEST,
        DB
    }
}
