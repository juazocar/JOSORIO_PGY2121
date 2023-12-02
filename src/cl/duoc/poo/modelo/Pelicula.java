
package cl.duoc.poo.modelo;

import cl.duoc.poo.interfaz.Interfaz;

/**
 *
 * @author Ignacio Osorio
 * @version 00: Creación código base - 20-11-2023
 */
public class Pelicula {
    //atributos
    private int idPelicula;
    private String titulo;
    private String director;
    private int anio;
    private int duracion;
    private String genero;

    //Constructor s parámetros
    public Pelicula() {
    }
    
    //Constructor con parámetro

    public Pelicula(int idPelicula, String titulo, String director, int anio, int duracion, String genero) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
    }


        //Getter and setter
    
    public int getIdPelicula() {
        return idPelicula;
    }


    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    //MÉTODO DE IMPRESIÓN TO STRING

    @Override
    public String toString() {
        return "Pelicula{" + "idPelicula=" + idPelicula + ", titulo=" + titulo + ", director=" + director + ", anio=" + anio + ", duracion=" + duracion + ", genero=" + genero + '}';
    }

   
   

     
    
    
    
    
}
