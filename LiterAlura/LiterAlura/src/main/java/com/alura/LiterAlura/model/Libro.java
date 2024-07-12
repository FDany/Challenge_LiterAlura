package com.alura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Long numeroDeDescargas;

    public Libro(){
    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = new Autor( datosLibro.autores().get(0) );
        if(datosLibro.idiomas().isEmpty())
            this.idioma = "Desconocido";
        else
            this.idioma = datosLibro.idiomas().get(0).getIdioma();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autores) {
        this.autor = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "-------------- Libro ----------------\n" +
                "Titulo: " + titulo + '\n' +
                "Autor: " + autor.getNombre() + '\n' +
                "Idioma: " + idioma + '\n' +
                "NumeroDeDescargas: " + numeroDeDescargas + '\n' +
                "-------------------------------------\n";
    }
}
