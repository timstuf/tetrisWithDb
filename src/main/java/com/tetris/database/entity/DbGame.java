package com.tetris.database.entity;

import com.tetris.database.entity.annotations.MyColumn;
import com.tetris.database.entity.annotations.MyTable;
import com.tetris.model.GameState;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
@Entity
public class DbGame {

    @Column(name = "game_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_generator")
    @SequenceGenerator(name="game_generator", sequenceName = "tetris_game")
    private int gameId;

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    private GameState gameStatus;

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DbMove> moves = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "figure",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "figure_id")})
    public List<DbFigureType> figures = new ArrayList<>();

    public DbGame(GameState gameStatus) {
        this.gameStatus = gameStatus;
    }
}
