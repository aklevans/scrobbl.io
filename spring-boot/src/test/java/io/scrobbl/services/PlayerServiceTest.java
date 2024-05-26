package io.scrobbl.services;

import io.scrobbl.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService service;

    @BeforeEach
    void setUp(){
        service.deleteAll();
    }

    @Test
    void test(){
        Player p = new Player("Pingas");
        service.save(p);
        Player p2 = service.findFirstByName("Pingas");
        assertEquals(p2.getName(), p.getName());

    }
}
