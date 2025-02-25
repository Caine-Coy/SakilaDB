package com.example.sakiladb.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "language_id")
    private Short id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "film_id")
    private List<Film> originalFilms;
}
