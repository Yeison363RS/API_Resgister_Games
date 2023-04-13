package com.valve.register_games.domain.services;

import com.valve.register_games.application.ports.input.ServiceGame;
import com.valve.register_games.application.ports.output.GameStorage;
import com.valve.register_games.application.ports.output.PlayerStorage;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImplServiceGame implements ServiceGame {

    @Autowired
    private GameStorage gameStorage;
    @Autowired
    private PlayerStorage playerStorage;
    @Override
    public String saveGame(Game game) {
        return gameStorage.saveGame(game);
    }

    @Override
    public List<Game> getTopGamesForPlayer(int numberTops, Player player) {
        /*validar que el jugador exista o buscarlo por alguno de los atributos para obtener un objeto
        TO DO..*/
        List<Game> list=gameStorage.getTopGamesByOnePlayer(numberTops,player.getId());
        list.forEach(game -> game.setHours_game(playerStorage.getTimeGameOfPlayerOneGame(player.getId(),game.getId())));
        return list;
    }
}
