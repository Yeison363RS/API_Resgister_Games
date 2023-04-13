package com.valve.register_games.application.ports.input;

import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceGame {
    String saveGame(Game game);
    List<Game> getTopGamesForPlayer(int numberTops,Player player);
}
