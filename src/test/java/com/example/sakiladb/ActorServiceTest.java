package com.example.sakiladb;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import com.example.sakiladb.services.ActorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {

    @Mock
    private ActorRepo actorRepo;

    @Mock
    private FilmRepo filmRepo;

    @InjectMocks
    private ActorService service;

    @Test
    public void listActorsReturnsAllActors() {
        List<Actor> actors = List.of(new Actor());
        when(actorRepo.findAll()).thenReturn(actors);

        List<Actor> result = service.listActors();

        assertEquals(actors, result);
        verify(actorRepo, times(1)).findAll();
    }

    @Test
    public void createActorSuccessfully() {
        Actor actor = new Actor();
        actor.setId((short) 1);
        List<Short> filmIds = List.of((short) 1);
        Film film = new Film();
        when(filmRepo.findById((short) 1)).thenReturn(Optional.of(film));
        when(actorRepo.save(any(Actor.class))).thenReturn(actor);
        when(actorRepo.findById((short) 1)).thenReturn(Optional.of(actor));

        Actor result = service.createActor("John", "Doe", filmIds);

        assertNotNull(result);
        verify(filmRepo, times(1)).findById((short) 1);
        verify(actorRepo, times(1)).save(any(Actor.class));
    }

    @Test
    public void createActorThrowsExceptionForInvalidFilmId() {
        when(filmRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.createActor("John", "Doe", List.of((short) 1)));
    }

    @Test
    public void updateActorSuccessfully() {
        Actor actor = new Actor();
        actor.setId((short) 1);
        List<Short> filmIds = List.of((short) 1);
        Film film = new Film();
        when(actorRepo.findById((short) 1)).thenReturn(Optional.of(actor));
        when(filmRepo.findById((short) 1)).thenReturn(Optional.of(film));
        when(actorRepo.save(any(Actor.class))).thenReturn(actor);
        when(actorRepo.findById((short) 1)).thenReturn(Optional.of(actor));

        Actor result = service.updateActor((short) 1, "John", "Doe", filmIds);

        assertNotNull(result);
        verify(filmRepo, times(1)).findById((short) 1);
        verify(actorRepo, times(1)).save(any(Actor.class));
    }

    @Test
    public void updateActorThrowsExceptionForInvalidActorId() {
        when(actorRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.updateActor((short) 1, "John", "Doe", List.of((short) 1)));
    }

    @Test
    public void findByFullNameContainingIgnoreCaseReturnsActors() {
        List<Actor> actors = List.of(new Actor());
        when(actorRepo.findByFullNameContainingIgnoreCase("John")).thenReturn(actors);

        List<Actor> result = service.findByFullNameContainingIgnoreCase("John");

        assertEquals(actors, result);
        verify(actorRepo, times(1)).findByFullNameContainingIgnoreCase("John");
    }

    @Test
    public void getActorReturnsActorForValidId() {
        Actor actor = new Actor();
        when(actorRepo.findById((short) 1)).thenReturn(Optional.of(actor));

        Actor result = service.getActor((short) 1);

        assertEquals(actor, result);
        verify(actorRepo, times(1)).findById((short) 1);
    }

    @Test
    public void getActorThrowsExceptionForInvalidId() {
        when(actorRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.getActor((short) 1));
    }

    @Test
    public void deleteActorSuccessfully() {
        Actor actor = new Actor();
        when(actorRepo.findById((short) 1)).thenReturn(Optional.of(actor));

        service.deleteActor((short) 1);

        verify(actorRepo, times(1)).delete(actor);
    }

    @Test
    public void deleteActorThrowsExceptionForInvalidId() {
        when(actorRepo.findById(anyShort())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.deleteActor((short) 1));
    }
}
