package com.example.sakiladb.services;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;

@Service
public class FilmService {

    private final FilmRepo filmRepo;

    public FilmService(ActorRepo actorRepo, FilmRepo filmRepo){
        this.filmRepo = filmRepo;
    }

    public List<Film> listFilms(){
        return filmRepo.findAll();
    }

    public Film findById(short id){
        return filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Film ID Not Found"));
    }

    public List<Film> findByTitleContainingIgnoreCase(String s) {
        return filmRepo.findByTitleContainingIgnoreCase(s);
    }

    public Film createFilm(String title, Byte languageId, Byte originalLanguageID) {
        Film film = new Film();
        film.setTitle(title);
        film.setLanguageID((byte)1);
        film.setOriginalLanguageId((byte)1);
        filmRepo.save(film);
        return film;
    }

    public Film updateFilm(Short id, String title, Byte languageId, Byte originalLanguageID, Year year, String description){
        Film film = filmRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists with that id"));
        film.setTitle(title);
        film.setLanguageID((byte) 1);
        film.setOriginalLanguageId((byte) 1);
        film.setReleaseYear(year);
        film.setDesc(description);
        final var savedFilm = filmRepo.save(film);
        final var newFilm = filmRepo.findById(savedFilm.getId()).orElseThrow(() -> new RuntimeException("Expected created film to exist!"));
        return newFilm;
    }
    public void deleteFilm(short Id){
        Film film = filmRepo.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Film Exists with that id"));
        filmRepo.delete(film);
    }
}
