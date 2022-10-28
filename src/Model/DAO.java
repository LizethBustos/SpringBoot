/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DAO {
    conexionBD conexion = new conexionBD(); //instancia de conexion
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){ //READ
        String sql = "select * from usuario";
        List<ModDTO> lista = new ArrayList<>();
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ModDTO usuario = new ModDTO();
                usuario.setCedula(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setCorreo(rs.getString(4));
                lista.add(usuario);
                       
            }
        } catch (SQLException e) {
            System.out.println("Error al listar "+e);
        }
        
        return lista;
    } //fin de metodo listar-read
    
    
    //Metodo agregar-CREATE
    public void agregar (ModDTO usuario){
        String sql = "insert INTO usuario (usu_cc,usu_nombre,usu_apellido,usu_email) VALUES (? ,? , ? , ?)";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getCedula());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en agregar - DAO:" + e);
        }      
    }//fin metodo agregar-CREATE
   
    
    //metodo actualizar-Update
    public void actualizar (ModDTO usuario){
        String sql = "update usuario set usu_nombre=?, usu_apellido=?, usu_email=? where usu_cc=?";
        try {
            con = conexion.conectarBaseDatos();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setInt(4, usuario.getCedula());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizar - DAO:" + e);
        }
    }//fin metodo actualizar
    
    
     // metodo borrar - delete
    
     public void borrar(int id){
          String sql = "delete from usuario where usu_cc=" + id;
          try {
             con = conexion.conectarBaseDatos();
             ps = con.prepareStatement(sql);
             ps.executeUpdate();
         } catch (SQLException e) {
              System.out.println("Error al borrar - Dao." + e);
         }
     }// fin metodo borrar
  
    
    
    
}   //fin de la clase
