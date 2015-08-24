package DTO;

/**
 * Clase con la que se gestiona la informacion de los autores de los enunciados
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class Autor {

    /**
     * Codigo identificador del autor
     */
    private int id;
    /**
     * Nombre del autor
     */
    private String nombre;
    /**
     * Telefono del autor
     */
    private String telefono;
    /**
     * Correo Electronico del autor
     */
    private String correoElectronico;
    /**
     * Dependencia del autor
     */
    private String dependencia;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

}
