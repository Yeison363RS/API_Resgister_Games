package com.valve.register_games.application.ports.output;

import com.valve.register_games.domain.models.Game;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GameStorage {
    List<Game> getTopGamesByOnePlayer(int nTops,long idPlayer);
    Game findGameById(long id);
    String saveGame(Game game);
}
