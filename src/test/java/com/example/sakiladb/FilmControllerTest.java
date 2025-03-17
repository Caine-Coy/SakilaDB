package com.example.sakiladb;

import com.example.sakiladb.controllers.ActorController;
import com.example.sakiladb.controllers.FilmController;
import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.request.FilmRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.dto.response.FilmResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.services.ActorService;
import com.example.sakiladb.services.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


public class FilmControllerTest {

    static List<Film> films = List.of(
            new Film()
    );

    static FilmService service = mock(FilmService.class);
    static FilmController controller = new FilmController(service);

    @BeforeAll
    public static void setup() {
        for (var film : films) {
            doReturn(film).when(service).findById(film.getId());
        }
    }

    @Test
    public void getActorReturnsActorResponseForValidActorId() {
        //test actor response

        Film film = films.getFirst();
        doReturn(film).when(service).findById(film.getId());

        final var expectedResponse = FilmResponse.from(film);
        final var actualResponse = controller.listFilms(film.getId());

        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getTitle(), actualResponse.getTitle());
    }

    @Test
    public void createActorReturnsActorResponse() {
        Film film = films.getFirst();
        doReturn(film).when(service).createFilm(film.getTitle(), film.getLanguageID(), film.getOriginalLanguageId());

        final var expectedResponse = FilmResponse.from(film);
        final var actualResponse = controller.createFilm(new FilmRequest(film.getId(), film.getTitle(), film.getLanguageID(), film.getOriginalLanguageId(), film.getReleaseYear(), film.getDesc()));

        Assertions.assertEquals(expectedResponse.getTitle(), actualResponse.getTitle());
        //Not sure use save
    }

    @Test
    public void updateActorReturnsActorResponse() {
        Film film = films.getFirst();
        doReturn(film).when(service).updateFilm(film.getId(), film.getTitle(), film.getLanguageID(), film.getOriginalLanguageId(), film.getReleaseYear(), film.getDesc());

        final var expectedResponse = FilmResponse.from(film);
        final var actualResponse = controller.updateFilm(film.getId(), new FilmRequest(film.getId(), film.getTitle(), film.getLanguageID(), film.getOriginalLanguageId(), film.getReleaseYear(), film.getDesc()));

        Assertions.assertEquals(expectedResponse.getTitle(), actualResponse.getTitle());
    }
    @Test
    public void testListPartialFilms(){
        controller.listPartialFilms(Optional.of("TEST"));
    }

    @Test
    public void deleteActor(){
        Film film = films.getFirst();
        controller.deleteFilm(film.getId());
    }


}
