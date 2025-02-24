package com.example.sakiladb.controllers;

import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
public class FilmController {

    private FilmRepo filmRepo;

    @Autowired
    public FilmController(FilmRepo filmRepo){
        this.filmRepo = filmRepo;
    }

    @GetMapping("/films")
    public List<Film> listFilms() {
        return filmRepo.findAll();
    }
    @GetMapping("/films/{id}")
    public Film listFilms(@PathVariable Short id) {
        return filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Film ID Not Found"));
    }
}
