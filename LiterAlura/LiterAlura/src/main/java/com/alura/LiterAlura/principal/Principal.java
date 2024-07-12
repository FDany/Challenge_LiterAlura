package com.alura.LiterAlura.principal;

import com.alura.LiterAlura.model.*;
import com.alura.LiterAlura.repository.AutorRepository;
import com.alura.LiterAlura.repository.LibroRepository;
import com.alura.LiterAlura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;



    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
       this.libroRepositorio = libroRepository;
       this.autorRepositorio = autorRepository;
    }

    public void menu(){
        System.out.println("\n************** Menu ****************");
        System.out.println("1- Buscar libro por titulo");
        System.out.println("2- Listar libros registrados");
        System.out.println("3- Listar autores registrados");
        System.out.println("4- Listar autores vivos en un determinado anio");
        System.out.println("5- Listar libros por idioma");
        System.out.println("0- Para salir del programa \n");
        System.out.println("Eliga una opcion: ");
    }

    public void muestraMenu(){
        int op;

        menu();
        op = this.teclado.nextInt();
        teclado.nextLine();

        while(op != 0) {
            switch (op) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;

            }
            menu();
            op = this.teclado.nextInt();
            teclado.nextLine();
        }


    }


    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        System.out.println(libro);
        this.libroRepositorio.save(libro);
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el titulo del libro que buscas");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        //System.out.println(json);

        DatosGenerales datos = conversor.obtenerDatos(json, DatosGenerales.class);

        if (datos.libros() != null && !datos.libros().isEmpty()) {
            return datos.libros().get(0);
        }

        return null;
    }

    public void listarLibrosRegistrados(){
        System.out.println("-------------- Libros Registrados --------------");
        List<Libro> libros = this.libroRepositorio.findAll();
        if(!libros.isEmpty())
            libros.forEach(System.out::println);
        else
            System.out.println("No posee libros registrados");
    }

    public void listarAutoresRegistrados(){
        System.out.println("\n-------------- Autores Registrados --------------");
        List<Autor> autores = this.autorRepositorio.findAll();
        if(!autores.isEmpty())
            autores.forEach(System.out::println);
        else
            System.out.println("No hay autores registrados");
    }


    public void listarAutoresVivosAnio(){
        System.out.println("Ingrese el anio en el que el autor estaba vivo: ");
        var anio = this.teclado.nextInt();
        this.teclado.nextLine();

        List<Autor> autores = autorRepositorio.listarAutoresVivosAnio( anio );

        if(!autores.isEmpty())
            autores.forEach(System.out::println);
        else
            System.out.println("No se registran autores vivos, segun el anio ingresado");

    }

    private void menuIdiomas() {
        System.out.println("Menu de idiomas");
        System.out.println("es (Español)");
        System.out.println("en (Ingles) ");
        System.out.println("fr (Frances) ");
        System.out.println("pt (Portugués) ");
        System.out.println("Elija una opcion: ");
    }


    private void listarLibrosPorIdioma() {
        menuIdiomas();
        String idioma = this.teclado.nextLine();
        List<Libro> libros = this.libroRepositorio.listarLibrosPorIdioma(idioma);
        if (!libros.isEmpty()) {
            System.out.println('\n');
            libros.forEach(System.out::println);
        } else {
            System.out.println("En la base de datos no se ha encontrado libros en el idioma seleccionado.");
        }
    }
}
