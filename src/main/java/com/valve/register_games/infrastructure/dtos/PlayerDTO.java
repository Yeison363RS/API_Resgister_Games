package com.valve.register_games.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PlayerDTO {
    private long id;
    private String email;
    private String name;
    private String lastName;
    private String userName;
    private String gender;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date birthdate;
    private int hours_game;
}
