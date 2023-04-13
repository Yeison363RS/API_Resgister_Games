package com.valve.register_games.infrastructure.persistence.repositories;

import com.valve.register_games.infrastructure.persistence.entities.PlayerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity,Long> {
    @Query(value = "SELECT * "+
            "FROM players "+
            "JOIN "+
            "(SELECT times_game.id_game,times_game.id_player, sum(times_game.number_hours) as hours_game "+
                            "FROM times_game "+
                            "where times_game.id_game = :idGame "+
                            "GROUP by times_game.id_game, times_game.id_player) AS results "+
            "ON results.id_player = players.id_player "+
                    "ORDER BY hours_game DESC LIMIT :topN " +
            ";", nativeQuery = true)
    List<PlayerEntity> getTopPlayerByOneGame(@Param("idGame") long idGame,@Param("topN") int nTops);

    @Query(value = "SELECT * " +
            "FROM players " +
            "JOIN " +
            "(SELECT times_game.id_player,sum(times_game.number_hours) as hours_game " +
            "FROM times_game " +
            "GROUP by times_game.id_player) AS results " +
            "ON results.id_player = players.id_player " +
            "ORDER BY hours_game DESC LIMIT :topN ;",nativeQuery = true)
    Iterable<PlayerEntity> getTopPlayerAllGames(@Param("topN") int nTops);

    @Query(value = "SELECT sum(times_game.number_hours) as hours_game " +
            "FROM times_game " +
            " WHERE times_game.id_player = :idPlayer " +
            " AND times_game.id_game = :idGame ; ",nativeQuery = true)
    int getTimeGameOfPlayerOneGame(@Param("idPlayer") long idPlayer,@Param("idGame")  long id_Game);
    @Query(value = "SELECT sum(times_game.number_hours) as hours_game " +
            "FROM times_game " +
            " WHERE times_game.id_player = :idPlayer ; ",nativeQuery = true)
    int getTotalTimeGameOnePlayer(long idPlayer);
}
