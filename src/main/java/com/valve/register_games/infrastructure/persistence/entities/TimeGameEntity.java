package com.valve.register_games.infrastructure.persistence.entities;

import com.valve.register_games.domain.models.Player;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="TIMES_GAME")
public class TimeGameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_time_game",nullable = false)
    private Long id_time_game;

    @Column(name = "number_hours")
    private long numberHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_player")
    private PlayerEntity player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game")
    private GameEntity game;
}
