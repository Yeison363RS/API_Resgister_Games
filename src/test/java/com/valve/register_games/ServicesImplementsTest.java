package com.valve.register_games;

import com.valve.register_games.application.ports.input.ServiceGame;
import com.valve.register_games.application.ports.input.ServicePlayer;
import com.valve.register_games.application.ports.input.ServiceTimeGame;
import com.valve.register_games.application.ports.output.GameStorage;
import com.valve.register_games.application.ports.output.PlayerStorage;
import com.valve.register_games.application.ports.output.TimeGameStorage;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import com.valve.register_games.domain.models.TimeGame;
import com.valve.register_games.domain.services.ImplTimeService;
import com.valve.register_games.infrastructure.dtos.TimeGameDTO;
import com.valve.register_games.infrastructure.persistence.entities.TimeGameEntity;
import com.valve.register_games.infrastructure.persistence.repositories.TimeGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:db_test.properties")
@Sql("/valuesForTest.sql")
public class ServicesImplementsTest {
	@Autowired
	ServiceGame serviceGame;
	@Autowired
	ServicePlayer servicePlayer;

	@Autowired
	ServiceTimeGame serviceTimeGame;

	@InjectMocks
	ImplTimeService implTimeService;
	@Mock
	TimeGameStorage timeGameStoragMock;
	@Mock
	GameStorage gameStorageMock;
	@Mock
	PlayerStorage playerStorageMock;

	@Before
	public void previusTest(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addExistIDPlayerTest() {
		Player player=new Player();
		player.setId(3);
		player.setName("julian");
		log.info(servicePlayer.savePlayer(player).toString());
		Assertions.assertInstanceOf(String.class,servicePlayer.savePlayer(player));
	}

	@Test
	void addNewPlayerTest() {
		Player player=new Player();
		player.setBirthdate(Calendar.getInstance().getTime());
		player.setId(80);
		player.setName("julian");
		player.setEmail("julianRamirez@gmail.com");
		player.setGender("Masculino");
		Object resAnswer=servicePlayer.savePlayer(player);
		Assertions.assertInstanceOf(Player.class,resAnswer);
		Player p = (Player)resAnswer;
		Assertions.assertEquals(p.getEmail(),"julianRamirez@gmail.com");
	}

	@Test
	void addExistIDGameTest(){
		Game game=new Game();
		game.setId(2);
		game.setName("Doom");
		game.setDescription("Game of Monsters space locate on Mars");
		log.info(serviceGame.saveGame(game).toString());
		Assertions.assertInstanceOf(String.class,serviceGame.saveGame(game));
	}

	@Test
	void addNewGameTest() {
		Game game=new Game();
		game.setName("Doom");
		game.setId(30);
		game.setDescription("Game of Monsters space locate on Mars");
		game.setDateRelease(Calendar.getInstance().getTime());
		Object resAnswer=serviceGame.saveGame(game);
		Assertions.assertInstanceOf(Game.class,resAnswer);
		Game gameSaved = (Game)resAnswer;
		Assertions.assertEquals(gameSaved.getDescription(),"Game of Monsters space locate on Mars");
	}
	@Test
	void searchExistIDTimeGame(@Autowired TimeGameRepository repository){
		TimeGameDTO timeGame=new TimeGameDTO();
		timeGame.setId_time_game(4);
		timeGame.setIdPlayer(1);
		timeGame.setIdGame(2);
		timeGame.setNumberHours(15);
		TimeGameEntity entity=repository.findById((long)4).orElse(null);
		Assertions.assertNotNull(entity);
	}
	@Test
	void addExistIDTimeGameTest(){
		TimeGameDTO timeGame=new TimeGameDTO();
		timeGame.setId_time_game(4);
		timeGame.setNumberHours(15);
		Game game= new Game();
		game.setId(2);
		Player player= new Player();
		player.setId(1);
		when(timeGameStoragMock.findTimeGameById(4)).thenReturn(new TimeGame());
		when(gameStorageMock.findGameById(2)).thenReturn(new Game());
		when(playerStorageMock.findPlayerById(1)).thenReturn(new Player());
		log.info(implTimeService.saveTimeGame(timeGame).toString());
		Assertions.assertInstanceOf(String.class,implTimeService.saveTimeGame(timeGame));
	}

	@Test
	void addNewTimeGameTest() {
		TimeGameDTO timeGame=new TimeGameDTO();
		timeGame.setId_time_game(4);
		timeGame.setIdGame(2);
		timeGame.setIdPlayer(2);
		timeGame.setNumberHours(15);

		Game game= new Game();
		game.setId(2);
		Player player= new Player();
		player.setId(2);

		when(timeGameStoragMock.findTimeGameById(anyLong())).thenReturn(null);
		when(timeGameStoragMock.saveTimeGame(any(TimeGame.class))).thenReturn(new TimeGame());
		when(gameStorageMock.findGameById(2)).thenReturn(new Game());
		when(playerStorageMock.findPlayerById(2)).thenReturn(new Player());

		Assertions.assertInstanceOf(TimeGame.class,implTimeService.saveTimeGame(timeGame));
	}

	@Test
	void testGetTopPlayerOneGameService(@Autowired GameStorage storageGame){
		Game game=storageGame.findGameById(2);
		List<Player> playersTop=servicePlayer.getTopPlayersForGame(10,game);
		Assertions.assertEquals(playersTop.size(),2);
		Assertions.assertEquals(playersTop.get(0).getId(),1);
		Assertions.assertEquals(playersTop.get(1).getId(),3);
	}

	@Test
	void testGetTopPlayerAllGamesService(){
		List<Player> playersTop=servicePlayer.getTopPlayersAllGames(10);
		Assertions.assertEquals(playersTop.size(),6);

		List<Long> orderRecceived=new ArrayList<>();
		playersTop.forEach(player -> orderRecceived.add(player.getId()));
		List<Long> orderExpected = Stream.of(4, 1, 6, 2, 5, 3).mapToLong(Long::valueOf)
				.boxed().
				collect(Collectors.toList());

		for (int i = 0; i < orderExpected.size(); i++) {
			Assertions.assertEquals(orderRecceived.get(i),orderExpected.get(i));
		}
	}

	@Test
	void testGetTopGamesOnePlayerService(@Autowired PlayerStorage playerStorage){
		Player player= playerStorage.findPlayerById(1);
		List<Game> gamesTop=serviceGame.getTopGamesForPlayer(10,player);
		Assertions.assertEquals(gamesTop.size(),3);

		List<Long> orderRecceived=new ArrayList<>();
		gamesTop.forEach(game -> orderRecceived.add(game.getId()));
		List<Long> orderExpected = Stream.of(2, 3, 1).mapToLong(Long::valueOf)
				.boxed().
				collect(Collectors.toList());
		for (int i = 0; i < orderExpected.size(); i++) {
			Assertions.assertEquals(orderRecceived.get(i),orderExpected.get(i));
		}
	}
	@Test
	void testDeleteTimeGameService(){
		String reponse=serviceTimeGame.deleteTimeGame(2);
		Assertions.assertTrue(reponse.contains("eliminado"));
	}
	@Test
	void testDeleteNotExitsTimeGameService(){
		String reponse=serviceTimeGame.deleteTimeGame(20);
		Assertions.assertTrue(reponse.contains("No existe"));
	}
	@Test
	void testEditTimeGameService(@Autowired TimeGameStorage storageTime){
		TimeGame timeGame=storageTime.findTimeGameById(3);
		int newQuantityHours=timeGame.getNumberHours()+6;
		timeGame.setNumberHours(newQuantityHours);
		Object timeGameUpdated=serviceTimeGame.editTimeGame(timeGame);
		Assertions.assertInstanceOf(TimeGame.class,timeGameUpdated);
		TimeGame timeGameModel=(TimeGame)timeGameUpdated;
		Assertions.assertEquals(timeGameModel.getNumberHours(),newQuantityHours);
	}
}
