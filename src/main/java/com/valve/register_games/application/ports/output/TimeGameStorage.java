package com.valve.register_games.application.ports.output;

import com.valve.register_games.domain.models.TimeGame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TimeGameStorage {
    List<TimeGame> getAllTimesGame();
    TimeGame saveTimeGame(TimeGame timeGame);
    TimeGame editTimeGame(TimeGame timeGame);
    String deleteTimeGame(TimeGame timeGame);

    TimeGame findTimeGameById(long id);
}
