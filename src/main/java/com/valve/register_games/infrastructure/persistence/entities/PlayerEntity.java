package com.valve.register_games.infrastructure.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.ColumnResult;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="PLAYERS")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_player", nullable = false)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "gender")
    private String gender;
    @Column(name = "username")
    private String username;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "player")
    private List<TimeGameEntity> listTimes;

}
