package io.scrobbl.repositories;

import io.scrobbl.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository repository;

    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test
    void test(){
        Player p = new Player("Pingas");
        ArrayList<String> albums = new ArrayList<>();
        albums.add("hiiii");
        p.setKnownAlbums(albums);
        repository.save(p);
        Player p2 = repository.findFirstByName("Pingas");
        assertEquals(p2.getName(), p.getName());
        assertEquals("hiiii", p2.getKnownAlbums().get(0));

    }
}
