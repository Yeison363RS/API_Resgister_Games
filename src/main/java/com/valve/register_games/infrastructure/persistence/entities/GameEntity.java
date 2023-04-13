package com.valve.register_games.infrastructure.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name="GAMES")
@Entity
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_game", nullable = false)
    private Long id;

    @Column(name="name")
    private String Name;

    @Column(name="description")
    private String description;

    @Column(name="date_release")
    private Date dateRelease;

    @Column(name="quatification")
    private int quantification;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<TimeGameEntity> timesGame;
}
