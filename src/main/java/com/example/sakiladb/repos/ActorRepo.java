package com.example.sakiladb.repos;

import com.example.sakiladb.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepo extends JpaRepository<Actor,Short>{
    List<Actor> findByFullNameContainingIgnoreCase(String fullName);
}
