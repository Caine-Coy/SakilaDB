package com.example.sakiladb;

import com.example.sakiladb.controllers.ActorController;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import com.example.sakiladb.services.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mockito.Mockito.mock;

@Service
public class ActorServiceTest {
    private final ActorRepo actorRepo;
    private final FilmRepo filmRepo;

    public ActorServiceTest(ActorRepo actorRepo, FilmRepo filmRepo) {
        this.actorRepo = mock(ActorRepo.class);
        this.filmRepo = mock(FilmRepo.class);
    }

    static List<Actor> actors = List.of(
            new Actor((short) 1, "Bob", "Boberson", "Bob Boberson", List.of())
    );

    //ActorService service = new ActorService(actorRepo,filmRepo);
    ActorController controller = mock(ActorController.class);
}
