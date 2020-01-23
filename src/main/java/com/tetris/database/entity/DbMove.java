package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@MyTable("move")  @Table(name = "move")
@Entity
public class DbMove {

    @Column(name = "move_id")
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int moveId;

   // @ManyToOne
    //@JoinColumn(name = "game_id", nullable = false)
    @MyColumn("game_id") @Column(name = "game_id")
    private int gameId;

    @MyColumn("move_name") @Column(name = "move_name")
    private String moveName;

    public DbMove(){}
    public DbMove(int gameId, String moveName){
        this.gameId = gameId;
        this.moveName = moveName;
    }
}
