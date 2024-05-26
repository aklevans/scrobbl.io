package io.scrobbl.repositories;

import io.scrobbl.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findFirstByName(String name);

    Player findById(long id);
}