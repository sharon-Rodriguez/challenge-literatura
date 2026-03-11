package com.aluracursos.repository;

import com.aluracursos.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdioma(String idioma);
}
