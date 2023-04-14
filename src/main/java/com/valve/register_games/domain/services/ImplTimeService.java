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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ImplTimeService implements ServiceTimeGame {

    @Autowired
    private TimeGameStorage timeGameStorage;

    @Autowired
    private GameStorage gameStorage;

    @Autowired
    private PlayerStorage playerStorage;
    @Transactional
    @Override
    public Object saveTimeGame(TimeGameDTO timeGameDTO) {
        Player player=playerStorage.findPlayerById(timeGameDTO.getIdPlayer());
        Game game=gameStorage.findGameById(timeGameDTO.getIdGame());
        TimeGame timeGameFound =timeGameStorage.findTimeGameById(timeGameDTO.getId_time_game());

        if(game==null || player == null){
            return ("No existe un ")+ (player == null?"jugador con el id indicado":"juego con el id indicado");
        }
        if(timeGameFound != null){
            return "Ya existe un Tiempo de Juego con ese id";
        }
        return timeGameStorage.saveTimeGame(TimeGame.builder()
                .game(game)
                .player(player)
                .numberHours(timeGameDTO.getNumberHours())
                .build());
    }
    @Transactional(readOnly = true)
    @Override
    public List<TimeGame> getAll() {
        return null;
    }

    @Transactional
    @Override
    public String deleteTimeGame(long idTimeGame) {
        TimeGame timeGameFound = timeGameStorage.findTimeGameById(idTimeGame);
        return timeGameFound==null?"No existe el tiempo de juego":timeGameStorage.deleteTimeGame(timeGameFound);
    }
    @Transactional
    @Override
    public Object editTimeGame(TimeGame timeGame) {
        TimeGame timeGameFound = timeGameStorage.findTimeGameById(timeGame.getId_time_game());
        if(timeGameFound == null){
            return "No se pudo encontrar el tiempo de juego solicitado";
        }
        timeGameFound.setNumberHours(timeGame.getNumberHours());
        return timeGameStorage.editTimeGame(timeGameFound);
    }
}
