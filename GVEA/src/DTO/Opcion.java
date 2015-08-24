package DTO;

/**
 * Clase con la que se gestiona la informacion de las opciones
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class Opcion {
    /**
     * Identificador de la opcion 
     */
    private int id;
    /**
     * Pregunta a la que pertenece la opcion
     */
    private Pregunta pregunta;
    /**
     * url donde se ubica la carpeta de la opcion
     */
    private String url;
   /**
    * Orden de la opcion en la pregunta (Si es primero o ultimo)
    */
    private int orden;
    /**
     * Atributo para especificar que la posicion de una opcion es despues de otra
     */
    private Opcion despuesDe;
    /**
     * Descripcion de la opcion
     */
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Opcion getDespuesDe() {
        return despuesDe;
    }

    public void setDespuesDe(Opcion despuesDe) {
        this.despuesDe= despuesDe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
