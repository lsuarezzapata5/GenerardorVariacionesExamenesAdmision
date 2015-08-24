package DAO;

import DTO.CombinacionOpcion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos CombinacionOpcion
 *
 * @see CombinacionOpcion
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class CombinacionOpcionDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public CombinacionOpcionDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para cargar una CombinacionOpcion desde la base de datos
     *
     * @param combinacionOpcion
     * @return combinacionOpcion cargada desde la base de datos
     */
    public CombinacionOpcion cargarCombinacion(CombinacionOpcion combinacionOpcion) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM CombinacionOpcion WHERE id = '" + combinacionOpcion.getId() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                combinacionOpcion.setA(rs.getInt("A"));
                combinacionOpcion.setB(rs.getInt("B"));
                combinacionOpcion.setC(rs.getInt("C"));
                combinacionOpcion.setD(rs.getInt("D"));

                return combinacionOpcion;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la combinacion! Error: " + ex);
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
