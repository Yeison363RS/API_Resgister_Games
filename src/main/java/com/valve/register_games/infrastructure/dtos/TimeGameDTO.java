package com.valve.register_games.infrastructure.dtos;

import lombok.Data;

@Data
public class TimeGameDTO {
    private long id_time_game;
    private long idPlayer;
    private long idGame;
    private int numberHours;
}
