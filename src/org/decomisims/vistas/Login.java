package org.decomisims.vistas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import org.decomisims.app.Aplicacion;
import org.decomisims.app.Vista;
import org.decomisims.error.AppError;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public class Login extends javax.swing.JPanel implements Vista {

    private static final Logger log = LoggerFactory.getLogger(Login.class);
    private Aplicacion app;

    public Login() {
        initComponents();

        KeyAdapter ka = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jbtnIngresar.doClick();
                }
            }

        };

        jtfUsuario.addKeyListener(ka);
        jtfContra.addKeyListener(ka);
    }

    @Override
    public void init(Aplicacion app) throws AppError {
        this.app = app;
    }

    @Override
    public void mostrar() throws AppError {
        // Normalmente no habrá problema para desplegar
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

        jtfUsuario = new javax.swing.JTextField();
        jtfContra = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbtnIngresar = new javax.swing.JButton();

        jtfUsuario.setPreferredSize(new java.awt.Dimension(160, 28));

        jtfContra.setPreferredSize(new java.awt.Dimension(160, 28));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Usuario");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 28));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Contraseña");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 28));

        jbtnIngresar.setText("Entrar");
        jbtnIngresar.setPreferredSize(new java.awt.Dimension(140, 28));
        jbtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnIngresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngresarActionPerformed
        String user = jtfUsuario.getText();
        String pass = new String(jtfContra.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            return; // No hay nada que hacer
        }

        String encUser = app.getProperty("app.usuario", "demo");
        if (encUser.isEmpty()) {
            encUser = "demo";
        }
        String encPass = app.getProperty("app.contrasena", "demo");
        if (encPass.isEmpty()) {
            encPass = "demo";
        }
        if (encUser.equals(user)
                && (encPass.equals(pass))) {
            try {
                app.mostrarTarea("org.decomisims.vistas.Home");
            } catch (AppError ex) {
                log.error(ex.getMessage(), ex);
            }
        } else {
            MensajeFactory.mostrar("El usuario y/o contraseña es incorrecto");
        }
    }//GEN-LAST:event_jbtnIngresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtnIngresar;
    private javax.swing.JPasswordField jtfContra;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables

}
