/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.vistagrafica;


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
        jTextFieldLogin = new javax.swing.JTextField();
        jBotonLogin = new javax.swing.JButton();
        tituloLabel = new javax.swing.JLabel();
        autorLabel = new javax.swing.JLabel();
        labelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsuarioLogin.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsuarioLogin.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        jLabelUsuarioLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuarioLogin.setText("Usuario:");
        vLogin.add(jLabelUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 150, 50));

        jTextFieldLogin.setBackground(new java.awt.Color(47, 28, 7));
        jTextFieldLogin.setFont(new java.awt.Font("Noto Sans Mono CJK HK", 0, 18)); // NOI18N
        jTextFieldLogin.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldLogin.setRequestFocusEnabled(false);
        jTextFieldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLoginActionPerformed(evt);
            }
        });
        vLogin.add(jTextFieldLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 170, 30));

        jBotonLogin.setBackground(new java.awt.Color(47, 28, 7));
        jBotonLogin.setFont(new java.awt.Font("RETROTECH", 0, 18)); // NOI18N
        jBotonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jBotonLogin.setText("Iniciar Sesion");
        jBotonLogin.setDefaultCapable(false);
        jBotonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonLoginActionPerformed(evt);
            }
        });
        vLogin.add(jBotonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 160, 30));

        tituloLabel.setFont(new java.awt.Font("RETROTECH", 2, 36)); // NOI18N
        tituloLabel.setForeground(new java.awt.Color(255, 255, 255));
        tituloLabel.setText("The Game - Quick & Easy");
        vLogin.add(tituloLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 430, 50));

        autorLabel.setFont(new java.awt.Font("RETROTECH", 0, 24)); // NOI18N
        autorLabel.setForeground(new java.awt.Color(255, 255, 255));
        autorLabel.setText("Creado por Correa Miqueas");
        vLogin.add(autorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, -1));

        labelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/file.gif"))); // NOI18N
        vLogin.add(labelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 530));

        getContentPane().add(vLogin, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLoginActionPerformed

    private void jBotonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonLoginActionPerformed
        if (jTextFieldLogin.getText() != null && !jTextFieldLogin.getText().isEmpty()) {
            vistaGrafica.mostrarMenuGrafica();
        } else{
            jTextFieldLogin.requestFocus();
        }
    }//GEN-LAST:event_jBotonLoginActionPerformed

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
    private javax.swing.JLabel jLabelUsuarioLogin;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JLabel labelBackground;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JPanel vLogin;
    // End of variables declaration//GEN-END:variables
}
