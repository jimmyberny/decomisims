package org.decomisims.app;

import javax.swing.JPanel;
import org.decomisims.error.AppError;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public interface Vista {

    public void init(Aplicacion app) throws AppError;
    
    public void mostrar() throws AppError;

    /**
     * Indica si la vista en cuestión puede ser cerrada.
     * <p>
     * En el caso del login, cada vez que se intente ingresar se invocara cerrar
     * el panel y solo cuando se autentique correctamente será replegado el
     * panel.
     *
     * @return Si la vista puede ser cerrada.
     */
    public boolean cerrar();
    
    public JPanel getContenido();
}
