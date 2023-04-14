package com.valve.register_games.application.ports.output;

import com.valve.register_games.domain.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlayerStorage {
    List<Player> getTopPlayersAllGames(int numberTops);
    int getTimeGameOfPlayerOneGame(long idPlayer, long id_Game);
    List<Player> getTopPlayersOneGame(int numberTops,long idPlayer);
    Player savePlayer(Player player);
    Player findPlayerById(long id);

    int getTotalTimeGameOnePlayer(long idPlayer);
}
