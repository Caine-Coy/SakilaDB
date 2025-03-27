package com.example.sakiladb;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.repos.ActorRepo;
import com.example.sakiladb.repos.FilmRepo;
import com.example.sakiladb.services.ActorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class) // Enable Mockito support
public class ActorServiceTest {

    @Mock
    private ActorRepo actorRepo; // Mock ActorRepo

    @Mock
    private FilmRepo filmRepo; // Mock FilmRepo

    @InjectMocks
    private ActorService service; // Automatically inject mocks into ActorService

    static List<Actor> actors = List.of(
            new Actor((short) 1, "Bob", "Boberson", "Bob Boberson", List.of())
    );

    @Test
    public void updateActor() {
        // Arrange: Define mock behavior
        short actorId = actors.get(0).getId();
        String firstName = actors.get(0).getFirstName();
        String lastName = actors.get(0).getLastName();

        //doNothing().when(actorRepo).updateActor(anyShort(), anyString(), anyString());

        // Act: Call the method under test
        //service.updateActor(actorId, firstName, lastName, List.of());

        // Assert: Verify interactions with the mocks
        //verify(actorRepo, times(1)).updateActor(actorId, firstName, lastName);
    }
}
