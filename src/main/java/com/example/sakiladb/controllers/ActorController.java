package com.example.sakiladb.controllers;

import com.example.sakiladb.dto.ValidationGroup.Create;
import com.example.sakiladb.dto.request.ActorRequest;
import com.example.sakiladb.dto.response.ActorResponse;
import com.example.sakiladb.entities.Actor;
import com.example.sakiladb.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static com.example.sakiladb.dto.ValidationGroup.*;

@RestController
@RequestMapping("/actors")
@CrossOrigin(origins = "*")
public class ActorController {
    private final ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @GetMapping
    public List<ActorResponse> listActors(@RequestParam(required = false) Optional<String> name) {
        return name
                .map(actorService::findByFullNameContainingIgnoreCase)
                .orElseGet(actorService::listActors)
                .stream()
                .map(ActorResponse :: from)
                .toList();

    }
    @GetMapping("/{id}")
    public ActorResponse getActor(@PathVariable Short id){
        return ActorResponse.from(actorService.getActor(id));
    }

    @PostMapping()
    public ActorResponse createActor(@Validated(Create.class) @RequestBody ActorRequest data) {
        Actor actor = actorService.createActor(
                data.getFirstName(),
                data.getLastName(),
                data.getFilmIds()
        );
        return ActorResponse.from(actor);
    }
    @PatchMapping()
    public ActorResponse updateActor(@Validated(Update.class)@RequestBody ActorRequest data){
        Actor actor = actorService.updateActor(
                data.getId(),
                data.getFirstName(),
                data.getLastName(),
                data.getFilmIds()
        );
        return ActorResponse.from(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Short id){
        actorService.deleteActor(id);
    }
}
