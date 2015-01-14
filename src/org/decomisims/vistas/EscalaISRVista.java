/*
 */
package org.decomisims.vistas;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.decomisims.app.Aplicacion;
import org.decomisims.app.Main;
import org.decomisims.app.Vista;
import org.decomisims.error.AppError;
import org.decomisims.modelo.EscalaISR;
import org.decomisims.modelo.RangoISR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class EscalaISRVista extends javax.swing.JPanel implements Vista {

    private static final Logger log = LoggerFactory.getLogger(EscalaISRVista.class);

    public EscalaISRVista() {
        initComponents();
        jtfCantidadPrueba.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                calc();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calc();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calc();
            }

            private void calc() {
                String str = jtfCantidadPrueba.getText();
                if (!str.isEmpty()) {
                    try {
                        Double val = Double.parseDouble(str);
                        EscalaISR.Resultado res = EscalaISR.ESCALA.evaluar(val);

                        jtfCuotaFija.setText(Main.MONEDA.format(res.getCuotaFija()));
                        jtfExcedente.setText(Main.MONEDA.format(res.getExcedente()));
                        jlPorcentajeExc.setText(Main.PORCENTAJE.format(res.getPorcentajeExcedente()) + " del excedente");
                        jtfExcedenteRelativo.setText(Main.MONEDA.format(res.getExcedenteRelativo()));
                        jtfTotal.setText(Main.MONEDA.format(res.getTotal()));
                    } catch (NumberFormatException nfex) {
                        log.error(nfex.getMessage(), nfex);
                    }
                }
            }
        });
    }

    @Override
    public void init(Aplicacion app) throws AppError {
        TableModel tm = new AbstractTableModel() {

            @Override
            public int getRowCount() {
                return EscalaISR.ESCALA.getNumRangos();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public String getColumnName(int column) {
                String res = null;
                switch (column) {
                    case 0:
                        res = "Límite Inferior";
                        break;
                    case 1:
                        res = "Límite Superior";
                        break;
                    case 2:
                        res = "Cuota Fija";
                        break;
                    case 3:
                        res = "% sobre excedente del límite inferior";
                        break;
                }
                return res;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                RangoISR row = EscalaISR.ESCALA.getRango(rowIndex);
                String res = "";
                switch (columnIndex) {
                    case 0:
                        res = Main.MONEDA.format(row.getMin());
                        break;
                    case 1:
                        res = Main.MONEDA.format(row.getMax());
                        break;
                    case 2:
                        res = Main.MONEDA.format(row.getCuota());
                        break;
                    case 3:
                        res = Main.PORCENTAJE.format(row.getExcedente());
                        break;
                }
                return res;
            }
        };
        jtEscala.setModel(tm);
    }

    @Override
    public void mostrar() throws AppError {
        jtfCantidadPrueba.setText(null);
    }

    @Override
    public boolean cerrar() {
        return true;
    }

    @Override
    public JPanel getContenido() {
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEscala = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfExcedenteRelativo = new javax.swing.JTextField();
        jtfExcedente = new javax.swing.JTextField();
        jlCantidad = new javax.swing.JLabel();
        jtfCuotaFija = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfCantidadPrueba = new javax.swing.JTextField();
        jlPorcentajeExc = new javax.swing.JLabel();
        jtfTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(jtEscala);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Excedente");
        jLabel1.setPreferredSize(new java.awt.Dimension(180, 26));

        jtfExcedenteRelativo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfExcedenteRelativo.setPreferredSize(new java.awt.Dimension(160, 26));

        jtfExcedente.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfExcedente.setPreferredSize(new java.awt.Dimension(160, 26));

        jlCantidad.setText("Cantidad");
        jlCantidad.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfCuotaFija.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfCuotaFija.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Total");
        jLabel4.setPreferredSize(new java.awt.Dimension(180, 26));

        jtfCantidadPrueba.setPreferredSize(new java.awt.Dimension(160, 26));
        jtfCantidadPrueba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCantidadPruebaKeyTyped(evt);
            }
        });

        jlPorcentajeExc.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlPorcentajeExc.setText("x% del excente");
        jlPorcentajeExc.setPreferredSize(new java.awt.Dimension(180, 26));

        jtfTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTotal.setPreferredSize(new java.awt.Dimension(160, 26));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Cuota fija");
        jLabel2.setPreferredSize(new java.awt.Dimension(180, 26));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCantidadPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCuotaFija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfExcedente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jlPorcentajeExc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfExcedenteRelativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCantidadPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCuotaFija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfExcedente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfExcedenteRelativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPorcentajeExc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel3.setBackground(new java.awt.Color(254, 254, 254));
        jLabel3.setFont(new java.awt.Font("Oxygen-Sans", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tabla ISR Mensual para 2014");
        jLabel3.setOpaque(true);
        add(jLabel3, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfCantidadPruebaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCantidadPruebaKeyTyped

    }//GEN-LAST:event_jtfCantidadPruebaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCantidad;
    private javax.swing.JLabel jlPorcentajeExc;
    private javax.swing.JTable jtEscala;
    private javax.swing.JTextField jtfCantidadPrueba;
    private javax.swing.JTextField jtfCuotaFija;
    private javax.swing.JTextField jtfExcedente;
    private javax.swing.JTextField jtfExcedenteRelativo;
    private javax.swing.JTextField jtfTotal;
    // End of variables declaration//GEN-END:variables
}
