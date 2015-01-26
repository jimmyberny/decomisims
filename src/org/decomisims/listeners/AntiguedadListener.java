/*
 */
package org.decomisims.listeners;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.decomisims.app.Aplicacion;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class AntiguedadListener implements DocumentListener {

    private final JTextField years;
    private final JTextField dias;
    private final JTextField prima;
    private final JTextField primaDias;

    public AntiguedadListener(JTextField years, JTextField dias, JTextField prima, JTextField primaDias) {
        this.years = years;
        years.getDocument().addDocumentListener(this);

        this.dias = dias;
        this.prima = prima;
        prima.getDocument().addDocumentListener(this);

        this.primaDias = primaDias;
    }

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
        String year = years.getText();
        if (!year.isEmpty()) {
            try {
                int ant = Integer.parseInt(year);
                int vac = 0;
                if (ant == 1) {
                    vac = 6;
                } else if (ant == 2) {
                    vac = 8;
                } else if (ant == 3) {
                    vac = 10;
                } else if (ant == 4) {
                    vac = 12;
                } else if (ant < 10) {
                    vac = 14;
                } else if (ant < 15) {
                    vac = 16;
                } else if (ant < 20) {
                    vac = 18;
                } else if (ant < 25) {
                    vac = 20;
                } else {
                    vac = 22;
                }
                dias.setText(String.valueOf(vac));
                // Calcular la prima
                double pp = Double.parseDouble(prima.getText());
                primaDias.setText(Aplicacion.DECIMAL_FORMAT.format((pp * vac) / 100));
            } catch (NumberFormatException nfex) {
                primaDias.setText("0");
                dias.setText("0");
            }
        } else {
            primaDias.setText("0");
            dias.setText("0");
        }
    }

}
