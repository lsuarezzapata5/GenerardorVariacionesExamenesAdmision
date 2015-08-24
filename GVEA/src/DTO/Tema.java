

package DTO;
import java.util.Collection;
import java.util.Date;

/**
 * Clase con la que se gestiona la informacion de los temas
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class Tema {

    /**
     * Identificador del tema
     */
    private int id;
    /**
     * Fecha de Creacion del tema
     */
    private Date FechaCreacion;
    /**
     * Año para el cual se crea el examen de admision
     */
    private Integer year;
    /**
     * Semestre para el cual se crea el examen de admision
     */
    private Integer semestre;
    /**
     * Jornada para la cual se crea el examen de admision
     */
    private String jornada;
    /**
     * Conjunto de PreguntaTema que contiene un tema
     */
    private Collection<PreguntaTema> preguntas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Collection<PreguntaTema> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Collection<PreguntaTema> preguntas) {
        this.preguntas = preguntas;
    }

}
