package com.example.sakiladb.controllers;

import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {
    private final ActorRepo actorRepo;

    @Autowired
    public ActorController(ActorRepo actorRepo){
        this.actorRepo = actorRepo;
    }

    @GetMapping("/actors")
    public List<ActorResponse> listActors(@RequestParam(required = false) Optional<String> name) {
        return name
                .map(actorRepo::findByFullNameContainingIgnoreCase)
                .orElseGet(actorRepo::findAll)
                .stream()
                .map(ActorResponse :: from)
                .toList();

    }
}
