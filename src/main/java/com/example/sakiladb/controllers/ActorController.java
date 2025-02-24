package com.example.sakiladb.controllers;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ActorController {
    private ActorRepo actorRepo;

    @Autowired
    public ActorController(ActorRepo actorRepo){
        this.actorRepo = actorRepo;
    }

    @GetMapping("/actors")
    public List<Actor> listActors() {
        return actorRepo.findAll();
    }
}
