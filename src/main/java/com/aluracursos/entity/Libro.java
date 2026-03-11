package com.aluracursos.entity;

import com.aluracursos.models.DatosLibro;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    private String idioma;
    private Double numeroDescargas;

    public Libro() {}

    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autores = datos.autores().stream().map(Autor::new).toList();
        this.idioma = datos.idiomas().isEmpty() ? "desconocido" : datos.idiomas().get(0);
        this.numeroDescargas = datos.numeroDescargas();
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Autor> getAutores() { return autores; }
    public String getIdioma() { return idioma; }
    public Double getNumeroDescargas() { return numeroDescargas; }

    @Override
    public String toString() {
        return "----- LIBRO -----" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autores != null && !autores.isEmpty() ? autores.get(0).getNombre() : "desconocido") +
                "\nIdioma: " + idioma +
                "\nNumero de descargas: " + numeroDescargas +
                "\n-----------------";
    }
}
