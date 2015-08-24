
package LogicaNegocio;


import Configuracion.Ruta;
import DAO.CombinacionOpcionDAO;
import DAO.EnunciadoDAO;
import DAO.OpcionDAO;
import DAO.PreguntaDAO;
import DAO.PreguntaTemaDAO;
import DTO.CombinacionOpcion;
import DTO.Enunciado;
import DTO.Opcion;
import DTO.Pregunta;
import DTO.PreguntaTema;
import DTO.Tema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase con la que se gestionan las funcionalidades con la herramienta LATEX
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class LatexLN {

    private String codInicial;
    private String codFinal;
    private ArchivoLN adminArch = new ArchivoLN();
    private PreguntaDAO perguntaDao = new PreguntaDAO();
    Ruta url = new Ruta();
    
    /**
     * Metodo para crear un enunciado con la herramienta LATEX
     * @param enunciado
     * @return
     * @throws IOException
     * @throws InterruptedException 
     */
    public int crearEnunciado(Enunciado enunciado) throws IOException, InterruptedException {
        //Para crear un enunciado, primero obtiene la ruta donde se está almacenando la información LaTeX
        //Creamos la carpeta que contendrá toda la información del enunciado a  crear

        String nombre = "Enunciado" + enunciado.getId();
        String ruta = url.getRuta() + "\\" + nombre;
        Process p = Runtime.getRuntime().exec("cmd /C "+url.getUnidad()+" && cd "+url.getRuta()+" && mkdir " + nombre );
        enunciado.setUrl(url.getRutaSQL() + "\\\\" + nombre);

        Thread.sleep(500);
        /*Creamos el archivo que contendrá los paquetes e insertamos los paquetes requeridos
         *Creamos el archivo que contendrá el enunciado e insertamos los paquetes requeridos
         */

        //Definimos el codigo incial y final de LaTex
        String paq = "";
        String desc = "";
        if (enunciado.getPaquetes() != null) {
            paq = enunciado.getPaquetes();
        }
        if (enunciado.getDescripcion() != null) {
            desc = enunciado.getDescripcion();
        }
         codInicial = "\\documentclass[a4paper,10pt]{article}"
                + "\\usepackage[utf8]{inputenc}"
                + "\\usepackage[spanish]{babel}"
                 + "\\usepackage{times}"
                + "\n" + paq
                + "\n"
                + "\\title{Vista preliminar de Enunciado}"
                + "\n"
                + "\\usepackage{graphicx}"
                + "\n"
                + "\\begin{document}"
                + "\\twocolumn \n"
                + "\n"
                + "\\maketitle"
                + "\n";
        codFinal = "\\end{document}";
        //Unimos todas las partes para crear el documento completo de LaTeX para el enunciado
        String codigo = codInicial + "\n" + desc + "\n" + codFinal;
        //Creamos el archivo .Tex con el codigo creado anteriormente en la ruta especificada
        String nom = "Enunciado";
        String ext = ".tex";
        System.out.println(ruta);
        File archivo = new File(ruta, nom + ext);
        PrintWriter a = null;
        try {
            a = new PrintWriter(archivo);

        } catch (IOException ex) {
            Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.println(codigo);
        a.close();
        //Creamos el archivo .BAT para compilar LaTeX
        adminArch.crearBAT(ruta, nom + ext);
        //Ejecutamos el .BAT y verificamos si hay errores
      
        try {
            Process q = Runtime.getRuntime().exec("cmd /c start /wait " + "exec.bat");
            boolean band = false;
            q.waitFor();
            File archiv = new File(ruta + "\\" + nom + ".log");
            String hilera = adminArch.leerTxt(archiv);
            int i = hilera.length();
            for (int j = 0; j < i; j++) {
                if (hilera.charAt(j) == '?') {
                    JOptionPane.showMessageDialog(null, "Error");
                    band = true;
                }
            }
            if (!band) {
                Process q1 = Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+ruta+" && start "+ nom + ".pdf && exit");
                //Todo se ejecutó perfectamente, entonces realizamos el registro en la base de datos
                return 1;

            } else {
                 Process q1 = Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+url.getRuta()+" && start /b /wait \n RD /S /Q " + nombre + " \n exit");
                return (0);
            }
        } catch (IOException ex) {
              Process q1 = Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+url.getRuta()+" && start /b /wait \n RD /S /Q " + nombre + " \n exit");
            return (0);
        }
    }

    /**
     * Metodo para borrar un enunciado
     *
     * @param enunciado
     * @throws IOException
     */
    public void borrarEnunciado(Enunciado enunciado) throws IOException {
        EnunciadoDAO enDAO = new EnunciadoDAO();
        enDAO.cargarEnunciado(enunciado);
        Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + enunciado.getUrl() + " \n exit");
        if (enDAO.borrarEnunciado(enunciado.getId()) == 1) {
        }
    }

    /**
     * Metodo para crear una opcion con la herramienta LATEX
     *
     * @param opcion
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public int crearOpcion(Opcion opcion) throws IOException, InterruptedException {
        Ruta url = new Ruta();
        String nombre = "Opcion" + opcion.getId();
        Pregunta preguntaAux = new Pregunta();
        preguntaAux.setId(opcion.getPregunta().getId());
        PreguntaDAO preguntaDao = new PreguntaDAO();
        preguntaDao.cargarInformacionPregunta(preguntaAux);
        String ruta = preguntaAux.getUrl() + "\\" + nombre;
        System.out.println(ruta);
        Process p1 = Runtime.getRuntime().exec("cmd /c " + url.getUnidad()
                + " && cd " + preguntaAux.getUrl() + " && md " + nombre);
        opcion.setUrl(url.getRutaSQL() + "\\\\Enunciado" + preguntaAux.getEnunciado().getId()
                + "\\\\Pregunta" + preguntaAux.getId() + "\\\\" + nombre);
        System.out.println(opcion.getUrl());
        Thread.sleep(500);

       codInicial = "\\documentclass[a4paper,10pt]{article}"
                + "\\usepackage[utf8]{inputenc}"
                + "\\usepackage[spanish]{babel}"
                + "\\usepackage{times}"
                + "\n"
                  + "\\title{Vista preliminar de opción}"
                + "\n"
                + "\\usepackage{graphicx}"
                + "\n"
                + "\\begin{document}"
                + "\\twocolumn \n"
                + "\n"
                + "\\maketitle"
                + "\n";
        codFinal = "\\end{document}";
        String codigo = codInicial + "\n" + opcion.getDescripcion() + "\n" + codFinal;
        String nom = "Opcion";
        String ext = ".tex";
        File archivo = new File(ruta, nom + ext);
        PrintWriter a = null;
        try {
            a = new PrintWriter(archivo);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);

        }
        a.println(codigo);
        a.close();
        adminArch.crearBAT(ruta, nom + ext);
        return adminArch.ejecutarBAT(ruta, nom);

    }

    /**
     * Metodo para crear un nuevo tema con la herramienta LATEX
     * @param tema
     * @param year
     * @param sem
     * @param jornada
     * @throws IOException
     */
    public void nuevoTema(Tema tema, Integer year, Integer sem, String jornada) throws IOException {
        String rutaCom = url.getRaiz() + "\\Pruebas\\examen" + tema.getId();
        File folder = new File(rutaCom);
        if (folder.exists()) {
            Process q = Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+rutaCom+" && start examen.pdf");
            return;
        }
        Enunciado e = new Enunciado();
        EnunciadoDAO eD = new EnunciadoDAO();
        int cont = 1;
        ArrayList idsEnunciados = new ArrayList();
        Iterator it = tema.getPreguntas().iterator();
        int temp = -1;
        String body = "";
        String paquetes = "";
        while (it.hasNext()) {
            PreguntaTema preguntaTema = (PreguntaTema) it.next();
            Pregunta PreguntaAux = preguntaTema.getPregunta();
            PreguntaLN preguntaLn=new PreguntaLN();
            preguntaLn.cargarInfPreg(PreguntaAux);
            Enunciado enunciado = PreguntaAux.getEnunciado();

            if (enunciado.getId() != temp) {
                temp = enunciado.getId();
                if (enunciado.getPaquetes() != null) {
                    paquetes = paquetes + enunciado.getPaquetes() + " \n ";
                }
                if (enunciado.getDescripcion() != null) {
                    body = body + enunciado.getDescripcion() + " \n ";
                }
            }
            Pregunta pregunta = preguntaTema.getPregunta();
            CombinacionOpcionDAO combinacionOpcionDao = new CombinacionOpcionDAO();
            combinacionOpcionDao.cargarCombinacion(preguntaTema.getCombinacion());

            if (pregunta.getDescripcion() != null) {
                body = body + preguntaTema.getNumeroPregunta() + ". " +
                        pregunta.getDescripcion() + "\\newline \n \\newline \n  ";
            }
            ArrayList<Opcion> oo = (ArrayList) pregunta.getOpciones();
             CombinacionOpcion combinacionOpcion = preguntaTema.getCombinacion();
            if (oo.size() < 4) {
                continue;
            }
            String nom = "";
            if (oo.get(combinacionOpcion.getA() - 1) != null) {
                nom = nom + "A. " + oo.get(combinacionOpcion.getA() - 1).getDescripcion() + "\\newline \n ";
            }
            if (oo.get(combinacionOpcion.getB() - 1) != null) {
                nom = nom + "B. " + oo.get(combinacionOpcion.getB() - 1).getDescripcion() + "\\newline \n ";
            }
            if (oo.get(combinacionOpcion.getC() - 1) != null) {
                nom = nom + "C. " + oo.get(combinacionOpcion.getC() - 1).getDescripcion() + "\\newline \n ";
            }
            if (oo.get(combinacionOpcion.getD() - 1) != null) {
                nom = nom + "D. " + oo.get(combinacionOpcion.getD() - 1).getDescripcion() + "\\newline \n ";
            }
           
            body = body + nom + "\\newline \n";
        }

        cont++;

        codInicial = "\\documentclass[a4paper,10pt]{article}\n"
                + "\\usepackage[left=1.5cm,top=1cm,right=1.5cm,bottom=1cm]{geometry} \n"
                + "\\usepackage[utf8]{inputenc} \n"
                + "\\usepackage[spanish]{babel}\n"
                 + "\\usepackage{times}\n"
                + "\\usepackage{graphicx}\n"
                + paquetes
                + "\n"
                + "\n"
                + "\\title{ \n"
                + "\\begin{minipage}{12cm} \n"
                + "\\centerline {\\includegraphics{../../escudo.jpg}} \n"
                + "\\begin{center}"
                + "Vicerector\\'ia de Docencia"
                + "\\end{center}"
                + "Departamento de Admisiones y Registro"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\centerline {Examen de Admisi\\'on}"
                + "\\newline"
                + "\\newline"
                + "\\centerline {" + year + " - " + sem + "}"
                + "\\date{Jornada :  " + jornada + "}"
                + "\\author{Universidad de Antioquia}"
                + "\\end{minipage}"
                + "}"
                + "\\begin{document}\n"
                + "\n"
                + "\\maketitle\n"
                + "\\twocolumn \n"
                + "\\newpage \n"
                + "\\noindent \n";

        codFinal = "\\end{document}";

        this.crearExamen(year, codInicial, body, codFinal, tema);
    }
    
    /**
     * Metodo para crear un nuevo examen con la herramienta LATEX
     *
     * @param tema
     * @param year
     * @param sem
     * @param jornada
     * @throws IOException
     */
   public void nuevoExamen(Tema tema, Integer year, Integer sem, String jornada) throws IOException {
        String rutaCom = url.getRaiz()+"\\Pruebas\\examen" + tema.getId();
        File folder = new File(rutaCom);
        if (folder.exists()) {
              Process q1 = Runtime.getRuntime().exec("cmd /c start /b /wait \n RD /S /Q " + rutaCom + " \n exit");
           
        }
        Enunciado enunciado = new Enunciado();
        EnunciadoDAO enunciadoDao = new EnunciadoDAO();
        ArrayList codigosEnunciados = new ArrayList();
        int cont = 1;
        codigosEnunciados = enunciadoDao.listarId();
        String paquetes = "", body = "";
        Iterator i = codigosEnunciados.iterator();
        while (i.hasNext()) {
            int eCod = (int) i.next();
            enunciado.setId(eCod);
            enunciadoDao.cargarEnunciado(enunciado);
            if (enunciado.getPaquetes() != null) {
                paquetes = paquetes + enunciado.getPaquetes() + " \n ";
            }

            if (body != null) {
                body = body + enunciado.getDescripcion() + " \n  ";
            }

            Pregunta pregunta = new Pregunta();
            PreguntaDAO preguntaDao = new PreguntaDAO();
            ArrayList idPreguntas = new ArrayList();
            idPreguntas = preguntaDao.listarIdsPreguntasdeEnunciado(eCod);
            Iterator j = idPreguntas.iterator();

            while (j.hasNext()) {
                int idPregunta = (int) j.next();
                PreguntaTema preguntaTema = new PreguntaTema();
                CombinacionOpcion combinacionOpcion = new CombinacionOpcion();
                combinacionOpcion.setId(1);
                Pregunta pregunta1 = new Pregunta();
                pregunta1.setId(idPregunta);
                preguntaTema.setPregunta(pregunta1);
                preguntaTema.setRespuestaCorrecta(0);
                preguntaTema.setTema(tema);
                preguntaTema.setCombinacion(combinacionOpcion);
                preguntaTema.setNumeroPregunta(cont);
                PreguntaTemaDAO ptd = new PreguntaTemaDAO();
                ptd.ingresarPreguntaTema(preguntaTema);

                pregunta1.setId(idPregunta);
                preguntaDao.cargarInformacionPregunta(pregunta1);
                if (body != null) {
                    body = body + cont + ". " + pregunta1.getDescripcion() + "\\newline \\newline  \n ";
                }

                Opcion opcion = new Opcion();
                OpcionDAO opcionDao = new OpcionDAO();
                Collection<Opcion> codigosOpciones = new ArrayList();
                codigosOpciones = opcionDao.listarOpcionesdePregunta(idPregunta);
                Iterator k = codigosOpciones.iterator();
                while (k.hasNext()) {
                    Opcion OpcionAux = (Opcion) k.next();
                    int idOpcion = OpcionAux.getId();
                    opcion.setId(idOpcion);
                    Pregunta preg = new Pregunta();
                    preg.setId(OpcionAux.getPregunta().getId());
                    opcion.setPregunta(preg);
                    opcionDao.cargarOpcion(opcion);
                    if (opcion.getDescripcion() != null) {
                        String letra = "";
                        if (opcion.getId() == 1) {
                            letra = "A";
                        } else if (opcion.getId() == 2) {
                            letra = "B";
                        } else if (opcion.getId() == 3) {
                            letra = "C";
                        } else if (opcion.getId() == 4) {
                            letra = "D";
                        }
                        body = body + letra + ". " + opcion.getDescripcion() + "\\newline \n ";
                    }
                }
                body = body + "\\newline \n";
                cont++;
            }
        }

        codInicial = "\\documentclass[a4paper,10pt]{article}\n"
                + "\\usepackage[utf8]{inputenc} \n"
                + "\\usepackage[left=1.5cm,top=1cm,right=1.5cm,bottom=1cm]{geometry} \n"
                + "\\usepackage[spanish]{babel}\n"
                + "\\usepackage{times}\n"
                + "\\usepackage{graphicx}\n"
                + paquetes
                + "\n"
                + "\n"
                + "\\title{ \n"
                + "\\begin{minipage}{12cm} \n"
                + "\\centerline {\\includegraphics{../../escudo.jpg}} \n"
                + "\\begin{center}"
                + "Vicerector\\'ia de Docencia"
                + "\\end{center}"
                + "Departamento de Admisiones y Registro"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\newline"
                + "\\centerline {Examen de Admisi\\'on}"
                + "\\newline"
                + "\\newline"
                + "\\centerline {" + year + " - " + sem + "}"
                + "\\date{Jornada :  " + jornada + "}"
                + "\\author{Universidad de Antioquia}"
                + "\\end{minipage}"
                + "}"
                + "\\begin{document}\n"
                + "\n"
                + "\\maketitle\n"
                + "\\twocolumn \n"
                + "\\newpage \n"
                + "\\noindent \n";

        codFinal = "\\end{document}";

        this.crearExamen(tema.getYear(), codInicial, body, codFinal, tema);
    }
    /**
     * Metodo para crear un nuevo examen con la herramienta LATEX
     *
     * @param n
     * @param inicio
     * @param preguntas
     * @param fin
     * @param tema
     */
    public void crearExamen(int n, String inicio, String preguntas, String fin, Tema tema) {
        String nombre = "examen";
        String ext = ".tex";
        Ruta ruta = new Ruta();
        try {
            Process p = Runtime.getRuntime().exec("cmd /c  "+url.getUnidad()+" && cd "+ruta.getRuta()+" &&  md examen" + tema.getId());
            Thread.sleep(500);
            File archivo = new File(ruta.getRuta() + "\\examen" + tema.getId() + "\\" + nombre + ext);
            PrintWriter a = null;

            try {
                a = new PrintWriter(archivo);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);
            }

            String cm = inicio + preguntas + fin;
            a.println(cm);
            a.close();

            File bat = new File("exec.bat");
            a = null;
            try {
                a = new PrintWriter(bat);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);
            }
            String comando = url.getUnidad()+" \n cd " + ruta.getRuta() + "\\examen" + tema.getId() + "\n pdflatex " + nombre + ext + " \n start "+ nombre + ".pdf \n exit";
            a.print(comando);
            a.close();

            try {
                Process q = Runtime.getRuntime().exec("cmd /c start exec.bat");
            } catch (IOException ex) {
                Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);
     
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para borrar una pregunta
     *
     * @param pregunta
     * @return
     * @throws IOException
     */
    public int borrarPregunta(Pregunta pregunta) throws IOException {
        PreguntaDAO preguntaDao = new PreguntaDAO();
        pregunta = preguntaDao.cargarInformacionPregunta(pregunta);
        String comando = "cmd /c " + url.getUnidad() + " && cd " + url.getRuta()
                + "\\Enunciado" + pregunta.getEnunciado().getId()
                + " && start /b /wait \n RD /S /Q Pregunta"
                + pregunta.getId() + " \n exit";
        Process q1 = Runtime.getRuntime().exec(comando);
        if (preguntaDao.borrarPregunta(pregunta.getId()) == 1) {
            return 1;
        }
        return 0;
    }

    /**
     * Metodo para borrar una opcion
     *
     * @param opcion
     * @throws IOException
     */
    public void vaciarOpcion(Opcion opcion) throws IOException {
        OpcionDAO opcionDao = new OpcionDAO();
        PreguntaDAO preguntaDao = new PreguntaDAO();
        opcion = opcionDao.cargarOpcion(opcion);
        opcion.setPregunta(preguntaDao.cargarInformacionPregunta(opcion.getPregunta()));
        String comando = "cmd /c " + url.getUnidad() + " && cd " + opcion.getPregunta().getUrl()
                + " &&  start /b /wait \n RD /S /Q Opcion" + opcion.getId() + " \n exit";
        Process q1 = Runtime.getRuntime().exec(comando);
        if (opcionDao.vaciarOpcion(opcion.getId()) == 1) {

        }
    }

    /**
     * Metodo para crear una pregunta
     *
     * @param pregunta
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public int crearPregunta(Pregunta pregunta) throws IOException, InterruptedException {

        Ruta url = new Ruta();
        String nombre = "Pregunta" + pregunta.getId();
        Enunciado enunciado = new Enunciado();
        enunciado.setId(pregunta.getEnunciado().getId());
        EnunciadoDAO enunciadoDao = new EnunciadoDAO();
        enunciadoDao.cargarEnunciado(enunciado);
        String ruta = enunciado.getUrl() + "\\" + nombre;
        Process p1 = Runtime.getRuntime().exec("cmd /c " + url.getUnidad() + " && cd " + enunciado.getUrl() + " && md " + nombre);
        pregunta.setUrl(url.getRutaSQL() + "\\\\Enunciado" + pregunta.getEnunciado().getId() + "\\\\" + nombre);
        Thread.sleep(500);

        codInicial = "\\documentclass[a4paper,10pt]{article}"
                + "\\usepackage[utf8]{inputenc}"
                + "\\usepackage[spanish]{babel}"
                + "\\usepackage{times}"
                + "\n"
                + "\\title{Vista preliminar de Pregunta}"
                + "\n"
                + "\\usepackage{graphicx}"
                + "\n"
                + "\\begin{document}"
                + "\\twocolumn \n"
                + "\n"
                + "\\maketitle"
                + "\n";
        codFinal = "\\end{document}";
        String codigo = codInicial + "\n" + pregunta.getDescripcion() + "\n" + codFinal;
        String nom = "Pregunta";
        String ext = ".tex";
        File archivo = new File(ruta, nom + ext);
        PrintWriter a = null;
        try {
            a = new PrintWriter(archivo);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LatexLN.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.println(codigo);
        a.close();

        adminArch.crearBAT(ruta, nom + ext);
        return adminArch.ejecutarBAT(ruta, nom);

    }

}
