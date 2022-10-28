/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Usuario
 */
public class ModDTO {
    private int cedula;
    private String nombre;
    private String apellido;
    private String correo;


    //constructor vacio
    public ModDTO() { //para cargar tabla en la vista 
    
    }

    //constructor agregar

    public ModDTO(int usu_cc, String usu_nombre, String usu_apellido, String usu_email) {
        this.cedula = usu_cc;
        this.nombre = usu_nombre;
        this.apellido = usu_apellido;
        this.correo = usu_email;
    }
  
     
    //getter y setter - modificadores de acceso

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
}