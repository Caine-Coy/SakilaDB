package com.example.sakiladb.dto.response;

import com.example.sakiladb.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
