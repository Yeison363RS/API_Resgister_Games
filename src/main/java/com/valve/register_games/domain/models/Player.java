package com.valve.register_games.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class Player {

    private long id;
    private String email;
    private String name;
    private String lastName;
    private Date birthdate;
    private String gender;
    private String userName;
    private int hours_game;
}
