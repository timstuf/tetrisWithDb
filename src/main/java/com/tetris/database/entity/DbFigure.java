package com.tetris.database.entity;

import ch.qos.logback.classic.db.names.ColumnName;
import com.tetris.database.entity.annotations.Column;
import com.tetris.database.entity.annotations.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Table("figure")
public class DbFigure {
    @Column("game_id")
    private int game_id;
    @Column("figure_id")
    private int figure_id;

}
