package io.scrobbl.services;

import io.scrobbl.models.Game;
import io.scrobbl.models.Player;
import io.scrobbl.repositories.GameRepository;
import io.scrobbl.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository){
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public long save(final Game game){
        for(Player p : game.getPlayers()){
            playerRepository.save(p);
        }

        long id =  gameRepository.save(game).getId();

        return id;
    }

    public Game findById(long id){
        return gameRepository.findById(id);
    }

    public void deleteAll(){
        gameRepository.deleteAll();
    }
}
