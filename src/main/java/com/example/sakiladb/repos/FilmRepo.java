package com.example.sakiladb.repos;

import com.example.sakiladb.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<Film,Short> {
}
