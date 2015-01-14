package org.decomisims.vistas;

import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.decomisims.app.Aplicacion;
import org.decomisims.modelo.Salarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class ValorExentoOneroso extends javax.swing.JPanel {

    private static final Logger log = LoggerFactory.getLogger(ValorExentoOneroso.class);
    private Salarios salarios;

    public ValorExentoOneroso() {
        initComponents();

        DocumentListener dl = new DocumentListener() {

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
                if (jtfValor.getText().isEmpty()) {
                    jtfValor.setBackground(Aplicacion.DEFAULT_COLOR);
                    jtfCobro.setText(null);
                    lblEstatus.setText(null);
                } else {
                    String str = jtfValor.getText();
                    try {
                        Double val = Double.parseDouble(str);
                        jtfValor.setBackground(Color.white);
                    } catch (NumberFormatException nfex) {
                        jtfValor.setBackground(Aplicacion.ERROR_COLOR);
                    }
                }
            }
        };
        jtfValor.getDocument().addDocumentListener(dl);

        DocumentListener dl2 = new DocumentListener() {

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
                String tmp = jtfCobro.getText();
                if (!tmp.isEmpty()) {
                    try {
                        Double val = Double.parseDouble(tmp);
                        if (val >= (0.20 * salarios.getSalarioDF())) {
                            lblEstatus.setText("Exento");
                        } else {
                            lblEstatus.setText("Integrado");
                        }
                        if (val > getValor()) {
                            jtfCobro.setBackground(Aplicacion.ERROR_COLOR);
                        } else {
                            jtfCobro.setBackground(Aplicacion.DEFAULT_COLOR);
                        }
                    } catch (NumberFormatException nfex) {
                        jtfCobro.setBackground(Aplicacion.ERROR_COLOR);
                    }
                } else {
                    lblEstatus.setText("Integrado");
                }
            }
        };
        jtfCobro.getDocument().addDocumentListener(dl2);

    }

    public void setSalarios(Salarios salarios) {
        this.salarios = salarios;
    }

    public Double getValor() {
        try {
            return Double.parseDouble(jtfValor.getText());
        } catch (NumberFormatException nfex) {
            return 0d;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfValor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfCobro = new javax.swing.JTextField();
        lblEstatus = new javax.swing.JLabel();

        jtfValor.setPreferredSize(new java.awt.Dimension(120, 26));

        jLabel1.setText("Cobro oneroso");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 26));

        jtfCobro.setPreferredSize(new java.awt.Dimension(120, 26));

        lblEstatus.setToolTipText("La alimentación y la habitación cuando se entreguen en forma onerosa a los trabajadores; se entiende que son onerosas estas prestaciones cuando el trabajador pague por cada una de ellas, como mínimo, el veinte por ciento del salario mínimo general diario que rija en el Distrito Federal");
        lblEstatus.setPreferredSize(new java.awt.Dimension(120, 26));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jtfCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtfCobro;
    private javax.swing.JTextField jtfValor;
    private javax.swing.JLabel lblEstatus;
    // End of variables declaration//GEN-END:variables
}
