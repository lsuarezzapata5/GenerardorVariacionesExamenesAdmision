package DTO;

/**
 * Clase con la que se gestiona la informacion de las combinaciones para las opciones
 *Esta clase contiene los 24 ordenes diferentes para 4 opciones.
 * 
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class CombinacionOpcion {

    /**
     * Identificador de la combinacion
     */
    private int id;
    /**
     * Atributo para la primera posicion de la combinacion
     */
    private int A;
    /**
     * Atributo para la segunda posicion de la combinacion
     */
    private int B;
    /**
     * Atributo para la primera posicion de la combinacion
     */
    private int C;
    /**
     * Atributo para la primera posicion de la combinacion
     */
    private int D;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA() {
        return A;
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getD() {
        return D;
    }

    public void setD(int D) {
        this.D = D;
    }

}
