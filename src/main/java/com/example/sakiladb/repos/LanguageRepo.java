package com.example.sakiladb.repos;

import com.example.sakiladb.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language,Short> {
}
