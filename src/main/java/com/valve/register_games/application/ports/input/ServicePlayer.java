package com.valve.register_games.application.ports.input;

import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServicePlayer {
    Object savePlayer(Player player);
    List<Player> getTopPlayersForGame(int numberTops,Game game);
    int getTimeGameOfPlayerOneGame(long idPlayer,long id_Game);
    List<Player> getTopPlayersAllGames(int numberTops);
}
