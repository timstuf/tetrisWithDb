package com.tetris.database.entity;

import com.tetris.database.entity.annotations.Column;
import com.tetris.database.entity.annotations.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Table("game")
public class DbGame {
    @Column("game_id")
    private int game_id;
    @Column("game_status")
    private String game_status;
}
