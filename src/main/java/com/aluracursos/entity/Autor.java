package com.aluracursos.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public Autor() {}

    public Autor(com.aluracursos.models.DatosAutor datos) {
        this.nombre = datos.nombre();
        this.fechaNacimiento = datos.fechaNacimiento();
        this.fechaFallecimiento = datos.fechaFallecimiento();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Integer f) { this.fechaNacimiento = f; }
    public Integer getFechaFallecimiento() { return fechaFallecimiento; }
    public void setFechaFallecimiento(Integer f) { this.fechaFallecimiento = f; }
    public List<Libro> getLibros() { return libros; }

    @Override
    public String toString() {
        return "Autor: " + nombre +
                "\nFecha de nacimiento: " + fechaNacimiento +
                "\nFecha de fallecimiento: " + fechaFallecimiento +
                "\nLibros: " + (libros != null ? libros.stream().map(Libro::getTitulo).toList() : "[]");
    }
}