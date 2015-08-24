package DTO;

import java.util.Collection;

/**
 * Clase con la que se gestiona la informacion de los enunciados
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class Enunciado {
    /**
     * Identificador del enunciado
     */
    private int id;
    /**
     * url donde se ubican la carpeta del enunciado
     */
    private String url;
    /**
     *Fecha de Creacion del enunciado
     */
    private String fechaCreacion;
    /**
     * Orden del enunciado en el examen (Si es primero o ultimo)
     */
    private int orden;
    /**
     * Descripción del enunciado
     */
    private String descripcion;
    /**
     * Autor del enunciado
     */
    private Autor autor;
    /**
     * Area del enunciado
     */
    private Area area;
    /**
     * Atributo para especificar que la posicion de un enunciado es despues de otro
     */
    private Enunciado despuesDe;
    /**
     * FALTA
     */
    private String paquetes;
    /**
     * Conjunto de preguntas que pertenecen a un enunciado
     */
    private Collection<Pregunta> preguntas;

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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Enunciado getDespuesDe() {
        return despuesDe;
    }

    public void setDespuesDe(Enunciado despuesDe) {
        this.despuesDe = despuesDe;
    }

    public String getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(String paquetes) {
        this.paquetes = paquetes;
    }

    public Collection<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Collection<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
