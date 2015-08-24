package DAO;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con la que se gestiona la conexion a la base de datos
* @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */


public class BD {
static String bd = "examenBD"; 
static String login = "root"; 
static String password = ""; 
static String url  ="jdbc:mysql://localhost/examenBD?user=" + login + "&password=" + password; 
static String Driver ="com.mysql.jdbc.Driver";
Connection conn = null; 
Connection conex;

/**
 * Metodo para realizar la conexion a la base de datos por medio del driver JDBC
 * @return la conexion establecida
 */

public Connection conexion() {
 
        try {
            Class.forName(Driver);
	        }
	        catch (ClassNotFoundException ex) {
	            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	        try {
	            conn = DriverManager.getConnection(url);
	        }
	        catch (SQLException ex) {
	            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       return conn;
	    }
	    
	  

}
