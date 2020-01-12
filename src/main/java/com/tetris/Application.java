package com.tetris;

import com.tetris.builder.FigureBuilderFactory;
import com.tetris.database.DataTables;
import com.tetris.database.GameData;
import com.tetris.model.Board;
import com.tetris.model.Player;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

import static com.tetris.builder.FigureBuilderFactory.BuilderType.TEST;


@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("Start tetris application {}", Arrays.toString(args));
       // new DataTables().doEverything();
        System.out.println("Please specify game id (if new, then 0)");
        Player player = new Player(new GameData(new Scanner(System.in).nextInt()));
        player.playGame();
    }
}

