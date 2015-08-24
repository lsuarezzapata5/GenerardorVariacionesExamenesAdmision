package Vista;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase controladora para las vistas
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class ExamenesCtrl {

    public static void main(String[] args) {
        Principal p = new Principal();
        p.show();

    }

    public void abrirCredenciales() {
        AdministrarCredenciales cred = new AdministrarCredenciales();
        cred.show();
    }

    public void abrirAdminEnunciados() {
        SeleccionarEnunciado admon = new SeleccionarEnunciado();
        admon.show();
    }

    public void abrirEnunciado(DTO.Enunciado en, int sel) {
        AdministrarEnunciado admon = null;
        try {
            admon = new AdministrarEnunciado(en, sel);
        } catch (ParseException ex) {
            Logger.getLogger(ExamenesCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        admon.show();
    }

    public void abrirPregunta(DTO.Pregunta p, int sel) {
        AdministrarPregunta admon = new AdministrarPregunta(p, sel);
        admon.show();

    }

    public void abrirOpcion(DTO.Opcion o, int sel) {
        AdministrarOpcion admon = new AdministrarOpcion(o, sel);
        admon.show();
    }

    public void abrirPrincipal() {
        Principal ppal = new Principal();
        ppal.show();
    }

    public void abrirRespuestas() {
        AdministrarRespuestas rta = new AdministrarRespuestas();
        rta.show();
    }

    public void abrirInformacionExamen() {
        InformacionExamen inf = new InformacionExamen();
        inf.show();
    }

}
