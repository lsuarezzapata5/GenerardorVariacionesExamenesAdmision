package DAO;

import DTO.Pregunta;
import DTO.PreguntaTema;
import DTO.Tema;
import DTO.CombinacionOpcion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * Clase con la que se realiza la persistencia de los objetos Tema
 *
 * @see Tema
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class TemaDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public TemaDAO() {
        bd = new BD();
       // con = bd.conexion();
    }

    /**
     * Metodo para ingresar un Tema a la base de datos
     *
     * @param tema
     * @return 1 para confirmar que el Insert fue exitoso, de lo contrario 0
     */
    public int ingresarTema(Tema tema) {
        try {
            String consulta;
            if (con == null || con.isClosed()) {
                con = bd.conexion();
            }
            consulta = "INSERT INTO tema VALUES('" + tema.getId() + "','"
                    + tema.getFechaCreacion() + "','" + tema.getYear() + "','"
                    + tema.getSemestre() + "','" + tema.getJornada() + "')";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);

            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("Problemas creando el tema!. Error: " + ex);
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
     * Metodo para validar si ya existe un registro en tabla Tema
     *
     * @param idTema
     * @return true si ya existe registro, de lo contrario false
     */
    public boolean existeTema(int idTema) {

        try {
            if (con == null || con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Tema WHERE id = '" + idTema + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return true;
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
        return false;
    }

    /**
     * Metodo para obtener el id del ultimo tema ingresado a la base de datos
     *
     * @return id de la ultimo tema ingresado
     */
    public int obtenerUltimoTema() {
        try {
            if (con == null || con == null || con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(id) as maximo FROM tema";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return rs.getInt("maximo");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la obtencion del ultimo tema! Error: " + ex);
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
     * Metodo para cargar los atributos de un Tema desde la base de datos
     *
     * @param tema
     * @return tema con los atributos cargados desde la base de datos
     */
    public Tema cargarTema(Tema tema) {

        try {
            if (con == null || con.isClosed()) {
                con = bd.conexion();
            }
            Collection<PreguntaTema> preguntaTemaAr = new ArrayList<>();
            String consulta = "SELECT * FROM tema WHERE id = '" + tema.getId() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                tema.setFechaCreacion(rs.getDate("fechaCreacion"));
                tema.setJornada(rs.getString("jornada"));
                tema.setSemestre(rs.getInt("semestre"));
                tema.setYear(rs.getInt("year"));

                consulta = "SELECT * FROM TemporalPT WHERE Tema_id= '" + tema.getId() + "'";
                ResultSet rs1 = sta.executeQuery(consulta);
                while (rs1.next()) {
                    PreguntaTema preguntaTema = new PreguntaTema();
                    CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
                    combinacionOpcion.setId(rs1.getInt("CombinacionOpcion_id"));
                    preguntaTema.setCombinacion(combinacionOpcion);
                    Pregunta pregunta = new Pregunta();
                    pregunta.setId(rs1.getInt("Pregunta_id"));
                    preguntaTema.setPregunta(pregunta);
                    preguntaTema.setRespuestaCorrecta(rs1.getInt("respuestaCorrecta"));
                    preguntaTema.setNumeroPregunta(rs1.getInt("numeroPregunta"));
                    preguntaTemaAr.add(preguntaTema);

                }
                tema.setPreguntas(preguntaTemaAr);
            }
            return tema;

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
     * Metodo para obtener la lista de Temas desde la base de datos
     *
     * @return Collection con objetos tipo Tema
     */
    public Collection<Tema> listarTemas() {
        Collection<Tema> temas = new ArrayList<>();
        try {
            if (con == null || con == null || con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM tema";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                Tema tema = new Tema();
                tema.setId(rs.getInt("id"));
                tema.setFechaCreacion(rs.getDate("fechaCreacion"));
                tema.setJornada(rs.getString("jornada"));
                tema.setYear(rs.getInt("year"));
                tema.setSemestre(rs.getInt("semestre"));
                temas.add(tema);
            }
            return temas;
        } catch (SQLException ex) {
            System.out.println("Problemas listando los temas existentes");
        }
        return null;
    }

    /**
     * Metodo para ingresar la respuesta correcta a las preguntaTema de un Tema
     *
     * @param tema
     * @return false si no es exitoso
     */
    public boolean ingresarRespuesta(Tema tema) {
        try {
            if (con == null || con.isClosed()) {
                con = bd.conexion();
            }
            Collection<PreguntaTema> preguntas = tema.getPreguntas();
            Iterator it = preguntas.iterator();
            while (it.hasNext()) {
                PreguntaTema pt = (PreguntaTema) it.next();
                pt.setTema(tema);
                String consulta = "UPDATE TemporalPT SET respuestaCorrecta = " + pt.getRespuestaCorrecta()
                        + " WHERE Tema_id = " + pt.getTema().getId() + " AND Pregunta_id = " + pt.getPregunta().getId();
                Statement sta = con.createStatement();
                sta.executeUpdate(consulta);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ingresando las respuestas " + ex);
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
     * Metodo para borrar todos los temas de la base de datos
     *
     * @return 1 para confirmar que el Delete fue exitosos, de lo contrario 0
     */
    public int borrarTemas() {
        try {
            String consulta;
            if (con == null || con.isClosed()) {
                con = bd.conexion();
            }
            consulta = "DELETE FROM tema";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas eliminando temas!. Error: " + ex);
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
     * Metodo para obtener atributos del tema Maestro
     *
     * @return Tema maestro
     */
    public Tema obtenerMaestro() {
        Tema maestro = new Tema();
        try {
            if (con == null || con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Tema WHERE id = 0";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                maestro.setJornada(rs.getString("jornada"));
                maestro.setSemestre(rs.getInt("semestre"));
                maestro.setYear(rs.getInt("year"));
                return maestro;
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta del maestro! Error: " + ex);
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
