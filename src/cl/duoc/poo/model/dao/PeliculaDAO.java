/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.poo.model.dao;

import cl.duoc.poo.model.connection.ConexionBD;
import cl.duoc.poo.model.entities.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Pelicula> buscarTodos(){
        
        List<Pelicula> lista = new ArrayList<Pelicula>();
        
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "SELECT idpelicula, titulo, director, anio, duracion, genero FROM pelicula order by titulo DESC";
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pelicula peli = new Pelicula();
                peli.setIdPelicula(rs.getInt("idpelicula"));
                peli.setTitulo(rs.getString("titulo"));
                peli.setDirector(rs.getString("director"));
                peli.setAnio(rs.getInt("anio"));
                peli.setDuracion(rs.getInt("duracion"));
                peli.setGenero(rs.getString("genero"));
                
                
                lista.add(peli);
            }
            rs.close();
            stmt.close();
            cnx.close(); 
            
        } catch (SQLException e) {
            System.out.println("Error SQL al seleccionar Peliculas desde BBDD: " + e.getMessage());            
        } catch(Exception ex){
            System.out.println("Error al seleccionar Peliculas Libro: " + ex.getMessage());                
        }
        
        return lista;
    }//Fin método buscar todos
    
     public List<Pelicula> buscarFiltro(int anioDesde, int anioHasta, String genero){
        List<Pelicula> lista = new ArrayList<Pelicula>();
        
        
        //BUSCAR POR RANGO DE AÑOS SIN CATEGORÍA
        if(anioDesde > 0 && anioHasta > 0 && genero.equals("genero")){
            
            try {
                ConexionBD conexion1 = new ConexionBD();
                Connection cnx = conexion1.obtenerConexion();

                String query = "SELECT idpelicula, titulo, director, anio, duracion, genero FROM pelicula WHERE anio BETWEEN ? AND ? order by titulo";
                PreparedStatement stmt = cnx.prepareStatement(query);

                stmt.setInt(1, anioDesde);
                stmt.setInt(2, anioHasta);

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setIdPelicula(rs.getInt("idpelicula"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setDirector(rs.getString("director"));
                    peli.setAnio(rs.getInt("anio"));
                    peli.setDuracion(rs.getInt("duracion"));
                    peli.setGenero(rs.getString("genero"));


                    lista.add(peli);
                }
                rs.close();
                stmt.close();
                cnx.close(); 
            
            } catch (SQLException e) {
                System.out.println("Error SQL al seleccionar Peliculas desde BBDD: " + e.getMessage());            
            } catch(Exception ex){
                System.out.println("Error al seleccionar Peliculas Libro: " + ex.getMessage());                
            }
        //FIN PRIMER ELSE
        
        
        
        //BÚSQUEDA POR GÉNERO
        }else if(anioDesde == 0 && anioHasta == 0 && genero != null && genero != ""){
            try {
                ConexionBD conexion1 = new ConexionBD();
                Connection cnx = conexion1.obtenerConexion();

                String query = "SELECT idpelicula, titulo, director, anio, duracion, genero FROM pelicula WHERE genero = ? order by titulo";
                PreparedStatement stmt = cnx.prepareStatement(query);
                
                stmt.setString(1, genero);
                
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setIdPelicula(rs.getInt("idpelicula"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setDirector(rs.getString("director"));
                    peli.setAnio(rs.getInt("anio"));
                    peli.setDuracion(rs.getInt("duracion"));
                    peli.setGenero(rs.getString("genero"));


                    lista.add(peli);
                }
                rs.close();
                stmt.close();
                cnx.close(); 
            
            } catch (SQLException e) {
                System.out.println("Error SQL al seleccionar Peliculas desde BBDD: " + e.getMessage());            
            } catch(Exception ex){
                System.out.println("Error al seleccionar Peliculas Libro: " + ex.getMessage());                
            }
        //FIN SEGUNDO IF
        
        //BUSQUEDA POR AMBOS FILTROS
        }else if(anioDesde > 0 && anioHasta > 0 && genero != null && genero != ""){
            try {
                ConexionBD conexion1 = new ConexionBD();
                Connection cnx = conexion1.obtenerConexion();

                String query = "SELECT idpelicula, titulo, director, anio, duracion, genero FROM pelicula WHERE anio BETWEEN ? AND ? AND genero = ? order by titulo";
                PreparedStatement stmt = cnx.prepareStatement(query);
                
                stmt.setInt(1, anioDesde);
                stmt.setInt(2, anioHasta);
                stmt.setString(3, genero);
                

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setIdPelicula(rs.getInt("idpelicula"));
                    peli.setTitulo(rs.getString("titulo"));
                    peli.setDirector(rs.getString("director"));
                    peli.setAnio(rs.getInt("anio"));
                    peli.setDuracion(rs.getInt("duracion"));
                    peli.setGenero(rs.getString("genero"));


                    lista.add(peli);
                }
                rs.close();
                stmt.close();
                cnx.close(); 
            
            } catch (SQLException e) {
                System.out.println("Error SQL al seleccionar Peliculas desde BBDD: " + e.getMessage());            
            } catch(Exception ex){
                System.out.println("Error al seleccionar Peliculas Libro: " + ex.getMessage());                
            }
        }else{
            System.out.println("### Los filtros no son correctos ###");
        }
        
        
        return lista;
     }
     
     public Pelicula buscarPorId(int idPelicula){
        Pelicula peli = new Pelicula();
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "SELECT idpelicula, titulo, director, anio, duracion, genero FROM pelicula WHERE idpelicula=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idPelicula);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                
                peli.setIdPelicula(rs.getInt("idpelicula"));
                peli.setTitulo(rs.getString("titulo"));
                peli.setDirector(rs.getString("director"));
                peli.setAnio(rs.getInt("anio"));
                peli.setDuracion(rs.getInt("duracion"));
                peli.setGenero(rs.getString("genero"));
            }
            
            rs.close();
            stmt.close();
            cnx.close();            
        } catch (SQLException e) {
            System.out.println("Error SQL al buscar por ID: " + e.getMessage());            
        } catch(Exception ex){
            System.out.println("Error al buscar pelicula por ID: " + ex.getMessage());                
        }
        return peli;
    }//Fin Método Buscar por ID
     
     public boolean eliminarPelicula(int idPelicula){
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "DELETE FROM pelicula WHERE idpelicula=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, idPelicula);
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al Eliminar Pelicula: " + idPelicula +" - " + e.getMessage());
            return false;
        } catch(Exception ex){
            System.out.println("Error al Eliminar Pelicula: " + idPelicula +" - " + ex.getMessage());    
            return false;
        }
    }//Fin Método Eliminar
     
      public boolean actualizarRegistro(Pelicula peli){
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "UPDATE pelicula set titulo = ?, director = ?, anio = ?, duracion = ?, genero = ? WHERE idpelicula = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            stmt.setString(1, peli.getTitulo());
            stmt.setString(2, peli.getDirector());
            stmt.setInt(3,peli.getAnio());
            stmt.setInt(4, peli.getDuracion());
            stmt.setString(5, peli.getGenero());
            stmt.setInt(6, peli.getIdPelicula());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar registro: " + e.getMessage());
            return false;
        } catch(Exception ex){
            System.out.println("Error al actualizar Pelicula: " + ex.getMessage());    
            return false;
        }
    
    }//Fin método actualizar
}
