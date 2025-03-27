package com.example.sakiladb.dto.request;

import com.example.sakiladb.dto.ValidationGroup.Create;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ActorRequest {

    private final Short id;
    @NotNull(groups ={Create.class})
    @Size(min = 1, max = 45)
    private final String firstName;

    @Size(min = 1, max = 45)
    @NotNull(groups ={Create.class})
    private final String lastName;

    @NotNull(groups ={Create.class})
    private final List<Short> filmIds;

}
