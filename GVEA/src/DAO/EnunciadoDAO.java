package DAO;

import DTO.Area;
import DTO.Autor;
import DTO.Enunciado;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos Enunciado
 *
 * @see Enunciado
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class EnunciadoDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public EnunciadoDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para listar los id de los Enunciados registrados en la base de
     * datos
     *
     * @return ArrayList con los id de los enunciados
     */
    public ArrayList listarId() {
        ArrayList codigos = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT id FROM Enunciado ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                codigos.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de id de Enunciado! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return codigos;
    }

    /**
     * Metodo para listar los autores registrados en la base de datos
     *
     * @return Collection de Autores
     */
    public Collection<Autor> cargarAutores() {
        Collection<Autor> autores = new ArrayList();
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Autor ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setTelefono(rs.getString("telefono"));
                autor.setCorreoElectronico(rs.getString("correoelectronico"));
                autor.setDependencia(rs.getString("dependencia"));
                autores.add(autor);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de autores! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return autores;
    }
    
      /**
     * Metodo para listar los nombres de los autores registrados en la base de datos
     *
     * @return Collection con los nombres de los autores
     */
    public Collection cargarNombreAutores() {
        Collection autores = new ArrayList();
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Autor ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            autores.add(rs.getString("nombre"));
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de autores! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return autores;
    }

    /**
     * Metodo para cargar un enunciado desde la base de datos
     *
     * @param enunciado
     */
    public void cargarEnunciado(Enunciado enunciado) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT e.*, a.* FROM enunciado e, autor a WHERE e.id = '" + enunciado.getId() + "' AND e.Autor_id = a.id";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("a.id"));
                autor.setNombre(rs.getString("a.nombre"));
                autor.setCorreoElectronico(rs.getString("a.correoelectronico"));
                autor.setDependencia(rs.getString("dependencia"));
                autor.setTelefono(rs.getString("telefono"));
                enunciado.setAutor(autor);
                enunciado.setUrl(rs.getString("e.url"));

                Area area = new Area();
                area.setId(rs.getInt("e.Area_id"));
                enunciado.setArea(area);
                enunciado.setFechaCreacion(rs.getString("e.fechaCreacion"));
                enunciado.setOrden(rs.getInt("e.orden"));
                Enunciado enaux = new Enunciado();

                if (rs.getInt("e.despuesDe") == 3) {
                    enaux.setId(0);
                } else {
                    enaux.setId(rs.getInt("e.despuesDe"));
                }
                enunciado.setDespuesDe(enaux);
                enunciado.setDescripcion(rs.getString("e.descripcion"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta del enunciado! Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    /**
     * Metodo para realizar Update a un registro de Enunciado en la base de
     * datos
     *
     * @param enunciado
     * @return 1 para confirmar que el Update fue exitoso, de lo contrario 0
     */
    public int modificarEnunciado(Enunciado enunciado) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            Object ob = null;
            if (enunciado.getDespuesDe().getId() != 0) {
                ob = enunciado.getDespuesDe().getId();
            }
            String descripcion = enunciado.getDescripcion().replace("\\", "\\\\");
            consulta = "UPDATE enunciado SET orden = '" + enunciado.getOrden()
                    + "', autor_id = '" + enunciado.getAutor().getId()
                    + "', area_id = '" + enunciado.getArea().getId()
                    + "', despuesDe=" + ob + ", descripcion ='" + descripcion
                    + "' WHERE id = '" + enunciado.getId() + "'";

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error modificando enunciado!. Error: " + ex);
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
     * Metodo para insertar un enunciado a la base de datos
     *
     * @param enunciado
     * @return 1 para confirmar que el Insert fue exitosos, de lo contrario 0
     */
    public int crearEnunciado(Enunciado enunciado) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            String descripcion = enunciado.getDescripcion().replace("\\", "\\\\");
            if (enunciado.getDespuesDe().getId() != 0) {
                consulta = "INSERT INTO Enunciado VALUES('" + enunciado.getId() + "','"
                        + enunciado.getUrl() + "','" + enunciado.getFechaCreacion() + "','"
                        + enunciado.getOrden() + "','" + descripcion + "','"  + enunciado.getArea().getId() +
                        "','" +  enunciado.getAutor().getId() +"'," + enunciado.getDespuesDe().getId() + ",'"
                        + enunciado.getPaquetes() + "')";
            } else {
                consulta = "INSERT INTO Enunciado (id, url, fechaCreacion, orden, autor_id, area_id,"
                        + "paquetes, descripcion) VALUES('" + enunciado.getId() + "','" + enunciado.getUrl() + "','"
                        + enunciado.getFechaCreacion() + "','" + enunciado.getOrden() + "','"
                        + enunciado.getAutor().getId() + "','" + enunciado.getArea().getId() + "','"
                        + enunciado.getPaquetes() + "', '" + descripcion + "')";
            }

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando el enunciado!. Error: " + ex);
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
     * Metodo para obtener el id del ultimo enunciado ingresado
     *
     * @param enunciado
     * @return id del ultimo enunciado ingresado
     */
    public int obtenerId(Enunciado enunciado) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(id) FROM Enunciado";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return rs.getInt("MAX(id)") + 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo código de enunciado!. Error: " + ex);
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
     * Metodo para borrar un Enunciado de la base de datos
     *
     * @param idEnunciado
     * @return 1 para confirmar que el Delete fue exitosos, de lo contrario 0
     */
    public int borrarEnunciado(int idEnunciado) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "DELETE FROM enunciado WHERE id = '" + idEnunciado + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                JOptionPane.showMessageDialog(null, "Enunciado borrado !");
                return 1;
            }
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error borrando el enunciado ! Error: " + ex);
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
     * Metodo que validar si ya existe un enunciado con orden "primero"
     *
     * @return 1 si existe un enunciado con orden primero, de lo contrario 0
     */
    public int ValidaOrdenEnunciadoPrimero() {
        // Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Enunciado WHERE orden =0";//0 es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return 1;//Ya existe enunciado con orden primero
            } else {
                return 0;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de enunciado!. Error: " + ex);
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
     * Metodo que validar si ya existe un enunciado con orden "Ultimo"
     *
     * @return 1 si existe un enunciado con orden primero, de lo contrario 0
     */
    public int ValidaOrdenEnunciadoUltimo() {
        //Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Enunciado WHERE orden =1";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {

                return 1;//Ya existe enunciado con orden primero
            } else {

                return 0;

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de enunciado!. Error: " + ex);
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
