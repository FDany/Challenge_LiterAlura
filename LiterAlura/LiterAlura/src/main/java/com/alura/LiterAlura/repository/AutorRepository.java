package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // se usan los atributos de la entidad (clase) Autor (a.fallecimiento, a.nacimiento)
    @Query("SELECT a FROM Autor a WHERE a.fallecimiento > :anio AND a.nacimiento < :anio")
    List<Autor> listarAutoresVivosAnio(int anio);

}
