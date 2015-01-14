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
public class NumberInputListener implements DocumentListener {

    private final JTextField field;

    public NumberInputListener(JTextField field) {
        this.field = field;
        field.getDocument().addDocumentListener(this);
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
        String val = field.getText();
        if (val.isEmpty()) {
            field.setBackground(Aplicacion.DEFAULT_COLOR);
        } else {
            try {
                Double.parseDouble(val);
                field.setBackground(Aplicacion.DEFAULT_COLOR);
            } catch (NumberFormatException nfex) {
                field.setBackground(Aplicacion.DEFAULT_COLOR);
            }
        }
    }

}
