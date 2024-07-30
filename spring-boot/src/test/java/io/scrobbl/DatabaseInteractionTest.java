package io.scrobbl.services;

import io.scrobbl.models.Game;
import io.scrobbl.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DatabaseInteractionTest {
    @Autowired
    private GameService service;

    @BeforeEach
    void setUp(){
        service.deleteAll();
    }

    @Test
    void test(){
        Game g = new Game();
        Player p1 = new Player("player 1");
        Player p2 = new Player("player 2");

        List<String> known1 = new ArrayList<>();
        known1.add("album1");
        known1.add("album2");

        p1.setKnownAlbums(known1);

        List<String> known2 = new ArrayList<>();
        known2.add("album3");
        known2.add("album4");
        known2.add("album5");
        p2.setKnownAlbums(known2);

        g.addPlayer(p1);
        g.addPlayer(p2);

        long id = service.save(g);

        Game g2 = service.findById(id);
        List<Player> players = g2.getPlayers();
        assertEquals(2, players.size());
        assertEquals("player 1", players.get(0).getName());
        assertEquals(2, players.get(0).getKnownAlbums().size());
        assertEquals("album1", players.get(0).getKnownAlbums().get(0));
        assertEquals("album2", players.get(0).getKnownAlbums().get(1));
        assertEquals(3, players.get(1).getKnownAlbums().size());
        assertEquals("album3", players.get(1).getKnownAlbums().get(0));
        assertEquals("album4", players.get(1).getKnownAlbums().get(1));
        assertEquals("album5", players.get(1).getKnownAlbums().get(2));

    }
}
