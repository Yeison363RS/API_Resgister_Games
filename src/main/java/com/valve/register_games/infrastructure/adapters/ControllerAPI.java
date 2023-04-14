package com.valve.register_games.infrastructure.adapters;

import com.valve.register_games.application.ports.input.ServiceGame;
import com.valve.register_games.application.ports.input.ServicePlayer;
import com.valve.register_games.application.ports.input.ServiceTimeGame;
import com.valve.register_games.domain.models.Game;
import com.valve.register_games.domain.models.Player;
import com.valve.register_games.domain.models.TimeGame;
import com.valve.register_games.infrastructure.dtos.GameDTO;
import com.valve.register_games.infrastructure.dtos.PlayerDTO;
import com.valve.register_games.infrastructure.dtos.TimeGameDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ControllerAPI {

    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ServicePlayer servicePlayer;
    @Autowired
    private ServiceGame serviceGame;
    @Autowired
    private ServiceTimeGame serviceTimeGame;


    @GetMapping("/topPlayersOneGame/{idGame}/{nTops}")
    public ResponseEntity<List<PlayerDTO>> getTopPlayersByOneGame(@PathVariable(value = "idGame",required = true) long idGame,
                                                                  @PathVariable(value = "nTops",required = true) long nTops){
        Game game= new Game();
        game.setId(idGame);
        List<PlayerDTO> list = new ArrayList<>();
        servicePlayer.getTopPlayersForGame((int)nTops,game).forEach(player -> list.add(modelMapper.map(player,PlayerDTO.class)));
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/topGamesOnePlayer/{idPlayer}/{nTops}")
    public ResponseEntity<List<GameDTO>> getTopGamesByOnePlayer(@PathVariable(value = "idPlayer",required = true) long idPlayer,
                                                @PathVariable(value = "nTops",required = true) int nTops){
        Player player= new Player();
        player.setId(idPlayer);
        List<GameDTO> list = new ArrayList<>();
        serviceGame.getTopGamesForPlayer(nTops,player).forEach(game -> list.add(modelMapper.map(game,GameDTO.class)));
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/topPlayersAllGames/{nTops}")
    public ResponseEntity<List<PlayerDTO>> getTopPlayersAllGames(@PathVariable(value = "nTops",required = true) int nTops){
        List<PlayerDTO> list = new ArrayList<>();
        servicePlayer.getTopPlayersAllGames(nTops).forEach(player -> list.add(modelMapper.map(player,PlayerDTO.class)));
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/savePlayer")
    public ResponseEntity<?> savePlayer(@RequestBody PlayerDTO player){
        Object playerSaved=servicePlayer.savePlayer(modelMapper.map(player, Player.class));
        return new ResponseEntity<>(playerSaved.getClass()!=String.class?modelMapper.map(playerSaved,PlayerDTO.class):playerSaved,HttpStatus.OK);
    }

    @PostMapping("/saveGame")
    public ResponseEntity<?> saveGame(@RequestBody GameDTO game){
        Object gameSaved=serviceGame.saveGame(modelMapper.map(game, Game.class));
        return new ResponseEntity<>(gameSaved.getClass()!=String.class?modelMapper.map(gameSaved,GameDTO.class):gameSaved,HttpStatus.OK);
    }

    @PostMapping("/saveTime")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?>  saveTimeGame(@RequestBody TimeGameDTO timeGame){
        Object timeSaved=serviceTimeGame.saveTimeGame(timeGame);
        return new ResponseEntity<>(timeSaved.getClass()!=String.class?modelMapper.map(timeSaved,TimeGameDTO.class):timeSaved,HttpStatus.OK);
    }

    @PutMapping("/editTime")
    @ResponseStatus(code = HttpStatus.OK)
    public  ResponseEntity<?>  editTimeGameForOnePlayerInOneGame(@RequestBody TimeGameDTO timeGame){
        System.out.println("timegameid :"+timeGame.getId_time_game());
        Object timeSaved = serviceTimeGame.editTimeGame(modelMapper.map(timeGame, TimeGame.class));
        return new ResponseEntity<>(timeSaved.getClass()!=String.class?modelMapper.map(timeSaved,TimeGameDTO.class):timeSaved,HttpStatus.OK);
    }

    @DeleteMapping("/deleteTime/{idTimeGame}")
    public ResponseEntity<String>  deleteOneTimeGameForOnePlayerInOneGame(@PathVariable(value = "idTimeGame",required = true)  long idTimeGame){
        return new ResponseEntity<>(serviceTimeGame.deleteTimeGame(idTimeGame),HttpStatus.OK);
    }
}
