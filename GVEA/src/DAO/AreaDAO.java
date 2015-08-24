package DAO;

import DTO.Area;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos Area
 *
 * @see Area
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class AreaDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public AreaDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para listar las Areas registradas en la base de datos
     *
     * @return Collection de tipo Area
     */
    public Collection<Area> listarAreas() {
        ArrayList areas = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Area";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);

            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt("id"));
                area.setNombre(rs.getString("nombre"));
                areas.add(area);
            }
            return areas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas cargando la lista de areas!. Error: " + ex);
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
     * Metodo para listar los id de las Areas registradas en la base de datos
     *
     * @return Collection con los id de las Areas
     */
    public Collection listarIdsAreas() {
        ArrayList areas = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Area";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            areas.add(rs.getInt("id"));
            return areas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas cargando la lista de ids de areas!. Error: " + ex);
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
     * Metodo para listar los nombres de las Areas registradas en la base de datos
     *
     * @return Collection con los id de las Areas
     */
    public Collection listarNombreAreas() {
        ArrayList areas = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Area";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            areas.add(rs.getInt("nombre"));
            return areas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas cargando la lista de ids de areas!. Error: " + ex);
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

}
