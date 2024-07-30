package io.scrobbl.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void test(){
        Player p1 = new Player("alexkl3");
        Player p2 = new Player("th0gus");

        Game g = new Game();
        g.addPlayer(p1);
        g.addPlayer(p2);

        List<Player> players = g.getPlayers();
        assertEquals(2, players.size());

        assertEquals(50, players.get(0).getKnownAlbums().size());
        assertEquals(50, players.get(1).getKnownAlbums().size());

        List<String> overlap = g.getOverlap();
        //System.out.println(overlap);

    }
}
