package com.example.sakiladb.dto.response;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ActorResponse {
    private final short id;
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final List<Film> films;

    public static ActorResponse from(Actor actor){
        return new ActorResponse(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getFullName(),
                actor.getFilms()
        );
    }
}
