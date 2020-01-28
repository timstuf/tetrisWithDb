package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import com.tetris.game.handler.MoveEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "move")
@Entity
public class DbMove {

    @Column(name = "move_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "move_generator")
    @SequenceGenerator(name="move_generator", sequenceName = "tetris_move")
    private int moveId;

    @Column(name = "move_name")
    @Enumerated(EnumType.STRING)
    private MoveEvent moveName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_id", nullable = false)
    private DbGame game;

    public DbMove(DbGame game, MoveEvent moveName) {
        this.game = game;
        this.moveName = moveName;
    }
}
