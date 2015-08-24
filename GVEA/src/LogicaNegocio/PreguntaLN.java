package LogicaNegocio;

import DAO.PreguntaTemaDAO;
import DAO.PreguntaDAO;
import DAO.OpcionDAO;
import DTO.Opcion;
import DTO.Pregunta;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase con la logica del negocio y controladores para las preguntas
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class PreguntaLN {

    private PreguntaTemaDAO preguntaTemaDao = new PreguntaTemaDAO();
    private OpcionDAO opcionDao = new OpcionDAO();
    private PreguntaDAO preguntaDao = new PreguntaDAO();

    /**
     * Metodo que dirige a la capa de persistencia para listar los ids de las
     * preguntas pertenecientes a un enunciado
     *
     * @param idEnunciado
     * @return Collection tipo Pregunta
     * @see PreguntaDAO#listarIdsPreguntasdeEnunciado(int)
     */
    public Collection<Pregunta> listarIds(int idEnunciado) {
        return preguntaDao.listarIdsPreguntasdeEnunciado(idEnunciado);
    }

    /**
     * Metodo que dirige a la capa de persistencia para obtener el id de la
     * ultima pregunta ingresada para un enunciado
     *
     * @param pregunta
     * @return id de la ultima pregunta
     * @see PreguntaDAO#obtenerUltimoIdPreguntaDeEnunciado(DTO.Pregunta)
     */
    public int obtenerUltimoIdPreguntaDeEnunciado(Pregunta pregunta) {
        return preguntaDao.obtenerUltimoIdPreguntaDeEnunciado(pregunta);

    }

    /**
     * Metodo que dirige a la capa de persistencia para crear una pregunta en la
     * base de datos
     *
     * @param pregunta
     * @return 1 para confirmar el exito de la operacion, de lo contrario 0
     * @see PreguntaDAO#crearPregunta(DTO.Pregunta)
     */
    public int crearPregunta(Pregunta pregunta) {
        return preguntaDao.crearPregunta(pregunta);
    }

    /**
     * Metodo que dirige a la capa de persistencia para modificar una pregunta
     *
     * @param pregunta
     * @return 1 para confirmar el exito de la operacion, de lo contrario 0
     * @see PreguntaDAO#modificarPregunta(DTO.Pregunta)
     */
    public int modificarPregunta(Pregunta pregunta) {
        return preguntaDao.modificarPregunta(pregunta);
    }

    /**
     * Metodo que dirige a la capa de persistencia para obtener el id de la
     * ultima opcion ingresada para una pregunta
     *
     * @param opcion
     * @return id de la ultima opcion
     * @see PreguntaDAO#obtenerUltimoIdPreguntaDeEnunciado(DTO.Pregunta)
     */
    public int obtenerUltimoIdOpcionPregunta(Opcion opcion) {
        return opcionDao.obtenerUltimoIdOpcionPregunta(opcion);
    }

    /**
     * Metodo que dirige a la capa de persistencia para cargar la informacion de
     * una pregunta
     *
     * @param pregunta
     * @see PreguntaDAO#cargarInformacionPregunta(DTO.Pregunta)
     */
    public void cargarInfPreg(Pregunta pregunta) {
        preguntaDao.cargarInformacionPregunta(pregunta);
        pregunta.setOpciones(opcionDao.listarOpcionesdePregunta(pregunta.getId()));

    }

    /**
     * Metodo que dirige a la capa de persistencia para listar las opciones de
     * una pregunta
     *
     * @param pregunta
     * @return Collection tipo Opcion
     * @see OpcionDAO#listarOpcionesdePregunta(int)
     */
    public Collection<Opcion> listarOpcionesDePRegunta(Pregunta pregunta) {
        Collection<Opcion> ids = new ArrayList();
        preguntaDao.cargarInformacionPregunta(pregunta);
        ids = opcionDao.listarOpcionesdePregunta(pregunta.getId());
        return ids;

    }
}
