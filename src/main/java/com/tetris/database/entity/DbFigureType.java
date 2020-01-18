package com.tetris.database.entity;

import com.tetris.database.entity.annotations.Column;
import com.tetris.database.entity.annotations.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Table("figure_type")
public class DbFigureType {
 @Column("figure_id")
    private int figure_id;
    @Column("figure")
 private String figure;
}
