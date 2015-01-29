package org.decomisims.app;

import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JFrame;
import org.decomisims.error.AppError;
import org.decomisims.modelo.EscalaISR;
import org.decomisims.vistas.MensajeFactory;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class Main extends javax.swing.JFrame implements Aplicacion {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    //
    public static final NumberFormat MONEDA = new DecimalFormat("#,###.##");
    public static final NumberFormat PORCENTAJE = new DecimalFormat("%#.##");
    //
    private CardLayout clManager;
    private Properties config;
    // 
    private Vista actual;
    private Map<String, Vista> cache = new HashMap<>(10);
    //
    private String ultimaVista;

    public Main() {
        initComponents();
        clManager = (CardLayout) jpPrincipal.getLayout();
        initConfig();

        mostrarTarea("org.decomisims.vistas.Login");
    }

    private void initConfig() {
        config = new Properties();
        config.setProperty("usuario", "demo");
        config.setProperty("contrasena", new BasicPasswordEncryptor().encryptPassword("demo"));
        try {
            config.load(new FileReader("decomisims.cfg"));
        } catch (FileNotFoundException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Muestra una vista.
     * <p>
     * Se determina si la vista solicitada existe. Si existe proceder, de otro
     * modo notificar al usuario que la vista solicitada no existe. Así se evita
     * un glitching.
     *
     * @param klass
     */
    @Override
    public final void mostrarTarea(String klass) {
        Vista sig = cache.get(klass);
        if (sig == null) {
            Exception error = null;
            try {
                sig = (Vista) Class.forName(klass).newInstance();
                sig.init(this);
                cache.put(klass, sig);
                // Agregarla a la vista
                jpPrincipal.add(sig.getContenido(), klass);
            } catch (ClassNotFoundException ex) {
                error = ex;
            } catch (InstantiationException ex) {
                error = ex;
            } catch (IllegalAccessException ex) {
                error = ex;
            } catch (AppError ex) {
                error = ex;
            } finally {
                if (error != null) {
                    log.error(error.getMessage(), error);
                    MensajeFactory.mostrar("No se puede mostrar la pantalla solicitada");
                }
            }
        }
        if (sig != null) {
            // Down current view
            if (actual == null || actual.cerrar()) {
                try {
                    sig.mostrar();
                    clManager.show(jpPrincipal, klass);
                } catch (AppError ex) {
                    log.error(ex.getMessage(), ex);
                    MensajeFactory.mostrar("No se puede mostrar la pantalla solicitada");
                }
            }
        }
    }

    @Override
    public EscalaISR getEscala() {
        EscalaISR escala = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("escala.cfg"));
            escala = (EscalaISR) ois.readObject();
        } catch (FileNotFoundException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            log.error(ex.getMessage(), ex);
        }
        return escala;
    }
    
    public void guardarEscala(EscalaISR escala) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("escala.cfg"));
            oos.writeObject(oos);
            oos.flush();
        } catch (FileNotFoundException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/decomisims/locales/lang"); // NOI18N
        setTitle(bundle.getString("app.title")); // NOI18N
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setPreferredSize(new java.awt.Dimension(600, 480));

        jpPrincipal.setLayout(new java.awt.CardLayout());
        getContentPane().add(jpPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpPrincipal;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getProperty(String key) {
        return config.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }
}
