package com.valve.register_games;

import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import com.valve.register_games.domain.models.TimeGame;
import com.valve.register_games.infrastructure.dtos.TimeGameDTO;
import com.valve.register_games.infrastructure.persistence.entities.GameEntity;
import com.valve.register_games.infrastructure.persistence.entities.PlayerEntity;
import com.valve.register_games.infrastructure.persistence.entities.TimeGameEntity;
import com.valve.register_games.infrastructure.persistence.repositories.TimeGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Time;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:db_test.properties")
@Sql("/valuesForTest.sql")
public class RepositiriesTest {

    @Before
    void previusTest(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveTimeGameTest(@Autowired TimeGameRepository repository){
        TimeGameEntity timeGame=new TimeGameEntity();
        timeGame.setId_time_game((long)10);
        timeGame.setNumberHours(65);

        GameEntity game= new GameEntity();
        game.setId((long)2);

        PlayerEntity player= new PlayerEntity();
        player.setId((long)1);

        timeGame.setGame(game);
        timeGame.setPlayer(player);
        TimeGameEntity timeEntity = repository.save(timeGame);
        Assertions.assertInstanceOf(TimeGameEntity.class,timeEntity);
        Assertions.assertEquals(timeEntity.getNumberHours(),65);
    }
}
