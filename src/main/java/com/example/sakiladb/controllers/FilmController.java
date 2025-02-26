package com.example.sakiladb.controllers;

import com.example.sakiladb.dto.ValidationGroup;
import com.example.sakiladb.dto.ValidationGroup.Create;
import com.example.sakiladb.dto.ValidationGroup.Update;
import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.request.FilmRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.dto.response.FilmResponse;
import com.example.sakiladb.dto.response.PartialFilmResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.entities.Language;
import com.example.sakiladb.repos.FilmRepo;
import com.example.sakiladb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {

    FilmService filmService;
    @Autowired
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }
    @GetMapping("/{id}")
    public Film listFilms(@PathVariable Short id) {
        return filmService.findById(id);

    }
    @GetMapping
    public List<PartialFilmResponse> listPartialFilms(@RequestParam(required = false) Optional<String> title) {
        return title
                .map(filmService::findByTitleContainingIgnoreCase)
                .orElseGet(filmService::listFilms)
                .stream()
                .map(PartialFilmResponse :: from)
                .toList();
    }

    @PostMapping
    public FilmResponse createFilm(@Validated(Create.class) @RequestBody FilmRequest data){
        final Film film = filmService.createFilm(
                data.getTitle(),
                data.getLanguageId(),
                data.getOriginalLanguageID()
        );
        final var newFilm = filmService.findById(film.getId());
        return FilmResponse.from(newFilm);
    }

    @PatchMapping
    public FilmResponse updateFilm(@Validated(Update.class) @RequestBody FilmRequest data){
        Film film = filmService.updateFilm(
                data.getId(),
                data.getTitle(),
                data.getLanguageId(),
                data.getOriginalLanguageID()
        );
        return FilmResponse.from(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Short id){
        filmService.deleteFilm(id);
    }
}

