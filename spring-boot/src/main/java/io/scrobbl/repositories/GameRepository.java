package io.scrobbl.repositories;

import io.scrobbl.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Game findById(long id);
}
