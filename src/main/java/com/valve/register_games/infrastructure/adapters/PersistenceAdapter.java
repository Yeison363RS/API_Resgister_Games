package com.valve.register_games.infrastructure.adapters;

import com.valve.register_games.application.ports.output.GameStorage;
import com.valve.register_games.application.ports.output.PlayerStorage;
import com.valve.register_games.application.ports.output.TimeGameStorage;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import com.valve.register_games.domain.models.TimeGame;
import com.valve.register_games.infrastructure.persistence.entities.GameEntity;
import com.valve.register_games.infrastructure.persistence.entities.PlayerEntity;
import com.valve.register_games.infrastructure.persistence.entities.TimeGameEntity;
import com.valve.register_games.infrastructure.persistence.repositories.GameRepository;
import com.valve.register_games.infrastructure.persistence.repositories.PlayerRepository;
import com.valve.register_games.infrastructure.persistence.repositories.TimeGameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersistenceAdapter implements GameStorage, PlayerStorage, TimeGameStorage {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TimeGameRepository timeGameRepository;


    @Override
    public List<Game> getTopGamesByOnePlayer(int nTops,long idPlayer) {
        List<Game> listGames= new ArrayList<>();
        gameRepository.getTopGamesByOnePlayer(nTops,idPlayer)
                .iterator()
                .forEachRemaining(game->listGames.add(modelMapper.map(game,Game.class)));
        return listGames;
    }

    @Override
    public Game findGameById(long id) {
        return modelMapper.map(gameRepository.findById(id).get(),Game.class);
    }

    @Override
    public Player findPlayerById(long id) {
        return modelMapper.map(playerRepository.findById(id).get(),Player.class);
    }

    @Override
    public int getTotalTimeGameOnePlayer(long idPlayer) {
        return playerRepository.getTotalTimeGameOnePlayer(idPlayer);
    }

    @Override
    public String saveGame(Game game) {
        gameRepository.save(modelMapper.map(game, GameEntity.class));
        return"se ha registrado un juego";
    }

    @Override
    public List<Player> getTopPlayersAllGames(int numberTops) {
        List<Player> listPlayers= new ArrayList<>();
        playerRepository.getTopPlayerAllGames(numberTops)
                .iterator()
                .forEachRemaining(player->listPlayers.add(modelMapper.map(player,Player.class)));
        return listPlayers;
    }

    @Override
    public int getTimeGameOfPlayerOneGame(long idPlayer, long id_Game) {
        return playerRepository.getTimeGameOfPlayerOneGame(idPlayer,id_Game);
    }

    @Override
    public List<Player> getTopPlayersOneGame(int nTops, long idGame) {
        List<Player> listGames= new ArrayList<>();
        playerRepository.getTopPlayerByOneGame(idGame,nTops)
                .iterator()
                .forEachRemaining(player->listGames.add(modelMapper.map(player,Player.class)));
        return listGames;
    }

    @Override
    public String savePlayer(Player player) {
        playerRepository.save(modelMapper.map(player,PlayerEntity.class));
        return"se ha registrado un jugador";
    }

    @Override
    public List<TimeGame> getAllTimesGame() {
        return null;
    }

    @Override
    public String saveTimeGame(TimeGame timeGame) {
        timeGameRepository.save(modelMapper.map(timeGame, TimeGameEntity.class));
        return"se ha registrado un nuevo tiempo de juego";
    }

    @Override
    public String deleteTimeGame(TimeGame timeGame) {
        timeGameRepository.delete(modelMapper.map(timeGame,TimeGameEntity.class));
        return"se ha eliminado un tiempo de juego";
    }

    @Override
    public TimeGame findTimeGameById(long id) {
        return modelMapper.map(timeGameRepository.findById(id).get(),TimeGame.class);
    }
}
