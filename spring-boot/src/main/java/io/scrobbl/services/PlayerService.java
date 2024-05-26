package io.scrobbl.services;

import io.scrobbl.models.Player;
import io.scrobbl.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public void save(final Player player){
        playerRepository.save(player);
    }

    public Player findFirstByName(final String name){
        return playerRepository.findFirstByName(name);
    }

    public void deleteAll(){
        playerRepository.deleteAll();
    }
}
