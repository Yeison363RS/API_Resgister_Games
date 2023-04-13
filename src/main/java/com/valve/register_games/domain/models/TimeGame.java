package com.valve.register_games.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeGame {
    private long id_time_game;
    private Player player;
    private Game game;
    private int numberHours;
}
