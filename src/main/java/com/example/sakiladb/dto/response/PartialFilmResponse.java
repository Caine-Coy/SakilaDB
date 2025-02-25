package com.example.sakiladb.dto.response;

import com.example.sakiladb.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;
@AllArgsConstructor
@Getter
public class PartialFilmResponse {
    private final Short id;
    private final String title;
    private final Year releaseYear;

    public static PartialFilmResponse from(Film film) {
        return new PartialFilmResponse(
                    film.getId(),
                    film.getTitle(),
                    film.getYear()
                );
    }
}
