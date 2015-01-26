package org.decomisims.vistas;

import java.io.IOException;
import java.net.URL;
import javax.swing.JPanel;
import org.decomisims.app.Aplicacion;
import org.decomisims.app.Vista;
import org.decomisims.error.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class Home extends javax.swing.JPanel implements Vista {

    private static final Logger log = LoggerFactory.getLogger(Home.class);
    private Aplicacion app;

    public Home() {
        initComponents();
    }

    @Override
    public void init(Aplicacion app) throws AppError {
        this.app = app;
        try {
            URL res = getClass().getResource("bienvenida.html");
            jepContenido.setPage(res);
            
            URL var = getClass().getResource("variables.html");
            jepVariables.setPage(var);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        
        // Opciones generales
        ogGenerales.init(app);
        
        // Contenido
        conceptos.init(app, ogGenerales);
    }

    @Override
    public void mostrar() throws AppError {

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

        jtpMain = new javax.swing.JTabbedPane();
        jpConceptos = new javax.swing.JPanel();
        conceptos = new org.decomisims.vistas.Conceptos();
        jspConceptos = new javax.swing.JScrollPane();
        jepContenido = new javax.swing.JEditorPane();
        jspVariables = new javax.swing.JScrollPane();
        jepVariables = new javax.swing.JEditorPane();
        ogGenerales = new org.decomisims.vistas.OpcionesGenerales();

        setLayout(new java.awt.BorderLayout());

        jtpMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jpConceptos.setLayout(new java.awt.BorderLayout());
        jpConceptos.add(conceptos, java.awt.BorderLayout.CENTER);

        jtpMain.addTab("tab4", jpConceptos);

        jepContenido.setEditable(false);
        jspConceptos.setViewportView(jepContenido);

        jtpMain.addTab("Conceptos", jspConceptos);

        jspVariables.setViewportView(jepVariables);

        jtpMain.addTab("Variables", jspVariables);

        add(jtpMain, java.awt.BorderLayout.CENTER);
        add(ogGenerales, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.decomisims.vistas.Conceptos conceptos;
    private javax.swing.JEditorPane jepContenido;
    private javax.swing.JEditorPane jepVariables;
    private javax.swing.JPanel jpConceptos;
    private javax.swing.JScrollPane jspConceptos;
    private javax.swing.JScrollPane jspVariables;
    private javax.swing.JTabbedPane jtpMain;
    private org.decomisims.vistas.OpcionesGenerales ogGenerales;
    // End of variables declaration//GEN-END:variables
}
