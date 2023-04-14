package com.valve.register_games.infrastructure.persistence.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="TIMES_GAME")
public class TimeGameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_time_game",nullable = false)
    private Long id_time_game;
    @NotNull
    @Column(name = "number_hours")
    private int numberHours;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_player")
    private PlayerEntity player;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_game")
    private GameEntity game;
}
