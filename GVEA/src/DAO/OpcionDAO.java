package DAO;

import DTO.Opcion;
import DTO.Pregunta;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos Opcion
 *
 * @see Opcion
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class OpcionDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public OpcionDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para listar las opciones de una pregunta
     *
     * @param idPregunta
     * @return Collection de tipo Opcion
     */
    public Collection<Opcion> listarOpcionesdePregunta(int idPregunta) {
        Collection<Opcion> opciones = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Opcion WHERE Pregunta_id = '" + idPregunta + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);

            while (rs.next()) {
                Opcion opcion = new Opcion();
                opcion.setId(rs.getInt("id"));
                opcion.setDescripcion(rs.getString("descripcion"));
                opcion.setOrden(rs.getInt("orden"));
                Pregunta preg = new Pregunta();
                preg.setId(rs.getInt("Pregunta_id"));
                opcion.setPregunta(preg);
                opcion.setUrl(rs.getString("url"));
                Opcion opcionAux = new Opcion();
                opcionAux.setId(rs.getInt("despuesDe"));
                opcion.setDespuesDe((opcionAux));

                opciones.add(opcion);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de opcion de una pregunta! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return opciones;
    }

    /**
     * Metodo para cargar una opcion de la base de datos
     *
     * @param opcion
     * @return opcion con los atributos cargados desde la base de datos
     */
    public Opcion cargarOpcion(Opcion opcion) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Opcion WHERE id = '" + opcion.getId()
                    + "' AND Pregunta_id = '" + opcion.getPregunta().getId() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                opcion.setUrl(rs.getString("url"));
                opcion.setOrden(rs.getInt("orden"));
                Opcion opcionAux = new Opcion();
                if (rs.getInt("despuesDe") == 3) {
                    opcionAux.setId(0);
                } else {
                    opcionAux.setId(rs.getInt("despuesDe"));
                }
                opcion.setDespuesDe(opcionAux);
                Pregunta preg = new Pregunta();
                preg.setId(rs.getInt("Pregunta_id"));
                opcion.setPregunta(preg);
                opcion.setDescripcion(rs.getString("descripcion"));
                return opcion;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la opcion! Error: " + ex);
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
     * Metodo para ingresar una opcion a la base de datos
     *
     * @param opcion
     * @return 1 para confirmar el existo del Insert, de lo contrario 0
     */
    public int crearOpcion(Opcion opcion) {
        String consulta;
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Object ob = null;
            if (opcion.getDespuesDe().getId() != 0) {
                ob = opcion.getDespuesDe().getId();
            }
            String descripcion = opcion.getDescripcion().replace("\\", "\\\\");
            consulta = "INSERT INTO Opcion VALUES('" + opcion.getId() + "','"
                     + opcion.getPregunta().getId() + "','"+ opcion.getUrl() + "','" +
                    opcion.getOrden() +"','" + descripcion + "'," +ob  + ")";

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);

            if (rs == 1 || rs == 4 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando la opcion!. Error: " + ex);
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
     * Metodo para obtener el id de la ultima opcion ingresada a la base de datos
     *
     * @param opcion
     * @return id de la ultima opcion ingresada
     */
    public int obtenerUltimoIdOpcionPregunta(Opcion opcion) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(id) FROM Opcion where Pregunta_id = '" + opcion.getPregunta().getId() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return rs.getInt("MAX(id)") + 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo código de opcion!. Error: " + ex);
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
     * Metodo para realizar Update a un registro de Opcion en la base de datos
     *
     * @param opcion
     * @return 1 para confirmar que el Update fue exitosos, de lo contrario 0
     */
    public int modificarOpcion(Opcion opcion) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Object ob = null;
            if (opcion.getDespuesDe().getId() != 0) {
                ob = opcion.getDespuesDe().getId();
            }
            String descripcion = opcion.getDescripcion().replace("\\", "\\\\");
            String consulta = "UPDATE  opcion SET orden = '" + opcion.getOrden()
                    + "',  despuesDe = " + ob + ", descripcion = '" + descripcion
                    + "' WHERE id = '" + opcion.getId() + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error modificando opcion!. Error: " + ex);
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
     * Metodo para borrar una Opcion de la base de datos
     *
     * @param idOpcion
     * @return 1 para confirmar que el Delete fue exitosos, de lo contrario 0
     */
    public int vaciarOpcion(int idOpcion) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "DELETE FROM opcion WHERE id = '" + idOpcion + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                JOptionPane.showMessageDialog(null, "Opcion limpiada !");
                return 1;
            }
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error limpiando la opcion ! Error: " + ex);
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
