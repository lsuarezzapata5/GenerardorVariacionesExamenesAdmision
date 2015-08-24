
package DTO;

/**
 * Clase con la que se gestiona la informacion de las areas de conocimiento
 * @author Daniel Restrepo
 * Email: danistiven19@gmail.com
 * @author Julian Montoya
 * Email: julianesten@gmail.com
 * @author Luisa Suarez
 * Email: lsuarezzapata5@gmail.com
 */

public class Area {

    /**
     * Codigo identificador del Area
     */
    private int id;
    /**
     * Nombre del Area
     */
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
