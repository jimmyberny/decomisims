/*
 */
package org.decomisims.vistas;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.decomisims.app.Aplicacion;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class ValorExento extends javax.swing.JPanel {

    private Double referencia;

    public ValorExento() {
        initComponents();

        jtfValor.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                eval();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                eval();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                eval();
            }

            private void eval() {
                String str = jtfValor.getText();
                if (str.isEmpty()) {
                    jtfValor.setBackground(Aplicacion.DEFAULT_COLOR);
                } else {
                    try {
                        Double val = Double.parseDouble(str);
                        lblEstatus.setText(val <= referencia
                                ? "Exento"
                                : "Integrado");
                    } catch (NumberFormatException nfex) {
                        jtfValor.setBackground(Aplicacion.ERROR_COLOR);
                    }
                }
            }
        });
    }

    public void init(Double base, Double porcentaje) {
        referencia = base * porcentaje;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfValor = new javax.swing.JTextField();
        lblEstatus = new javax.swing.JLabel();

        jtfValor.setPreferredSize(new java.awt.Dimension(120, 26));

        lblEstatus.setPreferredSize(new java.awt.Dimension(120, 26));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jtfValor;
    private javax.swing.JLabel lblEstatus;
    // End of variables declaration//GEN-END:variables
}
