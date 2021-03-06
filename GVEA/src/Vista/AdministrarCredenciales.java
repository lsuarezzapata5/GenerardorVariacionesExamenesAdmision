package Vista;

import LogicaNegocio.CredencialLN;
import DAO.CredencialTemaDAO;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase para que el usuario Administre las credenciales
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */

public class AdministrarCredenciales extends javax.swing.JFrame {

    CredencialLN credencialLn = new CredencialLN();

    public AdministrarCredenciales() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtRutaCredenciales = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSeleccionarCredenciales = new javax.swing.JButton();
        btnGuardarCredenciales = new javax.swing.JButton();
        btnVolverCredenciales = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        txt_nombreHoja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Credenciales");

        txtRutaCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaCredencialesActionPerformed(evt);
            }
        });

        jLabel2.setText("Ingrese ruta del archivo con las credenciales:");

        btnSeleccionarCredenciales.setText("Seleccionar archivo");
        btnSeleccionarCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarCredencialesActionPerformed(evt);
            }
        });

        btnGuardarCredenciales.setText("Guardar");
        btnGuardarCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCredencialesActionPerformed(evt);
            }
        });

        btnVolverCredenciales.setText("Volver");
        btnVolverCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverCredencialesActionPerformed(evt);
            }
        });

        btnVer.setText("Ver Tema x Credencial");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        txt_nombreHoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreHojaActionPerformed(evt);
            }
        });

        jLabel3.setText("Ingrese el nombre de la hoja donde se ecnuentran las credenciales:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnVolverCredenciales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarCredenciales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtRutaCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionarCredenciales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nombreHoja, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRutaCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarCredenciales))
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombreHoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCredenciales)
                    .addComponent(btnVer)
                    .addComponent(btnVolverCredenciales))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRutaCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaCredencialesActionPerformed

    }//GEN-LAST:event_txtRutaCredencialesActionPerformed

    private void btnSeleccionarCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarCredencialesActionPerformed
        JFileChooser elegir = new JFileChooser();
        int opcion = elegir.showOpenDialog(btnSeleccionarCredenciales);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
            String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
            System.out.println("El nombre del archivo es: " + nombre);
            System.out.println("El path del archivo es: " + pathArchivo);// TODO add your handling code here:
            txtRutaCredenciales.setText(pathArchivo);
            credencialLn.setRutaLeerCredenciales(pathArchivo);
        }
    }//GEN-LAST:event_btnSeleccionarCredencialesActionPerformed

    private void btnGuardarCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCredencialesActionPerformed
        btnVer.setEnabled(true);
        ArrayList credenciales = new ArrayList();
        credenciales = credencialLn.leerArchivoExcel(credencialLn.getRutaLeerCredenciales(),txt_nombreHoja.getText() );
        int i = 0;
        CredencialTemaDAO credencialTemaDao = new CredencialTemaDAO();
        HashMap credencialTemaHs = credencialTemaDao.generarCredencialTema(credenciales);
        Iterator it = credencialTemaHs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry map = (Map.Entry) it.next();
            String[] o = {map.getKey().toString(), map.getValue().toString()};
        }
        System.out.println("Credencial-Tema guardado con existo");
    }//GEN-LAST:event_btnGuardarCredencialesActionPerformed

    private void btnVolverCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverCredencialesActionPerformed
        ExamenesCtrl exa = new ExamenesCtrl();
        exa.abrirPrincipal();
        this.hide();
    }//GEN-LAST:event_btnVolverCredencialesActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        try {
            String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo que contendrá la relacion Tema-Credencial");
            Desktop.getDesktop().open(credencialLn.escribirArchivoExcel(nombreArchivo));
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "Problemas al momento de crear el archivo Tema-Credencial! Error: " + e);
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void txt_nombreHojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreHojaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreHojaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdministrarCredenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarCredenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarCredenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarCredenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarCredenciales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCredenciales;
    private javax.swing.JButton btnSeleccionarCredenciales;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton btnVolverCredenciales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtRutaCredenciales;
    private javax.swing.JTextField txt_nombreHoja;
    // End of variables declaration//GEN-END:variables

}
