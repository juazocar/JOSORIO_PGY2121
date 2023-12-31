/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.poo.controller;

import cl.duoc.poo.model.dao.PeliculaDAO;
import cl.duoc.poo.model.entities.Pelicula;
import java.util.List;

/**
 *
 * @author CETECOM
 */
public class PeliculaController {
    
    private PeliculaDAO peliculaDAO;
    
    public PeliculaController(){
        peliculaDAO = new PeliculaDAO();
    }
    
    public int insertPelicula(Pelicula pelicula){
        return peliculaDAO.insertPelicula(pelicula);
    }
    
    //CONECTAR TODOS LOS METODOS QUE SE ENCUENTREN EN LA CAPA DAO.....
    
    public List<Pelicula> buscarFiltro(int anioDesde, int anioHasta, String filtroGenero){
        return peliculaDAO.buscarFiltro(anioDesde, anioHasta, filtroGenero);
    }
    
    public List<Pelicula> buscarTodos(){
        return peliculaDAO.buscarTodos();
    }

    public boolean actualizarRegistro(Pelicula pelicula){
        return peliculaDAO.actualizarRegistro(pelicula);
    }
    
    public boolean eliminarPelicula(int idPelicula){
        return peliculaDAO.eliminarPelicula(idPelicula);
    }
    
    public Pelicula buscarPorId(int idPelicula){
        return peliculaDAO.buscarPorId(idPelicula);
    }
}
