package DTO;

/**
 * Relacion de las Credenciales con Tema
 * A cada credencial se le asigna un tema
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class CredencialTema {

    /**
     * Numero identificador de cada aspirante
     */
    private String credencial;
    /**
     * Tema asignado a la credencial
     */
    private Tema tema;

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

}
