package Configuracion;

/**
 * Clase con la configuracion de rutas
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class Ruta {

    private String rutaBase = "C:/Users/Luisa/Desktop/GeneradorVariacionesExamenesAdmision";

    public String getRutaBase() {
        return rutaBase;
    }

    public void setRutaBase(String rutaBase) {
        this.rutaBase = rutaBase;
    }
    private String raiz = "C:\\Users\\Luisa\\Desktop\\GeneradorVariacionesExamenesAdmision";
    private String raizSQL = "C:\\\\Users\\\\Luisa\\\\Desktop\\\\GeneradorVariacionesExamenesAdmision";
    private String ruta = raiz + "\\Pruebas";
    private String rutaSQL = raizSQL + "\\\\Pruebas";

    public String getRaiz() {
        return raiz;
    }

    public String getUnidad() {
        return raiz.substring(0, 2);
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public String getRaizSQL() {
        return raizSQL;
    }

    public void setRaizSQL(String raizSQL) {
        this.raizSQL = raizSQL;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRutaSQL() {
        return rutaSQL;
    }

    public void setRutaSQL(String rutaSQL) {
        this.rutaSQL = rutaSQL;
    }

}
