package com.example.sakiladb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {
    @GetMapping("/greeting")
    public String getGreeting(){
        return "howdy";
    }
    @GetMapping("/film")
    public String getFilm(@RequestParam int number){
        return "howdy";
    }

}
