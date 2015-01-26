/*
 */
package org.decomisims.listeners;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.decomisims.app.Aplicacion;
import org.decomisims.modelo.Salarios;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class AreaListener implements ActionListener {

    private final JRadioButton a, b, df;
    private final JTextField minimo;
    private final Salarios salarios;

    public AreaListener(JRadioButton a, JRadioButton b, JRadioButton df,
            JTextField minimo, Salarios origen) {
        this.a = a;
        a.addActionListener(this);
        this.b = b;
        b.addActionListener(this);
        this.df = df;
        df.addActionListener(this);
        this.minimo = minimo;
        this.salarios = origen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Double min = 0d;
        if (e.getSource().equals(a) && a.isSelected()) {
            min = salarios.getSalarioA();
        } else if (e.getSource().equals(b) && b.isSelected()) {
            min = salarios.getSalarioB();
        } else  if (e.getSource().equals(df) && df.isSelected()){
            min = salarios.getSalarioDF();
        }
        minimo.setText(Aplicacion.DECIMAL_FORMAT.format(min));
    }

}
