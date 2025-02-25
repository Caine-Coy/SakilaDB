package com.example.sakiladb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class FilmRequest {
    private final Short id;
    private final String title;
    private final Byte languageId;
    private final Byte originalLanguageID;

}
