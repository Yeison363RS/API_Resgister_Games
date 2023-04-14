package com.valve.register_games.domain.models;

import lombok.Data;

import java.util.Date;

@Data
public class Game {
    private long id;
    private String name;
    private String description;
    private Date dateRelease;
    private int quantification;
    private int hours_game;
}
