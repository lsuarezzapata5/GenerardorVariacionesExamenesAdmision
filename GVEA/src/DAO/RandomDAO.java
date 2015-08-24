
package DAO;

import DTO.Pregunta;
import LogicaNegocio.CombinatoriaLN;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * Clase para realizar la persistencia de los objetos TemporalPT
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class RandomDAO {

    private BD bd;
    private Connection con;

    /**
     * Constructor de la clase donde se llama al metodo que realiza la conexion
     * a las base de datos
     */
    public RandomDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    /**
     * Metodo para realizar el random de las preguntas
     *
     * @return 0 en caso de que la transaccion falle
     * @throws SQLException
     */
    public LinkedList realizarRandomPreguntar() throws SQLException {
        PreguntaDAO preguntaDao=new PreguntaDAO();
        OpcionDAO opcionDao=new OpcionDAO();
        PreguntaTemaDAO tema = new PreguntaTemaDAO();
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "";
            Statement sta = con.createStatement();

            if (!tema.existePreguntaTema(0)) {
                consulta = "INSERT INTO TemporalPT SELECT * FROM PreguntaTema WHERE Tema_id = 0 ORDER BY RAND()";
                int rs = sta.executeUpdate(consulta);
            }

            consulta = "SELECT * FROM TemporalPT WHERE Tema_id = 0 ORDER BY RAND()";
            ResultSet rse = sta.executeQuery(consulta);
            LinkedList preguntas = new LinkedList();
            while (rse.next()) {
            Pregunta pregunta = new Pregunta();
            pregunta.setId(rse.getInt("Pregunta_id"));
            preguntaDao.cargarInformacionPregunta(pregunta);
            pregunta.setOpciones(opcionDao.listarOpcionesdePregunta(pregunta.getId()));
            preguntas.add(pregunta);
        }
            return preguntas;
            

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
