package LogicaNegocio;

import DAO.CombinacionOpcionDAO;
import DAO.PreguntaDAO;
import DAO.EnunciadoDAO;
import DAO.OpcionDAO;
import DTO.Enunciado;
import DTO.Opcion;
import DTO.Pregunta;
import DTO.PreguntaTema;
import DTO.CombinacionOpcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 * Clase con la que se realizan las combinatorias para realizar las variaciones
 * de Un Examen
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class CombinatoriaLN {

    /**
     *
     * @param preguntas
     * @return LinkedList de PreguntaTemas
     * @throws SQLException
     */
    public LinkedList realizarOrdenamiento(LinkedList preguntas) throws SQLException {
        
        LinkedList preguntaTemas = new LinkedList();        
        preguntas = ordenar(preguntas);
        int combinacion = 1;
        int contador = 1;
        Iterator it = preguntas.iterator();
        while (it.hasNext()) {
            Pregunta pregunta = (Pregunta) it.next();
            combinacion = verificarCombinacion(pregunta);
            PreguntaTema preguntaTema = new PreguntaTema();
            CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
            combinacionOpcion.setId(combinacion);
            preguntaTema.setCombinacion(combinacionOpcion);
            preguntaTema.setPregunta(pregunta);
            preguntaTema.setNumeroPregunta(contador);
            preguntaTemas.add(preguntaTema);
            contador++;
        }

        TemaLN temaLn = new TemaLN();
        temaLn.crearTema(preguntaTemas);
        return preguntaTemas;
    }

    /**
     *
     * @param linkedList
     * @return
     */
    public LinkedList ordenar(LinkedList linkedList) {
        LinkedList linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        EnunciadoDAO enunciadoDao = new EnunciadoDAO();
        while (it.hasNext()) {
            Object ob = it.next();
            if (ob != null && ob != "") {
                Pregunta pregunta = (Pregunta) ob;
                Enunciado enunciado = new Enunciado();
                enunciado.setId(pregunta.getEnunciado().getId());
                enunciadoDao.cargarEnunciado(enunciado);

                if (enunciado.getDespuesDe().getId() != 0) {
                    linkedList2 = verificarCondicionEnunciado(linkedList2, pregunta);
                } else {
                    linkedList2 = verificarPreguntas(linkedList2, pregunta);
                }
            }
        }

        return linkedList2;
    }

    /**
     * Metodo que selecciona una de las posibles 24 CombinacionOpcion, segun las
     * condiciones de orden y depsuesDe
     *
     * @param Pregunta
     * @return el id de la CombinacionOpcion seleccionada
     */
    public int verificarCombinacion(Pregunta Pregunta) {
        Vector vector = llenarVec(Pregunta);
        Random r = new Random();

        CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
        int seleccionado = 0;
        int tamañoVector = vector.size();
        seleccionado = r.nextInt(tamañoVector + 1);
        if (seleccionado != 0) {
            combinacionOpcion = (CombinacionOpcion) vector.get(seleccionado - 1);
        } else {
            combinacionOpcion = (CombinacionOpcion) vector.get(seleccionado);
        }

        for (int kk = 0; kk < vector.size(); kk++) {
            OpcionDAO opcionDao = new OpcionDAO();

            Collection<Opcion> opciones = (ArrayList<Opcion>) opcionDao.listarOpcionesdePregunta(Pregunta.getId());
            Iterator i = opciones.iterator();
            int temp = 0;
            while (i.hasNext()) {
                Object oj = i.next();
                if (oj != null && oj != "") {
                    Opcion opcion = (Opcion) oj;
                    if (combinacionOpcion.getA() == opcion.getId()) {
                        if (opcion.getDespuesDe().getId() != 0 || opcion.getOrden() == 2) {
                            temp = 1;
                        }
                    } else if (combinacionOpcion.getD() == opcion.getId()) {
                        if (opcion.getOrden() == 1) {
                            temp = 1;
                        } else if (opcion.getDespuesDe().getId() != 0) {
                            if (combinacionOpcion.getB() != opcion.getDespuesDe().getId()) {

                                temp = 1;
                            }
                        }
                    } else {
                        if (opcion.getOrden() == 1 || opcion.getOrden() == 2) {
                            temp = 1;
                            continue;
                        }
                        if ((combinacionOpcion.getC() == opcion.getId()
                                && opcion.getDespuesDe().getId() != 0
                                && opcion.getDespuesDe().getId() != combinacionOpcion.getB())
                                || (combinacionOpcion.getB() == opcion.getId()
                                && opcion.getDespuesDe().getId() != 0
                                && opcion.getDespuesDe().getId() != combinacionOpcion.getA())) {

                            temp = 1;
                        }
                    }

                }
            }
            if (temp != 0) {
                break;
            } else {
                return combinacionOpcion.getId();
            }
        }
        return 1;
    }

    /**
     *
     * @param pregunta
     * @return
     */
    public Vector llenarVec(Pregunta pregunta) {
        OpcionDAO opcionDao = new OpcionDAO();
        CombinacionOpcionDAO combinacionOpcionDao = new CombinacionOpcionDAO();
        Vector vector1 = new Vector();
        int consult = 0;
        Vector vector = new Vector();
        for (int ii = 0; ii < 4; ii++) {
            vector.add(0);
        }
        Iterator iteradorOpcion = pregunta.getOpciones().iterator();
        while (iteradorOpcion.hasNext()) {
            Opcion opcion = (Opcion) iteradorOpcion.next();

            if (opcion.getOrden() == 1) {
                vector.set(0, opcion.getId());
            } else if (opcion.getOrden() == 2) {
                vector.set(3, opcion.getId());
            } else if (opcion.getDespuesDe().getId() != 0) {
                for (int h = 0; h < vector.size(); h++) {
                    Object ob1 = vector.get(h);
                    if (ob1 != null) {
                        Opcion aux = new Opcion();
                        aux.setId((Integer) ob1);
                        aux.setPregunta(pregunta);
                        opcionDao.cargarOpcion(aux);
                        if (aux.getId() == opcion.getDespuesDe().getId()) {
                            vector.add(h + 1, opcion.getId());
                            break;
                        }
                    }
                }

                if (vector1.isEmpty()) {

                    for (int k = 0; k < 23; k++) {
                        CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
                        combinacionOpcion.setId(k);
                        combinacionOpcionDao.cargarCombinacion(combinacionOpcion);
                        if ((combinacionOpcion.getB() == opcion.getId()
                                && combinacionOpcion.getA() == opcion.getDespuesDe().getId())
                                || (combinacionOpcion.getC() == opcion.getId()
                                && combinacionOpcion.getB() == opcion.getDespuesDe().getId())
                                || (combinacionOpcion.getD() == opcion.getId()
                                && combinacionOpcion.getC() == opcion.getDespuesDe().getId())) {
                            consult = 1;
                            vector1.add(combinacionOpcion);
                        }
                    }
                } else {
                    int tam = vector1.size();
                    for (int k = 0; k < tam; k++) {
                        if (vector1.get(k) != null) {
                            CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
                            combinacionOpcion = (CombinacionOpcion) vector1.get(k);
                            if (!((combinacionOpcion.getB() == opcion.getId()
                                    && combinacionOpcion.getA() == opcion.getDespuesDe().getId())
                                    || (combinacionOpcion.getC() == opcion.getId()
                                    && combinacionOpcion.getB() == opcion.getDespuesDe().getId())
                                    || (combinacionOpcion.getD() == opcion.getId()
                                    && combinacionOpcion.getC() == opcion.getDespuesDe().getId()))) {

                                consult = 1;
                                vector1.set(k, null);
                            }
                        }
                    }
                }

            }
        }
        Vector def = new Vector();
        if (consult == 1) {

            int tamanoVector = vector1.size();
            for (int kk = 0; kk < tamanoVector; kk++) {
                if (vector1.get(kk) != null) {
                    CombinacionOpcion combinacionOpcion = (CombinacionOpcion) vector1.get(kk);

                    if ((Integer) vector.get(0) == 0 || combinacionOpcion.getA() == (Integer) vector.get(0)) {
                        if ((Integer) vector.get(1) == 0 || combinacionOpcion.getB() == (Integer) vector.get(1)) {
                            if ((Integer) vector.get(2) == 0 || combinacionOpcion.getC() == (Integer) vector.get(2)) {
                                if ((Integer) vector.get(3) == 0 || combinacionOpcion.getD() == (Integer) vector.get(3)) {
                                    def.add(combinacionOpcion);
                                }

                            }

                        }
                    }
                }

            }
        } else {

            for (int kk = 1; kk <= 23; kk++) {

                CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
                combinacionOpcion.setId(kk);
                combinacionOpcionDao.cargarCombinacion(combinacionOpcion);
                if ((Integer) vector.get(0) == 0 || combinacionOpcion.getA() == (Integer) vector.get(0)) {
                    if ((Integer) vector.get(1) == 0 || combinacionOpcion.getB() == (Integer) vector.get(1)) {
                        if ((Integer) vector.get(2) == 0 || combinacionOpcion.getC() == (Integer) vector.get(2)) {
                            if ((Integer) vector.get(3) == 0 || combinacionOpcion.getD() == (Integer) vector.get(3)) {
                                def.add(combinacionOpcion);
                            }

                        }

                    }
                }

            }

        }
        if (def.isEmpty()) {
            return vector1;
        }
        return def;
    }
//Verifica que en la lista nueva no haya otra preg del mismo enunciado o que no haya 
    //en la lista nueva un enunciado que tenga como condicion su propio enunciado en despuesde

    /**
     *
     * @param preguntas
     * @param pregunta
     * @return
     */
    public LinkedList verificarPreguntas(LinkedList preguntas, Pregunta pregunta) {
        int contador = 0;
        EnunciadoDAO enunciadoDao = new EnunciadoDAO();
        Iterator it = preguntas.iterator();
        Pregunta preguntaAux = null;

        int temp = 0;
        while (it.hasNext()) {
            preguntaAux = (Pregunta) it.next();
            Enunciado enunciado = new Enunciado();
            enunciado.setId(preguntaAux.getEnunciado().getId());
            enunciadoDao.cargarEnunciado(enunciado);
            if (enunciado.getId() != pregunta.getEnunciado().getId() && temp == 1) {
                preguntas.add(contador, pregunta);
                return preguntas;
            }

            if (enunciado.getDespuesDe().getId() == pregunta.getEnunciado().getId()) {

                preguntas.add(contador, pregunta);
                temp = 1;
                break;
            } else if (enunciado.getId() == pregunta.getEnunciado().getId()) {
                if (pregunta.getDespuesDe().getId() != 0) {
                    if (preguntaAux.getId() == pregunta.getDespuesDe().getId()) {
                        preguntas.add(contador + 1, pregunta);
                        temp = 1;
                        break;
                    } else if (preguntaAux.getDespuesDe().getId() == pregunta.getId()) {
                        preguntas.add(contador, pregunta);
                        temp = 1;
                        break;
                    }
                } else if (pregunta.getOrden() == 1) {
                    preguntas.add(contador, pregunta);
                    temp = 1;
                    break;
                } else if (pregunta.getOrden() == 2) {
                    preguntas.add(pregunta);
                    temp = 1;
                    break;
                }

            }

            contador++;
        }
        if (temp == 0) {
            preguntas.add(pregunta);
        }
        return preguntas;

    }

    /**
     *
     * @param lk
     * @param pregunta
     * @return
     */
    public LinkedList verificarCondicionEnunciado(LinkedList lk, Pregunta pregunta) {
        EnunciadoDAO enunciadoDao = new EnunciadoDAO();
        Enunciado enunciado = new Enunciado();
        enunciado.setId(pregunta.getEnunciado().getId());
        enunciadoDao.cargarEnunciado(enunciado);
        int despuesDe = enunciado.getDespuesDe().getId();

        Pregunta preguntaAux = (Pregunta) lk.getLast();
        if (despuesDe == preguntaAux.getEnunciado().getId()) {
            lk.add(preguntaAux);
        } else {
            Iterator it = lk.iterator();
            int cont = 1;
            int temp = 0;
            while (it.hasNext()) {
                Pregunta pregunta2 = (Pregunta) it.next();
                if (pregunta2.getEnunciado().getId() == despuesDe) {
                    temp = cont;
                }
                cont++;
            }
            if (temp == 0) {
                lk.add(pregunta);
            } else {
                lk.add(temp, pregunta);
                return lk;
            }
        }
        return lk;
    }

}
