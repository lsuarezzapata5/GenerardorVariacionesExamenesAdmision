package DAO;

import DTO.CombinacionOpcion;
import DTO.Pregunta;
import DTO.PreguntaTema;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import DTO.Tema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase con la que se realiza la persistencia de los objetos PreguntaTema
 *
 * @see PreguntaTema
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class PreguntaTemaDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public PreguntaTemaDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para validar si en la tabla TemporalPT ya existe registro para un
     * tema
     *
     * @param idTema
     * @return true si ya existe registro, de lo contrario false
     */
    public boolean existePreguntaTema(int idTema) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM TemporalPT WHERE Tema_id = '" + idTema + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la existencia de pregunta tema! Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return false;
    }

    /**
     * Metodo para ingresar un objeto PreguntaTema a la base de datos
     *
     * @param preguntaTema
     * @return 1 para confirmar que el Insert fue exitosos, de lo contrario 0
     */
    public int ingresarPreguntaTema(PreguntaTema preguntaTema) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            if (preguntaTema.getRespuestaCorrecta() != 0) {
                consulta = "INSERT INTO PreguntaTema VALUES('"
                        + preguntaTema.getTema().getId() + "','"
                        + preguntaTema.getPregunta().getId() + "','"
                        + preguntaTema.getRespuestaCorrecta() + "','"
                        + preguntaTema.getNumeroPregunta() + "','"
                        + preguntaTema.getCombinacion().getId() + "')";
            } else {
                consulta = "INSERT INTO PreguntaTema (Tema_id, Pregunta_id,"
                        + " numeroPregunta, CombinacionOpcion_id) VALUES('"
                        + preguntaTema.getTema().getId() + "','"
                        + preguntaTema.getPregunta().getId() + "','"
                        + preguntaTema.getNumeroPregunta() + "','"
                        + preguntaTema.getCombinacion().getId() + "')";
            }

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);

            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("Problemas creando preguntaTema!. Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return 0;
    }

    /**
     * Metodo para ingresar una PreguntaTema en la base de datos en la tabla
     * TemporalPT
     *
     * @param preguntaTema
     * @return 1 para confirmar que el Insert fue exitosos, de lo contrario 0
     */
    public int ingresarPreguntaTemaEnTemporal(PreguntaTema preguntaTema) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            int contador = preguntaTema.getNumeroPregunta();
            if (preguntaTema.getRespuestaCorrecta() != 0) {
                consulta = "INSERT INTO TemporalPT VALUES('"
                        + preguntaTema.getTema().getId() + "','"
                        + preguntaTema.getPregunta().getId() + "','"
                        + preguntaTema.getRespuestaCorrecta() + "','" + contador + "','"
                        + preguntaTema.getCombinacion().getId() + "')";
            } else {
                consulta = "INSERT INTO TemporalPT (Tema_id, Pregunta_id,"
                        + " numeroPregunta, CombinacionOpcion_id)"
                        + " VALUES('" + preguntaTema.getTema().getId() + "','"
                        + preguntaTema.getPregunta().getId() + "','" + contador + "','"
                        + preguntaTema.getCombinacion().getId() + "')";
            }

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);

            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando preguntaTema en TemporalPT!. Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return 0;
    }

    /**
     * Metodo para Obtener una PreguntaTema de la base de datos
     *
     * @param preguntaTema
     * @return PreguntaTema con todos los atributos cargados desde la base de
     * datos
     */
    public PreguntaTema consultarPreguntaTema(PreguntaTema preguntaTema) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM PreguntaTema WHERE Tema_id = '" + preguntaTema.getTema()
                    + "' and Pregunta_id='" + preguntaTema.getPregunta() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                Tema t = new Tema();
                t.setId(rs.getInt("Tema_id"));
                preguntaTema.setTema(t);
                Pregunta p = new Pregunta();
                p.setId(rs.getInt("Pregunta_id"));
                preguntaTema.setPregunta(p);
                preguntaTema.setRespuestaCorrecta(rs.getInt("respuestaCorrecta"));
                CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
                combinacionOpcion.setId(rs.getInt("CombinacionOpcion_id"));
                preguntaTema.setCombinacion(combinacionOpcion);
                preguntaTema.setNumeroPregunta(rs.getInt("nroPregunta"));
                return preguntaTema;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de PreguntaTema! Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return null;
    }

    /**
     * Metodo para borrar todos los registros de las tablas: PreguntaTema y
     * TemporalPT de la base de datos
     *
     * @return 1 para confirmar que el Delete fue exitosos, de lo contrario 0
     */
    public int borrarPreguntaTemas() {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }

            String consulta = "DELETE FROM PreguntaTema";
            Statement sta = con.createStatement();
            sta.executeUpdate(consulta);
            consulta = "DELETE FROM TemporalPT";
            sta = con.createStatement();
            sta.executeUpdate(consulta);
            return 1;
        } catch (SQLException ex) {
            System.out.println("Problemas borrando preguntaTemas!. Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return 0;
    }
    
   /**
    * 
    * @return resultado de consulta por Tema_id en la tabla TemporalPT
    * @throws SQLException 
    */
     public ResultSet hacerRandomPreguntas() throws SQLException {
          
         try {
           if (con.isClosed()) {
                con = bd.conexion();
            }
             String consulta= "";
            
             Statement sta = con.createStatement();
             
            if(!existePreguntaTema(0)){
                 consulta = "INSERT INTO TemporalPT SELECT * FROM PreguntaTema WHERE Tema_id = 0 ORDER BY RAND()";
               int rs = sta.executeUpdate(consulta);
            }

            consulta = "SELECT * FROM TemporalPT WHERE Tema_id = 0 ORDER BY RAND()";
            ResultSet rse = sta.executeQuery(consulta);
            return rse;
           
                      
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas en el random! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
      
    }

}
