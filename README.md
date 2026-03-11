# 📚 Literatura Catálogo

Aplicación de consola desarrollada con **Java + Spring Boot** que permite buscar libros a través de la API de [Gutendex](https://gutendex.com/) y guardarlos en una base de datos PostgreSQL.

---

## ✨ Funcionalidades

- 🔍 **Buscar libro por título** — Consulta la API de Gutendex y registra el libro en la base de datos
- 📖 **Listar libros registrados** — Muestra todos los libros guardados en la BD
- 👤 **Listar autores registrados** — Muestra todos los autores guardados
- 📅 **Listar autores vivos en un año** — Filtra autores que estaban vivos en un año determinado
- 🌍 **Listar libros por idioma** — Filtra libros por idioma (es, en, fr, pt)
- ❌ **Sin duplicados** — No permite registrar el mismo libro más de una vez

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring Data JPA | 3.x |
| PostgreSQL | 17 |
| Hibernate | 6.x |
| Jackson Databind | 2.15.2 |
| API Gutendex | - |

---

## ⚙️ Requisitos previos

- Java 17 o superior instalado
- PostgreSQL instalado y corriendo
- Maven

---

## 🚀 Instalación y configuración

1. **Clona el repositorio**
```bash
git clone https://github.com/TU_USUARIO/literatura-catalogo.git
cd literatura-catalogo
```

2. **Crea la base de datos en PostgreSQL**
```sql
CREATE DATABASE literatura;
```

3. **Configura las credenciales** en `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=postgres
spring.jpa.show-sql=false
```

4. **Ejecuta el proyecto** desde IntelliJ con el botón ▶️ o con:
```bash
mvn spring-boot:run
```

---

## 📋 Uso

Al iniciar la aplicación verás el siguiente menú:

```
Elija la opción a través de su número:
1- Buscar libro por título
2- Listar libros registrados
3- Listar autores registrados
4- Listar autores vivos en un determinado año
5- Listar libros por idioma
0- Salir
```

Ejemplo al buscar "quijote":
```
Libro encontrado
----- LIBRO -----
Título: Don Quijote
Autor: Cervantes Saavedra, Miguel de
Idioma: es
Numero de descargas: 10087.0
-----------------
```

---

## 🗂️ Estructura del proyecto

```
src/main/java/com/aluracursos/
├── LiteraturaCatalogoApplication.java
├── entity/
│   ├── Autor.java
│   └── Libro.java
├── models/
│   ├── DatosAutor.java
│   ├── DatosLibro.java
│   └── DatosResultados.java
├── principal/
│   └── Principal.java
├── repository/
│   ├── AutorRepository.java
│   └── LibroRepository.java
└── service/
    ├── ConsumoAPI.java
    └── ConvierteDatos.java
```

---

## 👩‍💻 Desarrollado por

**Sharon Rodriguez** — Challenge Alura LATAM + Oracle Next Education
