package com.example.sakiladb;

import com.example.sakiladb.controllers.ActorController;
import com.example.sakiladb.controllers.FilmController;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import com.example.sakiladb.services.ActorService;
import com.example.sakiladb.services.FilmService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ActorServiceTest {
    private ActorRepo actorRepo;
    private FilmRepo filmRepo;

    public ActorServiceTest(ActorRepo actorRepo, FilmRepo filmRepo) {
        this.actorRepo = actorRepo;
        this.filmRepo = filmRepo;
    }
    @InjectMocks
    ActorService service = new ActorService(actorRepo,filmRepo);
    ActorController controller = mock(ActorController.class);

    static List<Actor> actors = List.of(
            new Actor((short) 1, "Bob", "Boberson", "Bob Boberson", List.of())
    );
    @Test
    public void updateActor(){
        Actor actor = actors.getFirst();
        service.updateActor(actor.getId(),actor.getFirstName(), actor.getLastName(), List.of());
    }

}
