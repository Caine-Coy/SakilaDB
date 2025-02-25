package com.example.sakiladb.controllers;

import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class ActorController {
    private final ActorRepo actorRepo;
    private final FilmRepo filmRepo;

    @Autowired
    public ActorController(ActorRepo actorRepo, FilmRepo filmRepo){
        this.actorRepo = actorRepo;
        this.filmRepo = filmRepo;
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
    @GetMapping("/actors/{id}")
    public Actor getActor(@PathVariable Short id){
        return actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Actor ID Not Found"));
    }

    @PostMapping("/actors")
    public ActorResponse createActor(@RequestBody ActorRequest data){
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        final var films = data.getFilmIds()
                .stream()
                .map(filmId -> filmRepo.findById(filmId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists With That ID")))
                .toList();
        actor.setFilms(films);
        final var savedActor = actorRepo.save(actor);
        final var newActor = actorRepo.findById(savedActor.getId()).orElseThrow(() -> new RuntimeException("Expected created actor to exist!"));
        return ActorResponse.from(newActor);
    }

    @PatchMapping("/actors")
    public ActorResponse updateActor(@RequestBody ActorRequest data){
        Actor actor = actorRepo.findById(data.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Actor Exists with that id"));
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        final var films = data.getFilmIds()
                .stream()
                .map(filmId -> filmRepo.findById(filmId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists With That ID")))
                .toList();
        actor.setFilms(new ArrayList<>(films));
        final var savedActor = actorRepo.save(actor);
        final var newActor = actorRepo.findById(savedActor.getId()).orElseThrow(() -> new RuntimeException("Expected created actor to exist!"));
        return ActorResponse.from(newActor);
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable Short id){
        Actor actor = actorRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Actor Exists with that id"));
        actorRepo.delete(actor);
    }
}
