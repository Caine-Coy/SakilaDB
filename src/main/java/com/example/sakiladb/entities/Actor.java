package com.example.sakiladb.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Actor")
@Getter
@Setter
public class Actor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "actor_id")
        @Setter(AccessLevel.NONE)
        private Short id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Setter(AccessLevel.NONE)
        @Formula("CONCAT(first_name,' ',last_name)")
        private String fullName;

        @ManyToMany
        @JoinTable(
                name = "film_actor",
                joinColumns = {@JoinColumn(name = "actor_id")},
                inverseJoinColumns = {@JoinColumn(name = "film_id")}
        )
        private List<Film> films = new ArrayList<>();
}
