package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@MyTable("figure_type")
@Table(name = "figure_type")
@Entity
public class DbFigureType {

    @Column(name = "figure_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int figureId;

    @Column(name = "figure")
    private String figure;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "figures")
    private List<DbGame> games;
}
