package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@MyTable("figure_type")
@Table(name = "figure_type")
@Entity
public class DbFigureType {

    @MyColumn("figure_id")  @Column(name = "figure_id")
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int figureId;

    @MyColumn("figure")
    private String figure;

    public DbFigureType(){}
}
