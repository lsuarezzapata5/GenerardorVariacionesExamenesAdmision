package LogicaNegocio;

import DAO.AreaDAO;
import DAO.EnunciadoDAO;
import DAO.PreguntaDAO;
import DTO.Area;
import DTO.Autor;
import DTO.Enunciado;
import DTO.Pregunta;
import java.util.Collection;

/**
 * Clase con la logica del negocio y controladores para  los Enunciados
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class EnunciadoLN {

    private EnunciadoDAO enunciadoDao = new EnunciadoDAO();
    private PreguntaDAO preguntaDao = new PreguntaDAO();
    private AreaDAO areaDao = new AreaDAO();

    /**
     * Metodo que dirige a la capa de persistencia para eliminar un Enunciado
     *
     * @param idEnunciado
     * @see EnunciadoDAO#borrarEnunciado(int)
     */
    public void eliminarEnunciado(int idEnunciado) {
        enunciadoDao.borrarEnunciado(idEnunciado);
    }

    /**
     * Metodo que dirige a la capa de persistencia para listar los id de los
     * enunciados
     *
     * @return
     * @see EnunciadoDAO#listarId()
     */
    public Collection listarIds() {
        return enunciadoDao.listarId();
    }

    /**
     * Metodo que dirige a la capa de persistencia para listar los autores
     *
     * @return Collection con los autores
     * @see EnunciadoDAO#cargarAutores()
     */
    public Collection<Autor> listarAutorres() {
        return enunciadoDao.cargarAutores();
    }

    /**
     * Metodo que dirige a la capa de persistencia para obtener el id del ultimo
     * enunciado ingresado
     *
     * @param enunciado
     * @return id del ultimo enunciado ingresado
     * @see EnunciadoDAO#obtenerId(DTO.Enunciado)
     */
    public int obtenerId(Enunciado enunciado) {
        return enunciadoDao.obtenerId(enunciado);
    }

    /**
     * Metodo que dirige a la capa de persistencia para listar todas las areas
     *
     * @return Collection de tipo Area
     * @see AreaDAO#listarAreas()
     */
    public Collection<Area> listarAreas() {
        return areaDao.listarAreas();
    }

    /**
     * Metodo que dirige a la capa de persistencia para crear un enunciado
     *
     * @param enunciado
     * @return 1 para confirmar la operacion, de lo contrario 0
     * @see EnunciadoDAO#crearEnunciado(DTO.Enunciado)
     */
    public int crearEnunciado(Enunciado enunciado) {
        return enunciadoDao.crearEnunciado(enunciado);
    }

    /**
     * Metodo que dirige a la capa de persistencia para modificar un enunciado
     *
     * @param enunciado
     * @return 1 para confirmar la operacion, de lo contrario 0
     * @see EnunciadoDAO#modificarEnunciado(DTO.Enunciado)
     */
    public int modificarEnunciado(Enunciado enunciado) {
        return enunciadoDao.modificarEnunciado(enunciado);
    }

    /**
     * Metodo que dirige a la capa de persistencia para obtener el id de la
     * ultima pregunta ingresada
     *
     * @param pregunta
     * @return 1 para confirmar la operacion, de lo contrario 0
     * @see PreguntaDAO#obtenerUltimoIdPreguntaDeEnunciado(DTO.Pregunta)
     */
    public int obtenerUltimoCodigoOpcionPregunta(Pregunta pregunta) {
        return preguntaDao.obtenerUltimoIdPreguntaDeEnunciado(pregunta);
    }

    /**
     * Metodo que dirige a la capa de persistencia para cargar todos los
     * atributos de un enunciado
     *
     * @param enunciado
     * @see EnunciadoDAO#cargarEnunciado(DTO.Enunciado)
     */
    public void cargarEnunciado(Enunciado enunciado) {
        enunciadoDao.cargarEnunciado(enunciado);
    }

}
