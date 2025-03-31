package com.example.sakiladb;

import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repos.FilmRepo;
import com.example.sakiladb.services.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    private FilmRepo filmRepo;

    @InjectMocks
    private FilmService service;

    @Test
    public void listFilmsReturnsAllFilms() {
        List<Film> films = List.of(new Film());
        when(filmRepo.findAll()).thenReturn(films);

        List<Film> result = service.listFilms();

        assertEquals(films, result);
        verify(filmRepo, times(1)).findAll();
    }

    @Test
    public void findByIdReturnsFilmForValidId() {
        Film film = new Film();
        when(filmRepo.findById((short) 1)).thenReturn(Optional.of(film));

        Film result = service.findById((short) 1);

        assertEquals(film, result);
        verify(filmRepo, times(1)).findById((short) 1);
    }

    @Test
    public void findByIdThrowsExceptionForInvalidId() {
        when(filmRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.findById((short) 1));
    }

    @Test
    public void findByTitleContainingIgnoreCaseReturnsFilms() {
        List<Film> films = List.of(new Film());
        when(filmRepo.findByTitleContainingIgnoreCase("Title")).thenReturn(films);

        List<Film> result = service.findByTitleContainingIgnoreCase("Title");

        assertEquals(films, result);
        verify(filmRepo, times(1)).findByTitleContainingIgnoreCase("Title");
    }

    @Test
    public void createFilmSuccessfully() {
        Film film = new Film();
        when(filmRepo.save(any(Film.class))).thenReturn(film);

        Film result = service.createFilm("Title", (byte) 1, (byte) 1);

        assertNotNull(result);
        verify(filmRepo, times(1)).save(any(Film.class));
    }

    @Test
    public void updateFilmSuccessfully() {
        Film film = new Film();
        film.setId((short) 1);
        when(filmRepo.findById((short) 1)).thenReturn(Optional.of(film));
        when(filmRepo.save(any(Film.class))).thenReturn(film);
        when(filmRepo.findById((short) 1)).thenReturn(Optional.of(film));

        Film result = service.updateFilm((short) 1, "Title", (byte) 1, (byte) 1, Year.of(2023), "Description");

        assertNotNull(result);
        verify(filmRepo, times(1)).save(any(Film.class));
    }

    @Test
    public void updateFilmThrowsExceptionForInvalidId() {
        when(filmRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.updateFilm((short) 1, "Title", (byte) 1, (byte) 1, Year.of(2023), "Description"));
    }

    @Test
    public void deleteFilmSuccessfully() {
        Film film = new Film();
        when(filmRepo.findById((short) 1)).thenReturn(Optional.of(film));

        service.deleteFilm((short) 1);

        verify(filmRepo, times(1)).delete(film);
    }

    @Test
    public void deleteFilmThrowsExceptionForInvalidId() {
        when(filmRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.deleteFilm((short) 1));
    }
}
