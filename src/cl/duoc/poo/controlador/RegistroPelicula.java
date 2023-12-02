
package cl.duoc.poo.controlador;

import Conexion.ConexionBD;
import cl.duoc.poo.modelo.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ignacio Osorio
 * @version 00: Creación código base - 20-11-2023
 */

public class RegistroPelicula {
    
    
    //Agregar pelicula a BBDD
    public static boolean agregarPeli(Pelicula peli){
        
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "INSERT INTO movie(titulo, director, anio, duracion, genero) VALUES (?,?,?,?,?)";
            //Prepara la query para insertar
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            //Insertar datos
            stmt.setString(1, peli.getTitulo());
            stmt.setString(2, peli.getDirector());
            stmt.setString(3, String.valueOf(peli.getAnio())); //Parseamos de entero a String para insertar en SQL
            stmt.setString(4, String.valueOf(peli.getDuracion())); //Parseamos de entero a String para insertar en SQL
            stmt.setString(5, peli.getGenero());
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();;
            
            return true;
            
            
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Pelicula: " + e.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println("Error al agregar la Pelicula: "+ ex.getMessage());
            return false;
        }
        
        
    }
    
    
    
    //Método buscartodos
    public ArrayList<Pelicula> buscarTodos(){
        ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
        
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "SELECT id_pelicula, titulo, director, anio, duracion, genero FROM movie order by titulo";
            PreparedStatement stmt = cnx.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pelicula peli = new Pelicula();
                peli.setIdPelicula(rs.getInt("id_pelicula"));
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
    
    
    public ArrayList<Pelicula> buscarFiltro(int anioDesde, int anioHasta, String genero){
        ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
        
        
        //BUSCAR POR RANGO DE AÑOS SIN CATEGORÍA
        if(anioDesde > 0 && anioHasta > 0 && genero.equals("genero")){
            
            try {
                ConexionBD conexion1 = new ConexionBD();
                Connection cnx = conexion1.obtenerConexion();

                String query = "SELECT id_pelicula, titulo, director, anio, duracion, genero FROM movie WHERE anio BETWEEN ? AND ? order by titulo";
                PreparedStatement stmt = cnx.prepareStatement(query);

                stmt.setInt(1, anioDesde);
                stmt.setInt(2, anioHasta);

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setIdPelicula(rs.getInt("id_pelicula"));
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

                String query = "SELECT id_pelicula, titulo, director, anio, duracion, genero FROM movie WHERE genero = ? order by titulo";
                PreparedStatement stmt = cnx.prepareStatement(query);
                
                stmt.setString(1, genero);
                
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setIdPelicula(rs.getInt("id_pelicula"));
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

                String query = "SELECT id_pelicula, titulo, director, anio, duracion, genero FROM movie WHERE anio BETWEEN ? AND ? AND genero = ? order by titulo";
                PreparedStatement stmt = cnx.prepareStatement(query);
                
                stmt.setInt(1, anioDesde);
                stmt.setInt(2, anioHasta);
                stmt.setString(3, genero);
                

                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Pelicula peli = new Pelicula();
                    peli.setIdPelicula(rs.getInt("id_pelicula"));
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
    }//Fin método buscar todos
    
    
    
    
    
    
    
    //Método Buscar por ID
    public Pelicula buscarPorId(int id_pelicula){
        Pelicula peli = new Pelicula();
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "SELECT id_pelicula, titulo, director, anio, duracion, genero FROM movie WHERE id_pelicula=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, id_pelicula);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                
                
                peli.setIdPelicula(rs.getInt("id_pelicula"));
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


    
    //Método Eliminar
    //ADAPTAR PARA ESTA APP
    public boolean eliminarPelicula(int idPelicula){
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "DELETE FROM movie WHERE id_pelicula=?";
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
            
    
    
    
    
    //Método Actualizar
    //ADAPTAR PARA ESTA APP
    public boolean actualizarRegistro(Pelicula peli){
        try {
            ConexionBD conexion1 = new ConexionBD();
            Connection cnx = conexion1.obtenerConexion();
            
            String query = "UPDATE movie set titulo = ?, director = ?, anio = ?, duracion = ?, genero = ? WHERE id_pelicula = ?";
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
    
    
    
    
    
    
    
    
    
    
    
    //CODIGO PARA LISTAR POR COLECCIÓN
    
    
    
    
    /*
    static ArrayList<Pelicula> listadopeliculas;

    public RegistroPelicula() {
        //Iniciar colección listado peliculas para guardar objetos
        listadopeliculas = new ArrayList<>();
    
    }

    
    //Getter and setter
    public static ArrayList<Pelicula> getListadopeliculas() {
        return listadopeliculas;
    }

    public static void setListadopeliculas(ArrayList<Pelicula> listadopeliculas) {
        RegistroPelicula.listadopeliculas = listadopeliculas;
    }
    
    
    //Metodo agregar Pelicula
    public static boolean agregarPelicula(Pelicula peli){
        boolean agregado = false;
        
        if(buscarPelicula(peli.getTitulo()) == false){
            if (listadopeliculas.add(peli)){
                agregado = true;
            }
        }
        return agregado;
    
    }
    
    
    
    
    //Método buscar Pelicula
    public static boolean buscarPelicula(String titulo){
        boolean valida = false;
        
        for(Pelicula temp : listadopeliculas){
            if(temp.getTitulo().equals(titulo)){
                valida = true;
                break;
            }
        }
        return valida;
    }
    
    //Eliminar Pelicula de la colección
    public static boolean eliminarPelicula(String titulo){
        boolean valida = false;
        
        for(int i=0; i < listadopeliculas.size(); i++){
            if(listadopeliculas.get(i).getTitulo()== titulo){
                listadopeliculas.remove(i);
                valida = true;
                break;
            }
        }
        return valida;
    }
    
    */
    
    
}
