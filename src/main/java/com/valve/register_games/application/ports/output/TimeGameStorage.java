package com.valve.register_games.application.ports.output;

import com.valve.register_games.domain.models.TimeGame;
import org.springframework.stereotype.Service;


@Service
public interface TimeGameStorage {
    TimeGame saveTimeGame(TimeGame timeGame);
    TimeGame editTimeGame(TimeGame timeGame);
    String deleteTimeGame(TimeGame timeGame);

    TimeGame findTimeGameById(long id);
}
