package com.example.sakiladb.entities;

import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Table(name = "Actor")
public class Actor {

    @Getter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "actor_id")
        private short id;

        @Column(name = "first_name")
        private String first_name;

        @Column(name = "last_name")
        private String last_name;
}
