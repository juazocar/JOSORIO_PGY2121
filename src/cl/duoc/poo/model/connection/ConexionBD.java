/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.poo.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ignacio Osorio
 */
public class ConexionBD {
    public static final String URL_CONEXION = "jdbc:mysql://localhost:3306/moviedb";
    public static final String USER = "root";
    //public static final String CONTRASEÑA = "root";
    public static final String CONTRASEÑA = "Informatica.23";
    // PAra duoc --- > "Informatica.23";
    public Connection obtenerConexion(){
        Connection conexion = null;
    
        try{
            //Carga la clase controlador
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //Establece la conexión a la base de datos
            conexion = DriverManager.getConnection(URL_CONEXION,USER,CONTRASEÑA); //URL de BBDD
            System.out.println("Conexión Exitosa");
        
        }catch(SQLException e){
            System.out.println("Error en la Conexión: "+ e.getMessage());

        } catch (Exception ex){
                System.out.println(ex.getMessage());

        }
        
        return conexion;
    }
    
}
