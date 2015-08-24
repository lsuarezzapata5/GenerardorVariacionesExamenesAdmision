package LogicaNegocio;

import Configuracion.Ruta;
import DAO.PreguntaTemaDAO;
import DAO.RandomDAO;
import DAO.TemaDAO;
import DTO.PreguntaTema;
import DTO.Tema;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clase con la logica del negocio y controladores para los Temas
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class TemaLN {

    Ruta url = new Ruta();
    Ruta ruta = new Ruta();
    private PreguntaTemaDAO preguntaTemaDao = new PreguntaTemaDAO();
    private TemaDAO temaDAO = new TemaDAO();
    private LatexLN latexLn = new LatexLN();
    String fileNameWrite = ruta.getRuta();

    /**
     * Metodo que dirige a la capa de persistencia para cargar la informacion de
     * un tema
     *
     * @param idTema
     * @return Tema con la informacion cargada
     * @see TemaDAO#cargarTema(DTO.Tema)
     */
    public Tema cargarTema(int idTema) {
        Tema tema = new Tema();
        tema.setId(idTema);
        return temaDAO.cargarTema(tema);

    }

    /**
     * Metodo que dirige a la capa de persistencia para listar los temas
     * existentes
     *
     * @return Collection tipo Tema
     * @see TemaDAO#listarTemas()
     */
    public Collection<Tema> listarTemas() {
        return temaDAO.listarTemas();
    }

    /**
     * Metodo que dirige a la capa de persistencia para realizar el random de
     * preguntas
     *
     * @return 1 para confirmar el exito de la operacion, de lo contrario 0
     * @see RandomDAO#realizarRandomPreguntar()
     */
    public int hacerRandom() {
        try {
            RandomDAO random = new RandomDAO();
            LinkedList preguntas=random.realizarRandomPreguntar();
            CombinatoriaLN com = new CombinatoriaLN();
            com.realizarOrdenamiento(preguntas);
            return 1;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Random: " + ex);
        }
        return 0;
    }

    /**
     * Metodo que dirige a la capa de persistencia para ingresar las repsuestas
     * correctas
     *
     * @param tema
     * @see TemaDAO#ingresarRespuesta(DTO.Tema)
     */
    public void ingresarRespuesta(Tema tema) {
        temaDAO.ingresarRespuesta(tema);
    }

    /**
     * Metodo para desplegar las respuestas de los temas segun las respuestas
     * ingresadas al examen maestro
     *
     * @param maestro
     */
    public void desplegarRespuestas(Tema maestro) {
        Collection<Tema> temas = this.listarTemas();
        Iterator it = temas.iterator();
        while (it.hasNext()) {
            Tema tema = (Tema) it.next();
            temaDAO.cargarTema(tema);
            if (tema.getId() != 0) {
                Collection<PreguntaTema> preguntas = tema.getPreguntas();
                Iterator it2 = preguntas.iterator();
                while (it2.hasNext()) {
                    PreguntaTema preguntaTema = (PreguntaTema) it2.next();
                    Iterator it3 = maestro.getPreguntas().iterator();
                    while (it3.hasNext()) {
                        PreguntaTema preguntaTemaMaestro = (PreguntaTema) it3.next();
                        if (preguntaTemaMaestro.getPregunta().getId() == preguntaTema.getPregunta().getId()) {
                            preguntaTema.setRespuestaCorrecta(preguntaTemaMaestro.getRespuestaCorrecta());
                            break;
                        }
                    }
                }
                this.ingresarRespuesta(tema);
            }

        }
    }

    /**
     * Metodo para crear un conjunto de temas nuevos
     *
     * @param lk lista de temas a crear
     * @return 1 para cofnirmar el exito de la operacion
     */
    public int crearTema(LinkedList lk) {
        Iterator it = lk.iterator();
        Tema tema = new Tema();
        int ultimo = temaDAO.obtenerUltimoTema();
        tema.setId(ultimo + 1);
        Calendar fecha = new GregorianCalendar();
        java.util.Date util = new java.util.Date();
        Date date = new Date(util.getTime());
        tema.setFechaCreacion(date);
        Tema temaMaestro = temaDAO.obtenerMaestro();
        tema.setSemestre(temaMaestro.getSemestre());
        tema.setYear(temaMaestro.getYear());
        tema.setJornada(temaMaestro.getJornada());
        if (temaDAO.ingresarTema(tema) == 1) {
            PreguntaTemaDAO preguntaTemaDao1 = new PreguntaTemaDAO();
            while (it.hasNext()) {
                PreguntaTema preguntaTema = (PreguntaTema) it.next();
                Tema temaAux = new Tema();
                temaAux.setId(ultimo + 1);
                preguntaTema.setTema(temaAux);
                preguntaTemaDao1.ingresarPreguntaTemaEnTemporal(preguntaTema);
            }
        }
        return 1;
    }

    /**
     * Metodo para crear un examen maestro
     *
     * @param tema0
     * @throws IOException
     */
    public void crearMaestro(Tema tema0) throws IOException {
        Tema tema = new Tema();
        Calendar fecha = new GregorianCalendar();
        java.util.Date ju = fecha.getTime();
        java.sql.Date date = new java.sql.Date(ju.getTime());
        tema.setFechaCreacion(date);
        tema.setId(0);
        tema.setJornada(tema0.getJornada());
        tema.setSemestre(tema0.getSemestre());
        tema.setYear(tema0.getYear());
        TemaDAO tdao = new TemaDAO();
        this.reiniciarExamen();
        tdao.ingresarTema(tema);
        latexLn.nuevoExamen(tema, tema0.getYear(), tema0.getSemestre(), tema0.getJornada());
    }

    /**
     * Metodo que dirige a la clase experta LatexLN para imprimir un tema
     *
     * @param tema
     * @throws IOException
     */
    public void imprimir(Tema tema) throws IOException {
        latexLn.nuevoTema(tema, tema.getYear(), tema.getSemestre(), tema.getJornada());

    }

    /**
     * Metodo para limpiar y reiniciar el proceso para la ceacion de un Examen
     *
     * @return 1 para confirmar el exito de la operacion, de lo contrario 0
     * @throws IOException
     */
    public int reiniciarExamen() throws IOException {
        TemaDAO temaDao = new TemaDAO();
        PreguntaTemaDAO preguntaTemaDao = new PreguntaTemaDAO();
        if (preguntaTemaDao.borrarPreguntaTemas() == 1) {
            Collection<Tema> temas = listarTemas();
            Iterator it = temas.iterator();
            while (it.hasNext()) {
                Tema tema = (Tema) it.next();
                String ruta = url.getRuta() + "\\examen" + tema.getId();
                File f = new File(ruta);
                if (f.exists()) {
                    Runtime.getRuntime().exec("cmd /c " + url.getUnidad() + " && cd " + url.getRuta() + " && start /b /wait \n RD /S /Q examen" + tema.getId() + " \n exit");
                }
            }

            temaDao.borrarTemas();
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Metodo para escribir en un archivo de excel las respuestas de cada una de
     * las preguntas para cada tema
     *
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public File escribirArchivoExcel(String nombreArchivo) throws IOException {
        Collection preguntaTemaArray = new ArrayList<PreguntaTema>();
        Tema tema = new Tema();
        String rutaArchivo = fileNameWrite + "/" + nombreArchivo + ".xls";
        File archivoXLS = new File(rutaArchivo);
        if (archivoXLS.exists()) {
            archivoXLS.delete();
        }
        archivoXLS.createNewFile();
        Workbook libro = new HSSFWorkbook();
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        int j = 0;
        while (j <= 10) {
            tema = cargarTema(j);
            preguntaTemaArray = tema.getPreguntas();
            Iterator itPreguntaTemaArray = preguntaTemaArray.iterator();
            int pregunta = 0;
            String respuesta = "";
            Sheet hoja = libro.createSheet("Tema " + j);
            int f = 0;
            int a = preguntaTemaArray.size() + 1;
            while (f < a) {
                Row fila = hoja.createRow(f);
                for (int c = 0; c < 2; c++) {
                    Cell celda = fila.createCell(c);
                    if (f == 0 && c == 0) {
                        celda.setCellValue("Pregunta");
                    } else if (f == 0 && c == 1) {
                        celda.setCellValue("Respuesta");
                    } else if (f != 0 && c == 1) {
                        celda.setCellValue(respuesta);
                    } else {
                        celda.setCellValue(pregunta);
                    }
                }

                f = f + 1;
                if (f < a) {
                    PreguntaTema pt = (PreguntaTema) itPreguntaTemaArray.next();

                    pregunta = pt.getNumeroPregunta();

                    int rta = pt.getRespuestaCorrecta();
                    if (rta == 1) {
                        respuesta = "A";
                    } else if (rta == 2) {
                        respuesta = "B";
                    } else if (rta == 3) {
                        respuesta = "C";
                    } else if (rta == 4) {
                        respuesta = "D";
                    }
                }

            }
            j++;
        }
        libro.write(archivo);
        archivo.close();

        return archivoXLS;
    }

}
