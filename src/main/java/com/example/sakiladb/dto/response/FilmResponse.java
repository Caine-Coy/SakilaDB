package com.example.sakiladb.dto.response;

import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class FilmResponse {
    private final Short id;
    private final String title;
    private final Byte languageId;
    private final Byte originalLanguageID;

    public static FilmResponse from(Film film){
        return new FilmResponse(
                film.getId(),
                film.getTitle(),
                film.getLanguageID(),
                film.getOriginalLanguageId()
        );
    }
}
