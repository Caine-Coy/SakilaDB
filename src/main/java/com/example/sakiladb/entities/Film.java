package com.example.sakiladb.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "film")
@Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "year")
    private int year;
}
