/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.poo.model.dao;

import cl.duoc.poo.model.connection.ConexionBD;
import cl.duoc.poo.model.entities.Pelicula;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author CETECOM
 */
public class PeliculaDAO {
    
    public int insertPelicula(Pelicula pelicula){
        int retorno = 0;
        try{
            ConexionBD conexion = new ConexionBD();
            String insert = "INSERT INTO pelicula (titulo, director, anio, duracion, genero) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.obtenerConexion().prepareStatement(insert);
            preparedStatement.setString(1, pelicula.getTitulo());
            preparedStatement.setString(2, pelicula.getDirector());
            preparedStatement.setInt(3, pelicula.getAnio());
            preparedStatement.setInt(4, pelicula.getDuracion());
            preparedStatement.setString(5, pelicula.getGenero());
            preparedStatement.executeUpdate();
            conexion.obtenerConexion().close();
            retorno = 1;
        } catch(SQLException sql){
            System.out.println("Error al ejecutar el insert. ");
        }     
        return retorno;
    }
 
    
    //AGREGAR TODOS LOS METODOS QUE TENIAS EN EL CONTROLLER.....
}
