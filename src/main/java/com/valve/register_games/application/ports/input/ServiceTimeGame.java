package com.valve.register_games.application.ports.input;

import com.valve.register_games.domain.models.TimeGame;
import com.valve.register_games.infrastructure.dtos.TimeGameDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceTimeGame {
    String saveTimeGame(TimeGameDTO player);
    List<TimeGame> getAll();
    String deleteTimeGame(long idTimeGame);

    String editTimeGame(TimeGame timeGame);
}
