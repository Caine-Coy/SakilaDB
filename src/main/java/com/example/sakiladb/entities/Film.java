package com.example.sakiladb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Formula;

import java.time.Year;

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

    @Column(name = "release_year")
    private Year year;

    //Add language string or language array
    @Column(name = "language_id")
    private short languageID;
    @ManyToOne()
    @JoinColumn(name = "language_id", insertable = false,updatable = false)
    private Language language;

    @Column(name = "length")
    private short length;

    @Column(name = "rating")
    private String rating;


}
