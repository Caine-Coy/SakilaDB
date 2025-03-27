package com.example.sakiladb.repos;

import com.example.sakiladb.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepo extends JpaRepository<Film,Short> {
    List<Film> findByTitleContainingIgnoreCase(String title);
}
