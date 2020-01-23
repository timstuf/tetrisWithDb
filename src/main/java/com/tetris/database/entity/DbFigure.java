package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@MyTable("figure")
@Table(name = "figure")
@Entity
public class DbFigure {

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

   // @ManyToOne
    //@JoinColumn(name = "game_id", nullable = false)
    @MyColumn("game_id") @Column(name = "game_id")
    private int gameId;

    //@ManyToOne
   // @JoinColumn(name = "figure_id", nullable = false)
    //  @MyColumn("figure_id") @Column(name = "figure_id")
    private int figureId;

    public DbFigure(){ }
    public DbFigure(int gameId, int figureId){
        this.figureId = figureId;
        this.gameId = gameId;
    }
}
