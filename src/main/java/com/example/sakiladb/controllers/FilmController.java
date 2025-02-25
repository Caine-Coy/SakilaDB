package com.example.sakiladb.controllers;

import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.dto.response.PartialFilmResponse;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<PartialFilmResponse> listActors(@RequestParam(required = false) Optional<String> title) {
        return title
                .map(filmRepo::findByTitleContainingIgnoreCase)
                .orElseGet(filmRepo::findAll)
                .stream()
                .map(PartialFilmResponse :: from)
                .toList();
    }
}

