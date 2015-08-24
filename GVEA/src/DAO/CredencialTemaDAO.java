package DAO;

import DTO.CredencialTema;
import DTO.Tema;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos CredenciaTema
 *
 * @see CredencialTema
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class CredencialTemaDAO {

    private BD bd = new BD();

    /**
     * Metodo que consulta todos los temas en la base de datos y asigna a cada
     * credencial un tema.
     *
     * @param credenciales a las que se les asignara tema
     * @return HashMap con la relacion Credencial x Tema
     */
    public HashMap generarCredencialTema(ArrayList credenciales) {

        ArrayList temas = new ArrayList();
        Connection con = bd.conexion();

        try {
            int j = 1, k = 0;
            String consulta = "SELECT * FROM Tema WHERE id > 0";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                Tema nuevoTema = new Tema();
                nuevoTema.setId(rs.getInt("id"));
                temas.add(nuevoTema);

            }
            HashMap credencialTema = new HashMap();
            while (j < credenciales.size()) {
                Tema tema = (Tema) temas.get(k);
                asignarCredencial(tema, (Integer) Integer.parseInt(credenciales.get(j).toString()));
                credencialTema.put(credenciales.get(j), tema.getId());
                j++;
                k++;
                if (k == temas.size()) {
                    k = 0;
                }
            }
            return credencialTema;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Problemas al momento de asignar Tema-Credencial! Error: " + e);
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
     * Metodo que ingresa en la tabla CredencialTema la relacion generada en
     * generarCredencialTema
     *
     * @param tema
     * @param credencial
     * @return 1 para confirmar la insercion exitosa o 0 de lo contrario
     */
    public int asignarCredencial(Tema tema, int credencial) {
        Connection con = bd.conexion();
        try {
            String consulta = "INSERT INTO CredencialTema VALUES('" + credencial + "','" + tema.getId() + "')";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas al momento de insertar datos en la tabla CredencialTema!. Error: " + ex);
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
     * Metodo para obtener todos los registros que se encuentran en la tabla
     * CredencialTema
     *
     * @return ArrayList de objetos tipo CredencialTema
     */
    public ArrayList getCredencialTema() {
        ArrayList credencialTemaAr = new ArrayList();
        Connection con = bd.conexion();

        try {
            String consulta = "SELECT * FROM CredencialTema ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                CredencialTema credencialTema = new CredencialTema();
                credencialTema.setCredencial(rs.getString("credencial"));
                int tema = rs.getInt("Tema_id");
                Tema temaT = new Tema();
                temaT.setId(tema);
                credencialTema.setTema(temaT);
                credencialTemaAr.add(credencialTema);
            }

            return credencialTemaAr;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas al momento de obtener Credencial-Tema! Error: " + e);
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
