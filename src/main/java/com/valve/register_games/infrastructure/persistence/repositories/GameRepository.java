package com.valve.register_games.infrastructure.persistence.repositories;

import com.valve.register_games.infrastructure.persistence.entities.GameEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<GameEntity,Long> {
    @Query(value = "SELECT * " +
            "FROM games " +
            "JOIN " +
            "(SELECT times_game.id_game,sum(times_game.number_hours) as hours_game " +
            "FROM times_game " +
            "where times_game.id_player = :idPlayer " +
            "GROUP by times_game.id_game) AS results " +
            "ON results.id_game = games.id_game " +
            "ORDER BY hours_game DESC LIMIT :nTops ;",nativeQuery = true)
    Iterable<GameEntity> getTopGamesByOnePlayer(@Param("nTops")int nTops,@Param("idPlayer") long idPlayer);
}
