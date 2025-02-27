package com.example.sakiladb;

import com.example.sakiladb.controllers.ActorController;
import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.services.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ActorControllerTest {

    static List<Actor> actors = List.of(
            new Actor((short) 1, "Bob", "Boberson", "Bob Boberson", List.of())
    );

    static ActorService service = mock(ActorService.class);
    static ActorController controller = new ActorController(service);

    @BeforeAll
    public static void setup() {
        for (var actor : actors) {
            doReturn(actor).when(service).getActor(actor.getId());
        }
    }

    @Test
    public void getActorReturnsActorResponseForValidActorId() {
        //test actor response

        Actor actor = actors.getFirst();
        doReturn(actor).when(service).getActor(actor.getId());

        final var expectedResponse = ActorResponse.from(actor);
        final var actualResponse = controller.getActor(actor.getId());

        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
        Assertions.assertEquals(expectedResponse.getLastName(), actualResponse.getLastName());
    }

    @Test
    public void createActorReturnsActorResponse() {
        Actor actor = actors.getFirst();
        doReturn(actor).when(service).createActor(actor.getFirstName(), actor.getLastName(), List.of());

        final var expectedResponse = ActorResponse.from(actor);
        final var actualResponse = controller.createActor(new ActorRequest(actor.getId(), actor.getFirstName(), actor.getLastName(), List.of()));

        Assertions.assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
        //Not sure use save
    }

    @Test
    public void updateActorReturnsActorResponse() {
        Actor actor = actors.getFirst();
        doReturn(actor).when(service).updateActor(actor.getId(), actor.getFirstName(), actor.getLastName(), List.of());

        final var expectedResponse = ActorResponse.from(actor);
        final var actualResponse = controller.updateActor(new ActorRequest(actor.getId(), actor.getFirstName(), actor.getLastName(), List.of()));

        Assertions.assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
    }
}
