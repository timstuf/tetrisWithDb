package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

@Data
@MyTable("game")
@Table(name = "game")
@Entity
public class DbGame {

    @MyColumn("game_id") @Column(name = "game_id")
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int gameId;

    @MyColumn("game_status") @Column(name = "game_status")
    private String gameStatus;

    @OneToMany
    private Collection<DbMove> moves = new ArrayDeque<>();

    @OneToMany
    private Collection<DbFigure> figures = new ArrayDeque<>();

    public DbGame(){}
    public DbGame(String gameStatus){
        this.gameStatus = gameStatus;
    }
}
