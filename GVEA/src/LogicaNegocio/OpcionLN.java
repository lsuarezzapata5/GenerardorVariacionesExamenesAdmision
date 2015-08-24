package LogicaNegocio;

import DAO.OpcionDAO;
import DTO.Opcion;

/**
 * Clase con la logica del negocio y controladores para las opciones
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class OpcionLN {

    OpcionDAO opcionDao = new OpcionDAO();

    /**
     * Metodo que dirige a la capa de persistencia para crear una opcion
     *
     * @param opcion
     * @return 1 para confirmar el exito de la operacion, de lo contrario 0
     * @see OpcionDAO#crearOpcion(DTO.Opcion)
     */
    public int crearOpcion(Opcion opcion) {
        opcion.setId(opcionDao.obtenerUltimoIdOpcionPregunta(opcion));
        return opcionDao.crearOpcion(opcion);
    }

    /**
     * Metodo que dirige a la capa de persistencia para Modificar una opcion
     *
     * @param opcion
     * @return 1 para confirmar el exito de la operacion, de lo contrario 0
     * @see OpcionDAO#modificarOpcion(DTO.Opcion)
     */
    public int modificarOpcion(Opcion opcion) {
        return opcionDao.modificarOpcion(opcion);
    }

    /**
     * Metodo que dirige a la capa de persistencia para cargar la informacion de
     * una opcion
     *
     * @param opcion
     * @return opcion con la informacion cargada
     * @see OpcionDAO#cargarOpcion(DTO.Opcion)
     */
    public Opcion cargarOpcion(Opcion opcion) {
        return opcionDao.cargarOpcion(opcion);
    }
}
