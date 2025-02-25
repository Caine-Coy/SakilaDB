package com.example.sakiladb.controllers;

import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.request.FilmRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.dto.response.FilmResponse;
import com.example.sakiladb.dto.response.PartialFilmResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {

    private final FilmRepo filmRepo;

    @Autowired
    public FilmController(FilmRepo filmRepo){
        this.filmRepo = filmRepo;
    }
    @GetMapping("/films/{id}")
    public Film listFilms(@PathVariable Short id) {
        return filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Film ID Not Found"));
    }
    @GetMapping("/films")
    public List<PartialFilmResponse> listPartialFilms(@RequestParam(required = false) Optional<String> title) {
        return title
                .map(filmRepo::findByTitleContainingIgnoreCase)
                .orElseGet(filmRepo::findAll)
                .stream()
                .map(PartialFilmResponse :: from)
                .toList();
    }

    @PostMapping("/films")
    public FilmResponse createFilm(@RequestBody FilmRequest data){
        final Film film = new Film();
        film.setTitle(data.getTitle());
        //Todo Fix
        film.setLanguageID((byte) 1);
        film.setOriginalLanguageId(data.getOriginalLanguageID());

        final var savedFilm = filmRepo.save(film);
        final var newFilm = filmRepo.findById(savedFilm.getId()).orElseThrow(() -> new RuntimeException("Expected created film to exist!"));
        return FilmResponse.from(newFilm);
    }
/*
    @PatchMapping("/films")
    public ActorResponse updateActor(@RequestBody ActorRequest data){
        Film film = filmRepo.findById(data.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Actor Exists with that id"));
        film.data

        final var savedActor = actorRepo.save(actor);
        final var newActor = actorRepo.findById(savedActor.getId()).orElseThrow(() -> new RuntimeException("Expected created actor to exist!"));
        return ActorResponse.from(newActor);
    }
*/
    @DeleteMapping("/films/{id}")
    public void deleteActor(@PathVariable Short id){
        Film film = filmRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists with that id"));
        filmRepo.delete(film);
    }
}

