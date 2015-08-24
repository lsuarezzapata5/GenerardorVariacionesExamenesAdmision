package LogicaNegocio;

import Configuracion.Ruta;
import DAO.CredencialTemaDAO;
import DTO.CredencialTema;
import DTO.Tema;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clase con la que se controlan las Credenciales
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class CredencialLN {

    private ArrayList credenciales = new ArrayList();
    private CredencialTemaDAO ctDAO = new CredencialTemaDAO();
    Ruta ruta = new Ruta();
    String rutaA = ruta.getRuta();
    private String rutaLeerCredenciales="";
    
    /**
     * Metodo para asignar la ruta donde se leeran las credenciales
     *
     * @return
     */
    public String getRutaLeerCredenciales() {
        return rutaLeerCredenciales;
    }

    /**
     * Metodo para asignar la ruta donde se leeran las credenciales
     *
     * @param rutaLeerCredenciales
     */
    public void setRutaLeerCredenciales(String rutaLeerCredenciales) {
        this.rutaLeerCredenciales = rutaLeerCredenciales;
    }
    /**
     * Metodo para leer datos desde un archivo de excel
     *
     * @param nombreArchivo
     * @return
     */
    public ArrayList leerArchivoExcel(String nombreArchivo, String nombreHoja) {

        List cellDataList = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(nombreArchivo);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet hssfSheet = workbook.getSheet(nombreHoja);
            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                while (iterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                }
                cellDataList.add(cellTempList);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error leyendo archivo de excel: " + e);
        }

        return credencialesToArray(cellDataList);

    }

    /**
     * Metodo para llenar un ArrayList con los datos de celda de una hoja de un
     * archivo de excel
     *
     * @param cellDataList lista de datos en la hoja
     * @return Arraylist con los datos obtenidos de las celdas de la hoja
     */
    private ArrayList credencialesToArray(List cellDataList) {

        for (int i = 0; i < cellDataList.size(); i++) {
            List cellTempList = (List) cellDataList.get(i);
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(3);
            String stringCellValue = hssfCell.toString();
            credenciales.add(stringCellValue);
        }
        Iterator<String> it = credenciales.iterator();
        while (it.hasNext()) {
            String element = it.next();

        }

        return credenciales;
    }

    /**
     *Metodo para escribir un archivo de excel con la relacion Tema-Credencial
     * @param nombreArchivo
     * @return el archivo de excel creado
     * @throws IOException
     */
    public File escribirArchivoExcel(String nombreArchivo) throws IOException {
        ArrayList credencialTemaAr = new ArrayList();
        credencialTemaAr = ctDAO.getCredencialTema();
        Iterator itcredencialTema = credencialTemaAr.iterator();
        CredencialTema credencialTema = new CredencialTema();
        String credencial = "";
        Tema temaT = new Tema();
        int tema = 0;
        String rutaArchivo = this.rutaA + "/" + nombreArchivo + ".xls";
        File archivoXLS = new File(rutaArchivo);
        if (archivoXLS.exists()) {
            archivoXLS.delete();
        }
        archivoXLS.createNewFile();
        Workbook libro = new HSSFWorkbook();
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        Sheet hoja = libro.createSheet("TemaXcredencial" + nombreArchivo);
        int f = 0;

        while (itcredencialTema.hasNext()) {
            Row fila = hoja.createRow(f);
            for (int c = 0; c < 2; c++) {
                Cell celda = fila.createCell(c);
                if (f == 0 && c == 0) {
                    celda.setCellValue("Tema");
                } else if (f == 0 && c == 1) {
                    celda.setCellValue("Credencial");
                } else if (f != 0 && c == 1) {
                    celda.setCellValue(credencial);
                } else {
                    celda.setCellValue(tema);
                }
            }

            f = f + 1;
            credencialTema = (CredencialTema) itcredencialTema.next();
            credencial = credencialTema.getCredencial();
            temaT = credencialTema.getTema();
            tema = temaT.getId();

        }

        libro.write(archivo);
        archivo.close();

        return archivoXLS;
    }

    /**
     * Metodo que dirige a la capa de persistencia para generar y guardar la
     * relacion CredencialTema 
     *
     * @param credenciales
     * @return HashMap con la relacion Credencial x Tema
     */
    public HashMap guardarCredencialeTema(ArrayList credenciales) {
        return ctDAO.generarCredencialTema(credenciales);

    }
}
