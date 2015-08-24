package DTO;

import java.util.Collection;

/**
 * Clase con la que se gestiona la informacion de las preguntas
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class Pregunta {
    /**
     * Identificador de la pregunta
     */
    private int id;
    /**
     * url donde se ubican la carpeta de las preguntas
     */
    private String url;
    /**
     * Enunciado al que pertenece la pregunta
     */
    private Enunciado enunciado;
    /**
     * Orden de la pregunta en el examen (Si es primero o ultimo)
     */
    private int orden;
    /**
     * Atributo para especificar que la posicion de una pregunta es despues de otra
     */
    private Pregunta despuesDe;
    /**
     * Descripcion de la pregunta
     */
    private String descripcion;
    /**
     * Conjunto de opciones que pertenecen a la pregunta
     */
    private Collection<Opcion> opciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Enunciado getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(Enunciado enunciado) {
        this.enunciado = enunciado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Pregunta getDespuesDe() {
        return despuesDe;
    }

    public void setDespuesDe(Pregunta despuesDe) {
        this.despuesDe = despuesDe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(Collection<Opcion> opciones) {
        this.opciones = opciones;
    }

   

}
