package com.example.sakiladb.dto.request;

import com.example.sakiladb.dto.ValidationGroup;
import com.example.sakiladb.dto.ValidationGroup.Create;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;
import java.util.List;
@AllArgsConstructor
@Getter
public class FilmRequest {
    private final Short id;
    @NotNull(groups= Create.class)
    @Size(min = 1, max = 45)
    private final String title;
    //@NotNull(groups= Create.class)
    private final Byte languageId;
    //@NotNull(groups= Create.class)
    private final Byte originalLanguageID;
    private final Year releaseYear;
    private final String description;
}
