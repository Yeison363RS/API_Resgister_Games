package com.valve.register_games.domain.services;

import com.valve.register_games.application.ports.input.ServicePlayer;
import com.valve.register_games.application.ports.output.PlayerStorage;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImplServicePlayer implements ServicePlayer {

    @Autowired
    private PlayerStorage playerStorage;


    @Transactional
    @Override
    public Object savePlayer(Player player) {
        return playerStorage.findPlayerById(player.getId())!=null?"Este jugador ya existe":playerStorage.savePlayer(player);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Player> getTopPlayersForGame(int numberTops, Game game) {
        List<Player> list=playerStorage.getTopPlayersOneGame(numberTops,game.getId());
        list.forEach(player -> player.setHours_game(getTimeGameOfPlayerOneGame(player.getId(),game.getId())));
        return list;
    }
    @Transactional(readOnly = true)
    @Override
    public int getTimeGameOfPlayerOneGame(long idPlayer, long id_Game) {
        return playerStorage.getTimeGameOfPlayerOneGame(idPlayer,id_Game);
    }
    @Transactional(readOnly = true)
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
