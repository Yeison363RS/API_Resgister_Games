package com.valve.register_games.infrastructure.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class GameDTO {
    private long id;
    private String name;
    private String description;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateRelease;
    private int hours_game;
}
