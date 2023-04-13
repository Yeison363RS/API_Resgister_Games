package com.valve.register_games.domain.services;

import com.valve.register_games.application.ports.input.ServicePlayer;
import com.valve.register_games.application.ports.output.PlayerStorage;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImplServicePlayer implements ServicePlayer {

    @Autowired
    private PlayerStorage playerStorage;


    @Override
    public String savePlayer(Player player) {
        return playerStorage.savePlayer(player);
    }

    @Override
    public List<Player> getTopPlayersForGame(int numberTops, Game game) {
        /*validar que el juego exista o buscarlo por alguno de los atributos para obtener un objeto
        TO DO..*/
        List<Player> list=playerStorage.getTopPlayersOneGame(numberTops,game.getId());
        list.forEach(player -> player.setHours_game(getTimeGameOfPlayerOneGame(player.getId(),game.getId())));
        return list;
    }

    @Override
    public int getTimeGameOfPlayerOneGame(long idPlayer, long id_Game) {
        return playerStorage.getTimeGameOfPlayerOneGame(idPlayer,id_Game);
    }

    @Override
    public List<Player> getTopPlayersAllGames(int numberTops) {
        List<Player> list=playerStorage.getTopPlayersAllGames(numberTops);
        list.forEach(player -> player.setHours_game(getTotalTimeGameOnePlayer(player.getId())));
        return list;
    }

    private int getTotalTimeGameOnePlayer(long idPlayer) {
        return playerStorage.getTotalTimeGameOnePlayer(idPlayer);
    }
}
