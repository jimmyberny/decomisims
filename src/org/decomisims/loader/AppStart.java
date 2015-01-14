package org.decomisims.loader;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.decomisims.app.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class AppStart {

    private static final Logger log = LoggerFactory.getLogger(AppStart.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

                    Main window = new Main();
                    window.setVisible(true);
                } catch (ClassNotFoundException |
                        IllegalAccessException |
                        InstantiationException |
                        UnsupportedLookAndFeelException ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        });

    }

}
