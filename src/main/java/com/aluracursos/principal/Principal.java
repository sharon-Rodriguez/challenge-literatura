package com.aluracursos.principal;

import com.aluracursos.entity.Autor;
import com.aluracursos.entity.Libro;
import com.aluracursos.models.DatosLibro;
import com.aluracursos.models.DatosResultados;
import com.aluracursos.repository.AutorRepository;
import com.aluracursos.repository.LibroRepository;
import com.aluracursos.service.ConsumoAPI;
import com.aluracursos.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                \nElija la opción a través de su número:
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                """);
            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando aplicación...");
                default -> System.out.println("Opción no válida");
            }
        }
    }

    private void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var titulo = teclado.nextLine();

        // Verificar si ya existe en BD
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("No se puede registrar el mismo libro más de una vez");
            System.out.println(libroExistente.get());
            return;
        }

        // Buscar en la API
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, DatosResultados.class);

        Optional<DatosLibro> libroDatos = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(titulo.toUpperCase()))
                .findFirst();

        if (libroDatos.isPresent()) {
            Libro libro = new Libro(libroDatos.get());
            libroRepository.save(libro);
            System.out.println(libro);
        } else {
            System.out.println("Libro no encontrado en la API");
        }
    }

    private void listarLibros() {
        libroRepository.findAll().forEach(System.out::println);
    }

    private void listarAutores() {
        autorRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        var año = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = autorRepository.autoresVivosEnAnio(año);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es- español
                en- inglés
                fr- francés
                pt- portugués
                """);
        var idioma = teclado.nextLine();
        libroRepository.findByIdioma(idioma).forEach(System.out::println);
    }
}
