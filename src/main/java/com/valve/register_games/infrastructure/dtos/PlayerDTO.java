package com.valve.register_games.infrastructure.dtos;

import lombok.Data;

@Data
public class PlayerDTO {
    private String email;
    private String name;
    private String lastName;
    private String userName;
    private int hours_game;
}
