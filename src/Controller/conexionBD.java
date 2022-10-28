/*
Misión TIC
Ciclo 2
Grupo de programación 12
Formador: Leonardo Bustamante
Integrantes del grupo:
Lizeth Katherine Bustos Espinosa
Oscar Germanico Motta Riaño
Bayron Alejandro Ramirez Vargas
*/


package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class conexionBD {
    Connection con;
    //String driver = "com.mysql.cj.jdbc.Driver";
    String dbName = "final_reto";
    String url = "jdbc:mysql://localhost:3306/"+dbName;
    String usuario = "root";
    String clave= "lkbe";
    
    public Connection conectarBaseDatos (){
        
        try {
            //Class.forName(driver);
            con = DriverManager.getConnection(url,usuario,clave);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("Error en conexion:" + e);
        }
        return con;
        }
    }
        
/*class Prueba {
    public static void main(String[] args) {
        Connection con;
        conexionBD conexion = new conexionBD();
        con = conexion.conectarBaseDatos();
    }
}*/