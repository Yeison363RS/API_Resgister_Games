package com.valve.register_games.domain.services;

import com.valve.register_games.application.ports.input.ServiceTimeGame;
import com.valve.register_games.application.ports.output.GameStorage;
import com.valve.register_games.application.ports.output.PlayerStorage;
import com.valve.register_games.application.ports.output.TimeGameStorage;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import com.valve.register_games.domain.models.TimeGame;
import com.valve.register_games.infrastructure.dtos.TimeGameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImplTimeService implements ServiceTimeGame {

    @Autowired
    private TimeGameStorage timeGameStorage;

    @Autowired
    private GameStorage gameStorage;

    @Autowired
    private PlayerStorage playerStorage;
    @Override
    public String saveTimeGame(TimeGameDTO timeGameDTO) {
        /*hacer validacion*/
        Player player=playerStorage.findPlayerById(timeGameDTO.getIdPlayer());
        Game game=gameStorage.findGameById(timeGameDTO.getIdGame());
        if(game==null || player == null){
            return "No se pudo encontrar"+player == null?"jugador con el id indicado":"juego con el id indicado";
        }
        return timeGameStorage.saveTimeGame(TimeGame.builder()
                .game(game)
                .player(player)
                .numberHours(timeGameDTO.getNumberHours())
                .build());
    }

    @Override
    public List<TimeGame> getAll() {
        return null;
    }

    @Override
    public String deleteTimeGame(long idTimeGame) {
        //TimeGame player = playerStorage.findPlayerById(timeGameDTO.getIdPlayer());
        //Game game=gameStorage.findGameById(timeGameDTO.getIdGame());
        //if(game==null || player == null){
          //  return "No se pudo encontrar"+player == null?"jugador con el id indicado":"juego con el id indicado";
        //};
        TimeGame timeGame=TimeGame.builder().id_time_game(idTimeGame).build();
        return  timeGameStorage.deleteTimeGame(timeGame);
    }

    @Override
    public String editTimeGame(TimeGame timeGame) {
        System.out.println("Time game search "+ timeGame.getId_time_game());
        TimeGame timeGameFound = timeGameStorage.findTimeGameById(timeGame.getId_time_game());
        if(timeGameFound == null){
            return "No se pudo encontrar el tiempo";
        }
        timeGameFound.setNumberHours(timeGame.getNumberHours());
        //Player player=playerStorage.findPlayerById(timeGameDTO.getIdPlayer());
        //Game game=gameStorage.findGameById(timeGameDTO.getIdGame());
        //if(game==null || player == null){
          //  return "No se pudo encontrar"+player == null?"jugador con el id indicado":"juego con el id indicado";
        //}
        return timeGameStorage.saveTimeGame(timeGameFound);
    }
}
