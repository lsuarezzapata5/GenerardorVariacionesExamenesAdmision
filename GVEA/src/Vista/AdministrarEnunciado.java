package Vista;

import DTO.Area;
import DTO.Autor;
import DTO.Enunciado;
import DTO.Pregunta;
import LogicaNegocio.EnunciadoLN;
import LogicaNegocio.LatexLN;
import LogicaNegocio.PreguntaLN;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * Clase para que el usuario Administre un Enunciado
 *
 * @author Daniel Restrepo Email: danistiven19@gmail.com
 * @author Julian Montoya Email: julianesten@gmail.com
 * @author Luisa Suarez Email: lsuarezzapata5@gmail.com
 */
public class AdministrarEnunciado extends javax.swing.JFrame {

    private Enunciado enunciado;
    ArrayList ids;
    ArrayList areas;
    ArrayList preguntas;
    private Pregunta pregunta;
    private int seleccion;
    private int existePrimero;
    private int existeUltimo;
    private EnunciadoLN enunciadoLn = new EnunciadoLN();
    private PreguntaLN preguntaLn = new PreguntaLN();
    private int ee;

    public AdministrarEnunciado(Enunciado enunciado1, int seleccion) throws ParseException {
        initComponents();
        setLocationRelativeTo(null);
        this.enunciado = enunciado1;
        Date now = new Date(System.currentTimeMillis());
        this.seleccion = seleccion;
        ids = new ArrayList();
        ids = (ArrayList) enunciadoLn.listarIds();
        DefaultComboBoxModel listaEnunciados = (DefaultComboBoxModel) cb_ListaEnunciados.getModel();
        DefaultComboBoxModel listaAutores = (DefaultComboBoxModel) cbAutor.getModel();
        listaEnunciados.addElement("Ninguno");
        Iterator i = ids.iterator();
        while (i.hasNext()) {
            String idEnunciados = i.next().toString();
            if (!idEnunciados.equals(Integer.toString(enunciado1.getId()))) {
                listaEnunciados.addElement(idEnunciados);
            }
        }
        cb_ListaEnunciados.setModel(listaEnunciados);
        areas = (ArrayList<Area>) enunciadoLn.listarAreas();
        listaEnunciados = (DefaultComboBoxModel) cb_ListaAreas.getModel();
        listaEnunciados.removeAllElements();
        i = areas.iterator();
        int select = 0;
        while (i.hasNext()) {
            Area area = (Area) i.next();
            int idArea = area.getId();
            if (enunciado1.getArea() != null) {
                if (idArea == enunciado1.getArea().getId()) {
                    select = idArea;
                }
            }
            listaEnunciados.addElement(area.getId() + " - " + area.getNombre());
        }
        cb_ListaAreas.setModel(listaEnunciados);

        Collection<Autor> autores = enunciadoLn.listarAutorres();
        listaAutores.removeAllElements();
        i = autores.iterator();
        int select1 = 0;
        while (i.hasNext()) {
            Autor autor = (Autor) i.next();
            int idAutor = autor.getId();
            if (enunciado1.getAutor() != null) {
                if (idAutor == enunciado1.getAutor().getId()) {
                    select1 = idAutor;
                }
            }
            listaAutores.addElement(autor.getId() + " - " + autor.getNombre());
        }

        cb_ListaAreas.setModel(listaEnunciados);
        // 1 para modificar, 2 para crear
        if (seleccion == 1) {

            cb_OrdenEnunciados.setSelectedIndex(enunciado1.getOrden() - 1);
            cbAutor.setSelectedIndex(enunciado1.getAutor().getId() - 1);
            java.util.Date d = new SimpleDateFormat("YY-MM-dd").parse(enunciado1.getFechaCreacion());
            if (enunciado1.getDespuesDe().getId() == 0) {
                cb_ListaEnunciados.setSelectedItem("Ninguno");
            } else {
                cb_ListaEnunciados.setSelectedIndex(enunciado1.getDespuesDe().getId() - 1);
            }

            cb_ListaAreas.setSelectedIndex(select - 1);
            btn_AgregarPregunta.setEnabled(true);
        } else {

            this.enunciado.setId(enunciadoLn.obtenerId(enunciado1));

        }
        actualizarPreg();
        lbl_CodigoEnunciado.setText(Integer.toString(this.enunciado.getId()));
        lbl_NombreEnunciado.setText("Enunciado " + Integer.toString(this.enunciado.getId()));
    }

    private void actualizarPreg() {
        cb_ListaPreguntas.removeAllItems();
        enunciadoLn.cargarEnunciado(enunciado);
        preguntas = new ArrayList();
        preguntas = (ArrayList) preguntaLn.listarIds(enunciado.getId());
        if (preguntas.size() == 0) {
            btn_editarPregunta.setEnabled(false);
            btn_EliminarPregunta.setEnabled(false);
        } else {
            DefaultComboBoxModel listaPreguntas = (DefaultComboBoxModel) cb_ListaPreguntas.getModel();
            int k = 0;
            while (k < preguntas.size()) {
                listaPreguntas.addElement(Integer.toString((int) preguntas.get(k)));
                k++;
            }
            cb_ListaPreguntas.setModel(listaPreguntas);
            btn_editarPregunta.setEnabled(true);
            btn_EliminarPregunta.setEnabled(true);
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
        lbl_CodigoEnunciado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_VerEnunciado = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cb_OrdenEnunciados = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cb_ListaEnunciados = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cb_ListaAreas = new javax.swing.JComboBox();
        cbAutor = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btn_editarPregunta = new javax.swing.JButton();
        btn_EliminarPregunta = new javax.swing.JButton();
        btn_AgregarPregunta = new javax.swing.JButton();
        cb_ListaPreguntas = new javax.swing.JComboBox();
        btn_cancelarEnunciado = new javax.swing.JButton();
        btn_GuardarEnunciado = new javax.swing.JButton();
        lbl_NombreEnunciado = new javax.swing.JLabel();
        lblInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Codigo:");

        lbl_CodigoEnunciado.setText(" ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enunciado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setName(""); // NOI18N

        btn_VerEnunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_VerEnunciado.setText("Ver Enunciado");
        btn_VerEnunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VerEnunciadoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Despues de:");

        cb_OrdenEnunciados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_OrdenEnunciados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Primero", "Ultimo" }));
        cb_OrdenEnunciados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_OrdenEnunciadosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Orden:");

        cb_ListaEnunciados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_ListaEnunciados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ListaEnunciadosActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Autor:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Area:");

        cb_ListaAreas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_ListaAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ListaAreasActionPerformed(evt);
            }
        });

        cbAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAutorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_VerEnunciado)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(cb_ListaEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_OrdenEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel7)))
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(cb_ListaAreas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_VerEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ListaEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_OrdenEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_ListaAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pregunta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btn_editarPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_editarPregunta.setText("Editar");
        btn_editarPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPreguntaActionPerformed(evt);
            }
        });

        btn_EliminarPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_EliminarPregunta.setText("Eliminar");
        btn_EliminarPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarPreguntaActionPerformed(evt);
            }
        });

        btn_AgregarPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_AgregarPregunta.setText("Agregar Pregunta");
        btn_AgregarPregunta.setEnabled(false);
        btn_AgregarPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarPreguntaActionPerformed(evt);
            }
        });

        cb_ListaPreguntas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cb_ListaPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btn_editarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_EliminarPregunta))
                    .addComponent(btn_AgregarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 80, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_editarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_EliminarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ListaPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_AgregarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cancelarEnunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cancelarEnunciado.setText("Volver");
        btn_cancelarEnunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarEnunciadoActionPerformed(evt);
            }
        });

        btn_GuardarEnunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_GuardarEnunciado.setText("Guardar");
        btn_GuardarEnunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarEnunciadoActionPerformed(evt);
            }
        });

        lbl_NombreEnunciado.setText(" ");

        lblInformacion.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl_NombreEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_CodigoEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancelarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(btn_GuardarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_CodigoEnunciado))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_NombreEnunciado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_GuardarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInformacion)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VerEnunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VerEnunciadoActionPerformed
        VerEnunciado ver = new VerEnunciado(enunciado, seleccion);
        ver.show();
    }//GEN-LAST:event_btn_VerEnunciadoActionPerformed

    private void cb_ListaEnunciadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ListaEnunciadosActionPerformed

    }//GEN-LAST:event_cb_ListaEnunciadosActionPerformed

    private void cb_ListaAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ListaAreasActionPerformed

    }//GEN-LAST:event_cb_ListaAreasActionPerformed

    private void btn_editarPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPreguntaActionPerformed
        pregunta = new Pregunta();
        pregunta.setId((int) preguntas.get(Integer.parseInt(cb_ListaPreguntas.getSelectedItem().toString()) - 1));
        preguntaLn.cargarInfPreg(pregunta);
        ExamenesCtrl ex = new ExamenesCtrl();
        ex.abrirPregunta(pregunta, 1);
        this.hide();
    }//GEN-LAST:event_btn_editarPreguntaActionPerformed

    private void btn_EliminarPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarPreguntaActionPerformed
        Pregunta pregunta1 = new Pregunta();
        pregunta1.setId((Integer.parseInt(cb_ListaPreguntas.getSelectedItem().toString())));
        LatexLN latexLn = new LatexLN();
        try {
            if (latexLn.borrarPregunta(pregunta1) == 1) {
                JOptionPane.showMessageDialog(this, "Pregunta borrada correctamente!");
                actualizarPreg();
            }
        } catch (IOException ex) {
            Logger.getLogger(AdministrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EliminarPreguntaActionPerformed

    private void btn_AgregarPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarPreguntaActionPerformed
        ExamenesCtrl ex = new ExamenesCtrl();
        Pregunta pregunta1 = new Pregunta();
        Enunciado enunciadoAux = new Enunciado();
        enunciadoAux.setId(enunciado.getId());
        pregunta1.setEnunciado(enunciadoAux);

        ex.abrirPregunta(pregunta1, 2);
        this.hide();
    }//GEN-LAST:event_btn_AgregarPreguntaActionPerformed

    private void btn_cancelarEnunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarEnunciadoActionPerformed
        ExamenesCtrl ex = new ExamenesCtrl();
        ex.abrirAdminEnunciados();
        this.hide();
    }//GEN-LAST:event_btn_cancelarEnunciadoActionPerformed

    private void btn_GuardarEnunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarEnunciadoActionPerformed
        Autor autor = new Autor();
        autor.setId((cbAutor.getSelectedIndex() + 1));
        enunciado.setAutor(autor);
        Area area = new Area();
        area.setId(cb_ListaAreas.getSelectedIndex() + 1);
        enunciado.setArea(area);
        Calendar calendario = new GregorianCalendar();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String year = Integer.toString(calendario.get(Calendar.YEAR));
        enunciado.setFechaCreacion(year + "/" + mes + "/" + dia);
        Enunciado enunciadoAux = new Enunciado();
        if (cb_ListaEnunciados.getSelectedItem().equals("Ninguno")) {
            enunciadoAux.setId(0);
        } else {
            if ((cb_OrdenEnunciados.getSelectedIndex()) != 0) {
                JOptionPane.showMessageDialog(this, "Verifique sus condiciones, no pueden ingresar dos condicionamientos diferentes");
                return;
            }
            enunciadoAux.setId(cb_ListaEnunciados.getSelectedIndex() + 1);
        }
        enunciado.setDespuesDe(enunciadoAux);

        enunciado.setOrden(cb_OrdenEnunciados.getSelectedIndex() + 1);
        LatexLN lt = new LatexLN();
        try {
            if (lt.crearEnunciado(enunciado) == 1) {
                if (seleccion == 2) {
                    if (enunciadoLn.crearEnunciado(enunciado) == 1) {
                        lblInformacion.setText("Enunciado " + enunciado.getId() + " almacenado correctamente!");
                        btn_AgregarPregunta.setEnabled(true);
                    }
                } else {

                    if (enunciadoLn.modificarEnunciado(enunciado) == 1) {
                        lblInformacion.setText("Enunciado " + enunciado.getId() + " modificado correctamente!");
                    }
                }

            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(AdministrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_GuardarEnunciadoActionPerformed

    private void cb_OrdenEnunciadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_OrdenEnunciadosActionPerformed
        if (this.existePrimero == 1 && cb_OrdenEnunciados.getSelectedIndex() == 0) {//cero es orden primero, 1 orden ultimo, 2 ningun orden
            JOptionPane.showMessageDialog(null, "No pueden existir dos enunciados con orden primero!");
            return;
        }
        if (this.existePrimero == 1 && cb_OrdenEnunciados.getSelectedIndex() == 1) {//cero es orden primero, 1 orden ultimo, 2 ningun orden
            JOptionPane.showMessageDialog(null, "No pueden existir dos enunciados con orden ultimo!");
        }

    }//GEN-LAST:event_cb_OrdenEnunciadosActionPerformed

    private void cbAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAutorActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdministrarEnunciado(null, 0).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(AdministrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AgregarPregunta;
    private javax.swing.JButton btn_EliminarPregunta;
    private javax.swing.JButton btn_GuardarEnunciado;
    private javax.swing.JButton btn_VerEnunciado;
    private javax.swing.JButton btn_cancelarEnunciado;
    private javax.swing.JButton btn_editarPregunta;
    private javax.swing.JComboBox cbAutor;
    private javax.swing.JComboBox cb_ListaAreas;
    private javax.swing.JComboBox cb_ListaEnunciados;
    private javax.swing.JComboBox cb_ListaPreguntas;
    private javax.swing.JComboBox cb_OrdenEnunciados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lbl_CodigoEnunciado;
    private javax.swing.JLabel lbl_NombreEnunciado;
    // End of variables declaration//GEN-END:variables
}
