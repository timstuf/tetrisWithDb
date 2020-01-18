package com.tetris.database.entity;

import com.tetris.database.entity.annotations.Column;
import com.tetris.database.entity.annotations.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Table("move")
public class DbMove {
    //@Column("move_id")
    //private int move_id;
    @Column("game_id")
    private int game_id;
    @Column("move_name")
    private int move_name;
}
