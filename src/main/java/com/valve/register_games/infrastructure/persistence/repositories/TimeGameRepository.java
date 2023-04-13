package com.valve.register_games.infrastructure.persistence.repositories;

import com.valve.register_games.infrastructure.persistence.entities.TimeGameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeGameRepository extends CrudRepository<TimeGameEntity,Long> {
}
