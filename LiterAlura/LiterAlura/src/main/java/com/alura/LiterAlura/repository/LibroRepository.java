package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository  extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> listarLibrosPorIdioma(String idioma);
}
