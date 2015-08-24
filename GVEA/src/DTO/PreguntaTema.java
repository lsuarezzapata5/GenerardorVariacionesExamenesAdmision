package DTO;
/**
 * Relacion de una pregunta con respecto a un tema, 
 * esta relacion es fundamental para realizar las variaciones.
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class PreguntaTema {

    /**
     * Tema al que pertenece la relacion PreguntaTema
     */
    private Tema tema;
    /**
     * Pregunta con la que se va a hacer la relacion PreguntaTema
     */
    private Pregunta pregunta;
    /**
     * Respuesta correcta a la pregunta
     */
    private int respuestaCorrecta;
    /**
     * Combinacion que se realizo con las opciones de la pregunta
     */
    private CombinacionOpcion combinacion;
    /**
     * Numero consecutivo de la pregunta segun el tema al que pertenece
     */
    private int numeroPregunta;

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public CombinacionOpcion getCombinacion() {
        return combinacion;
    }

    public void setCombinacion(CombinacionOpcion combinacion) {
        this.combinacion = combinacion;
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

}
