package com.aluracursos.repository;

import com.aluracursos.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :año AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento >= :año)")
    List<Autor> autoresVivosEnAnio(Integer año);
}