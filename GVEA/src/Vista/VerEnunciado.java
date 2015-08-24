package Vista;

import DTO.Enunciado;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase para que el usuario Vea la descripcion del enunciado
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class VerEnunciado extends javax.swing.JFrame {

    String paquetes;
    String descripcion;

    private Enunciado enunciado;

    public VerEnunciado(Enunciado enunciado1, int seleccion) {
        initComponents();
        setLocationRelativeTo(null);
        this.enunciado = enunciado1;
        if (seleccion == 1) {
            txt_paquetes.setText(enunciado1.getPaquetes());
            txt_enunciado.setText(enunciado1.getDescripcion());
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_paquetes = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_enunciado = new javax.swing.JTextArea();
        btn_Cancelar = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();
        btn_SeleccionarImagen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Paquetes latex");

        txt_paquetes.setColumns(20);
        txt_paquetes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_paquetes.setRows(5);
        jScrollPane1.setViewportView(txt_paquetes);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Enunciado");

        txt_enunciado.setColumns(20);
        txt_enunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_enunciado.setRows(5);
        txt_enunciado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_enunciadoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txt_enunciado);

        btn_Cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Cancelar.setText("Volver");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        btn_Guardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Guardar.setText("Guardar");
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });

        btn_SeleccionarImagen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_SeleccionarImagen.setText("Seleccionar imagen");
        btn_SeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_SeleccionarImagen)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(267, 267, 267))
                                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addComponent(btn_Guardar)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SeleccionarImagen)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Guardar)
                    .addComponent(btn_Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        this.hide();
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed

        if (txt_enunciado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción");
            txt_enunciado.requestFocus();
            return;
        }

        String textoEnunciado = txt_enunciado.getText();
        char comillaSimple = 39;
        char comillaDoble = 34;
        String nuevoTexto = textoEnunciado.replace(comillaSimple, comillaDoble);
        txt_enunciado.setText(nuevoTexto);
        enunciado.setPaquetes(txt_paquetes.getText());
        enunciado.setDescripcion(txt_enunciado.getText());
        this.hide();
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btn_SeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarImagenActionPerformed
        JFileChooser elegir = new JFileChooser();
        int opcion = elegir.showOpenDialog(btn_SeleccionarImagen);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
            String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
            String path = pathArchivo.replace('\\', '/');
            //txt_enunciado.setText("\\includegraphics{"+path +"}"+ txt_enunciado.getText());
            txt_enunciado.insert("\\includegraphics{" + path + "}", txt_enunciado.getCaretPosition());

        }
    }//GEN-LAST:event_btn_SeleccionarImagenActionPerformed

    private void txt_enunciadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_enunciadoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_enunciadoKeyPressed

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
            java.util.logging.Logger.getLogger(VerEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerEnunciado(null, 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton btn_SeleccionarImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txt_enunciado;
    private javax.swing.JTextArea txt_paquetes;
    // End of variables declaration//GEN-END:variables
}
