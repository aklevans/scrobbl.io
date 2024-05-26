package io.scrobbl.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    public static final String URL_BASE = "/api/v1";

    @PutMapping(URL_BASE + "/player/{username}")
    public String addPlayer() {
        return "index";
    }
    

}
