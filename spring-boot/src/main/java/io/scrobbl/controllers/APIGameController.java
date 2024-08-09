package io.scrobbl.controllers;

import io.scrobbl.lastfm.User;
import io.scrobbl.models.Game;
import io.scrobbl.models.Player;
import io.scrobbl.services.GameService;
import io.scrobbl.services.PlayerService;
import io.scrobbl.util.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIGameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    public static final String URL_BASE = "/api/v1";

    @PutMapping(URL_BASE + "/game/{gameid}/{username}")
    public void addPlayer(@PathVariable("gameid") final long id, @PathVariable( "username" ) final String name) {
        Game g = gameService.findById(id);
        Player p = new Player(name);
        g.addPlayer(p);
        gameService.save(g);
    }

    @PutMapping(URL_BASE + "/game")
    public long makeGame(){
        Game g = new Game();
        return gameService.save(g);
    }

    @GetMapping(URL_BASE + "/lastfm/{username}")
    public User getUser(@PathVariable("username") final String name) {
        return User.getInfo(name, System.getenv("APIKEY"));
    }

    @GetMapping(URL_BASE + "/game/{gameid}/overlap")
    public String getOverlap(@PathVariable("gameid") final long id, @RequestParam(name = "recalc") boolean recalc){
        Game g = gameService.findById(id);
        if(recalc){
            g.calcOverlap(1);
        }
        return PrintUtils.toCSV(g.getOverlap());
    }






    

}
