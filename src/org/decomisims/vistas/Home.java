package org.decomisims.vistas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFileChooser;
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

    private void crearReporte() {
        // File destiny
        File pdf = seleccionarArchivo();
        if (pdf != null) {
            // All world
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc,
                        new FileOutputStream(pdf));

                doc.open();
                // Titulo
                Paragraph par = new Paragraph();
                par.setAlignment(Paragraph.ALIGN_CENTER);

                Phrase phr = new Phrase();
                phr.add("Reporte comparativo DECOMISIM");
                par.add(phr);
                doc.add(par);

                // Datos generales
                par = new Paragraph();
                par.setFont(new Font(Font.getFamily("Arial"), Font.BOLD, 14));
                phr = new Paragraph("Datos del empleado");
                par.add(phr);
                doc.add(par);
                
                doc.close();
            } catch (DocumentException dex) {
                log.error(dex.getMessage(), dex);
            } catch (FileNotFoundException nfex) {
                log.error(nfex.getMessage(), nfex);
            }
        }
    }

    private File seleccionarArchivo() {
        File sel = null;
        String home = System.getProperty("user.home");
        // Get user home
        JFileChooser chooser = new JFileChooser(home);
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
            sel = chooser.getSelectedFile();
            try {
                log.info("{}, {}, {}", sel.getCanonicalPath(), sel.isFile(), sel.exists());
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }

        return sel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpMain = new javax.swing.JTabbedPane();
        jpConceptos = new javax.swing.JPanel();
        conceptos = new org.decomisims.vistas.Conceptos();
        jpControles = new javax.swing.JPanel();
        jbGenerarReporte = new javax.swing.JButton();
        jspConceptos = new javax.swing.JScrollPane();
        jepContenido = new javax.swing.JEditorPane();
        jspVariables = new javax.swing.JScrollPane();
        jepVariables = new javax.swing.JEditorPane();
        ogGenerales = new org.decomisims.vistas.OpcionesGenerales();

        setLayout(new java.awt.BorderLayout());

        jtpMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jpConceptos.setLayout(new java.awt.BorderLayout());
        jpConceptos.add(conceptos, java.awt.BorderLayout.NORTH);

        jbGenerarReporte.setText("Generar reporte");
        jbGenerarReporte.setPreferredSize(new java.awt.Dimension(180, 28));
        jbGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGenerarReporteActionPerformed(evt);
            }
        });
        jpControles.add(jbGenerarReporte);

        jpConceptos.add(jpControles, java.awt.BorderLayout.CENTER);

        jtpMain.addTab("Decomisims", jpConceptos);

        jepContenido.setEditable(false);
        jspConceptos.setViewportView(jepContenido);

        jtpMain.addTab("Conceptos", jspConceptos);

        jspVariables.setViewportView(jepVariables);

        jtpMain.addTab("Variables", jspVariables);

        add(jtpMain, java.awt.BorderLayout.CENTER);
        add(ogGenerales, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGenerarReporteActionPerformed
        crearReporte();

    }//GEN-LAST:event_jbGenerarReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.decomisims.vistas.Conceptos conceptos;
    private javax.swing.JButton jbGenerarReporte;
    private javax.swing.JEditorPane jepContenido;
    private javax.swing.JEditorPane jepVariables;
    private javax.swing.JPanel jpConceptos;
    private javax.swing.JPanel jpControles;
    private javax.swing.JScrollPane jspConceptos;
    private javax.swing.JScrollPane jspVariables;
    private javax.swing.JTabbedPane jtpMain;
    private org.decomisims.vistas.OpcionesGenerales ogGenerales;
    // End of variables declaration//GEN-END:variables
}
