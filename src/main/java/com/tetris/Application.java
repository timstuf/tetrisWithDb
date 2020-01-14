package com.tetris;

import com.tetris.database.repositories.impl.GameRepository;
import com.tetris.game.GameBuilder;
import com.tetris.game.handler.user.UserMoveHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;


@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("Start tetris application {}", Arrays.toString(args));
       // new DataTables().doEverything();
        GameBuilder.build().start();
    }
}

