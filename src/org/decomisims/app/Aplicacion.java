/*
 */
package org.decomisims.app;

import java.awt.Color;
import org.decomisims.error.AppError;
import org.decomisims.modelo.EscalaISR;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public interface Aplicacion {
    
    public static final Color DEFAULT_COLOR = Color.white;
    public static final Color ERROR_COLOR = new Color(16730955);

    public String getProperty(String name);

    public String getProperty(String name, String def);
    
    public void mostrarTarea(String tarea) throws AppError;
    
    // Especificas
    public EscalaISR getEscala();
}
