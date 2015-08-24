package DAO;

import DTO.Enunciado;
import DTO.Pregunta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos Pregunta
 *
 * @see Pregunta
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class PreguntaDAO {

    private BD bd;
    private Connection con;

    private OpcionDAO opcionDao = new OpcionDAO();
    private EnunciadoDAO enunciadoDao = new EnunciadoDAO();

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public PreguntaDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para listar los id de las preguntas que pertenecen a un enunciado
     *
     * @param idEnunciado
     * @return ArrayList con los id de las preguntas que pertenecen a un
     * enunciado
     */
    public ArrayList listarIdsPreguntasdeEnunciado(int idEnunciado) {
        ArrayList idPreguntas = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM pregunta WHERE Enunciado_id = '" + idEnunciado + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                idPreguntas.add(rs.getInt("id"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de id de pregunta de un enunciado! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return idPreguntas;
    }

    /**
     * Metodo para realizar Update a un registro de Pregunta en la base de datos
     *
     * @param pregunta
     * @return 1 para confirmar que el Update fue exitosos, de lo contrario 0
     */
    public int modificarPregunta(Pregunta pregunta) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            Object ob = null;

            if (pregunta.getDespuesDe().getId() != 0) {
                ob = pregunta.getDespuesDe().getId();
            }
            String descripcion = pregunta.getDescripcion().replace("\\", "\\\\");
            consulta = "UPDATE pregunta SET orden = '" + pregunta.getOrden() + "', despuesDe =" + ob
                    + ", descripcion = '" + descripcion + "' WHERE id = '" + pregunta.getId() + "'";

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error modificando pregunta!. Error: " + ex);
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
     * Metodo para cargar los atributos de una pregunta desde la base de datos
     *
     * @param pregunta
     * @return pregunta con los atributos cardados
     */
    public Pregunta cargarInformacionPregunta(Pregunta pregunta) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM pregunta WHERE id = '" + pregunta.getId() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                pregunta.setUrl(rs.getString("url"));
                Enunciado enunciadoAux = new Enunciado();
                enunciadoAux.setId(rs.getInt("Enunciado_id"));
                enunciadoDao.cargarEnunciado(enunciadoAux);
                pregunta.setEnunciado(enunciadoAux);
                pregunta.setOrden(rs.getInt("orden"));
                Pregunta preguntaAux = new Pregunta();
                if (rs.getInt("despuesDe") == 3) {

                    preguntaAux.setId(0);
                    pregunta.setDespuesDe(preguntaAux);
                } else {
                    preguntaAux.setId(rs.getInt("despuesDe"));
                    pregunta.setDespuesDe(preguntaAux);
                }

                pregunta.setDescripcion(rs.getString("descripcion"));
                return pregunta;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la pregunta! Error: " + ex);
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
     * Metodo para ingresar una pregunta a la base de datos
     *
     * @param pregunta
     * @return 1 para confirmar que el Insert fue exitoso, de lo contrario 0
     */
    public int crearPregunta(Pregunta pregunta) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            Object despuesDe = null ;
            if ((pregunta.getDespuesDe().getId()) != 0) {
                despuesDe = pregunta.getDespuesDe().getId();
            }
           System.out.println(pregunta.getUrl());
            String descripcion = pregunta.getDescripcion().replace("\\", "\\\\");
            consulta = "INSERT INTO Pregunta VALUES('" + pregunta.getId() + "','"
                     + pregunta.getEnunciado().getId() + "','"+ pregunta.getUrl() + "','"
                    + pregunta.getOrden() + "','" + descripcion+ "',"  +  despuesDe + ")";
            
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);

            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando la pregunta!. Error: " + ex);
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
     * Metodo para borrar una Pregunta de la base de datos
     *
     * @param idPregunta
     * @return 1 para confirmar que el Delete fue exitosos, de lo contrario 0
     */
    public int borrarPregunta(int idPregunta) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "DELETE FROM pregunta WHERE id = '" + idPregunta + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error borrando la pregunta ! Error: " + ex);
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
     * Metodo para obtener el id de la ultima pregunta ingresada a la base de
     * datos
     *
     * @param pregunta
     * @return 1 para confirmar la operacion, de lo contrario 0
     */
    public int obtenerUltimoIdPreguntaDeEnunciado(Pregunta pregunta) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(id) FROM pregunta ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {

                return rs.getInt("MAX(id)") + 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo código de pregunta!. Error: " + ex);
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
     * Metodo que validar si ya existe una pregunta con orden "primero"
     *
     * @return 1 si existe un enunciado con orden primero, de lo contrario 0
     */
    public int validaOrdenPreguntaPrimero() {
        //Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Pregunta WHERE orden =0";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return 1;//Ya existe enunciado con orden primero
            } else {
                return 0;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de pregunta!. Error: " + ex);
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
     * Metodo que validar si ya existe una pregunta con orden "Ultimo"
     *
     * @return 1 si existe un enunciado con orden primero, de lo contrario 0
     */
    public int validaOrdenPreguntaUltimo() {
        //Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Prregunta WHERE orden =1";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return 1;//Ya existe enunciado con orden primero
            } else {
                return 0;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de pregunta. Error: " + ex);
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

    
}
