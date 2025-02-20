/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.frames;


import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;
import model.excepciones.PasswordIncorrecta;
import view.vistas.VistaGrafica;

import javax.swing.*;
import java.rmi.RemoteException;

/**
 *
 * @author miqueas
 */
public class Login extends javax.swing.JFrame {
    private VistaGrafica vistaGrafica;
    /**
     * Creates new form VistaGrafica
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    public void setVistaGrafica(VistaGrafica vistaGrafica){
        this.vistaGrafica = vistaGrafica;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vLogin = new javax.swing.JPanel();
        jLabelUsuarioLogin = new javax.swing.JLabel();
        jLabelPasswordLogin = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextFieldUsernameLogin1 = new javax.swing.JTextField();
        jBotonRegistrarse = new javax.swing.JButton();
        jBotonLogin = new javax.swing.JButton();
        tituloLabel = new javax.swing.JLabel();
        autorLabel = new javax.swing.JLabel();
        labelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsuarioLogin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsuarioLogin.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        jLabelUsuarioLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuarioLogin.setText("Password:");
        vLogin.add(jLabelUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 180, 50));

        jLabelPasswordLogin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPasswordLogin.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        jLabelPasswordLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPasswordLogin.setText("Usuario:");
        vLogin.add(jLabelPasswordLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 150, 50));

        jPasswordField1.setBackground(new java.awt.Color(47, 28, 7));
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        vLogin.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 170, 30));

        jTextFieldUsernameLogin1.setBackground(new java.awt.Color(47, 28, 7));
        jTextFieldUsernameLogin1.setFont(new java.awt.Font("Noto Sans Mono CJK HK", 0, 18)); // NOI18N
        jTextFieldUsernameLogin1.setForeground(new java.awt.Color(255, 255, 255));
        vLogin.add(jTextFieldUsernameLogin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 170, 30));

        jBotonRegistrarse.setBackground(new java.awt.Color(47, 28, 7));
        jBotonRegistrarse.setFont(new java.awt.Font("RETROTECH", 0, 18)); // NOI18N
        jBotonRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        jBotonRegistrarse.setText("Registrarse");
        jBotonRegistrarse.setDefaultCapable(false);
        jBotonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonRegistrarseActionPerformed(evt);
            }
        });
        vLogin.add(jBotonRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 160, 30));

        jBotonLogin.setBackground(new java.awt.Color(47, 28, 7));
        jBotonLogin.setFont(new java.awt.Font("RETROTECH", 0, 18)); // NOI18N
        jBotonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jBotonLogin.setText("Iniciar Sesion");
        jBotonLogin.setDefaultCapable(false);
        jBotonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jBotonLoginActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        vLogin.add(jBotonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 160, 30));

        tituloLabel.setFont(new java.awt.Font("RETROTECH", 2, 36)); // NOI18N
        tituloLabel.setForeground(new java.awt.Color(255, 255, 255));
        tituloLabel.setText("The Game - Quick & Easy");
        vLogin.add(tituloLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 430, 50));

        autorLabel.setFont(new java.awt.Font("RETROTECH", 0, 24)); // NOI18N
        autorLabel.setForeground(new java.awt.Color(255, 255, 255));
        autorLabel.setText("Creado por Correa Miqueas");
        vLogin.add(autorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        labelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/file.gif"))); // NOI18N
        vLogin.add(labelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 530));

        getContentPane().add(vLogin, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBotonLoginActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_jBotonLoginActionPerformed
        if (verificarCampos()) {
            try {
                vistaGrafica.getControlador().iniciarSesion(jTextFieldUsernameLogin1.getText(), jPasswordField1.getText());
            } catch (JugadorNoExistente | PasswordIncorrecta e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            setVisible(false);
            vistaGrafica.menu();
        } else{
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_jBotonLoginActionPerformed

    private void jBotonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonRegistrarseActionPerformed
        // TODO add your handling code here:
        if (verificarCampos()) {
            try {
                vistaGrafica.getControlador().registrarUsuario(jTextFieldUsernameLogin1.getText(), jPasswordField1.getText());
            } catch (JugadorExistente e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            setVisible(false);
            vistaGrafica.menu();
        } else{
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_jBotonRegistrarseActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private boolean verificarCampos(){
        if (!jTextFieldUsernameLogin1.getText().isBlank() && !jPasswordField1.getText().isBlank()){
            return true;
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autorLabel;
    private javax.swing.JButton jBotonLogin;
    private javax.swing.JButton jBotonRegistrarse;
    private javax.swing.JLabel jLabelPasswordLogin;
    private javax.swing.JLabel jLabelUsuarioLogin;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldUsernameLogin1;
    private javax.swing.JLabel labelBackground;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JPanel vLogin;
    // End of variables declaration//GEN-END:variables
}
