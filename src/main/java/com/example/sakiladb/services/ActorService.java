package com.example.sakiladb.services;

import com.example.sakiladb.dto.ValidationGroup;
import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {
    private final ActorRepo actorRepo;
    private final FilmRepo filmRepo;

    public ActorService(ActorRepo actorRepo, FilmRepo filmRepo){
        this.actorRepo = actorRepo;
        this.filmRepo = filmRepo;
    }

    public List<Actor> listActors(){
        return actorRepo.findAll();
    }


    public Actor createActor(String firstName, String lastName,List<Short> filmIds){
        final var actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        final var films = filmIds
                .stream()
                .map(filmId -> filmRepo.findById(filmId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists With That ID")))
                .toList();
        actor.setFilms(films);
        final var savedActor = actorRepo.save(actor);
        final var newActor = actorRepo.findById(savedActor.getId()).orElseThrow(() -> new RuntimeException("Expected created actor to exist!"));
        return newActor;
    }

    public Actor updateActor(Short id, String firstName, String lastName,List<Short> filmIds){
        Actor actor = actorRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Actor Exists with that id"));
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        final var films = filmIds
                .stream()
                .map(filmId -> filmRepo.findById(filmId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists With That ID")))
                .toList();
        actor.setFilms(new ArrayList<>(films));
        final var savedActor = actorRepo.save(actor);
        return actorRepo.findById(savedActor.getId()).orElseThrow(() -> new RuntimeException("Expected created actor to exist!"));
    }

    public List<Actor> findByFullNameContainingIgnoreCase(String s) {
        return actorRepo.findByFullNameContainingIgnoreCase(s);
    }

    public Actor getActor(Short id) {
        return actorRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteActor(short id){
        Actor actor = actorRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Actor Exists with that id"));
        actorRepo.delete(actor);
    }
}
