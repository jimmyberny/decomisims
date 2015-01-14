/*
 */
package org.decomisims.vistas;

import javax.swing.JOptionPane;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class MensajeFactory {

    public static void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
