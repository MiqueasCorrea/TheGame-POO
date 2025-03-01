/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.frames;
//import java.awt.Color;

import view.vistas.VistaGrafica;

import java.rmi.RemoteException;

/**
 *
 * @author miqueas
 */
public class Reglas extends javax.swing.JFrame {
    private VistaGrafica vistaGrafica;
    String reglasTexto;

    public void setVistaGrafica(VistaGrafica vistaGrafica){
        this.vistaGrafica = vistaGrafica;
    }
    /**
     * Creates new form Reglas
     */
    public Reglas() {
        initComponents();
        
        reglasTexto = "The Game Quick & Easy \n" +
                "Deberemos jugar cooperativamente las cincuenta cartas del mazo en dos pilas diferentes.\n" +
                "\n" +
                "-Como se gana una partida, y como se pierde?\n" +
                "Si algun jugador no puede poner ninguna de sus dos cartas en su turno, el juego habra terminado y los jugadores pierden. \n" +
                "Si por el contrario, los jugadores consiguen vaciar el maso (que no haya ninguna carta en el mazo), los jugadores seran los vencedores.\n" +
                "\n" +
                "-Como se juega?\n" +
                "La partida consta con lo siguiente:\n" +
                "1. Se colocan las dos cartas de indicación (ascendente y descendente) visibles en el centro de la mesa (las de color purpura), una debajo de la otra.\n" +
                "2. Se baraja el mazo de 50 cartas y dar dos a cada jugador. El mazo consta de cincuenta cartas que vienen en cinco colores numeradas del 1 al 10 por cada color.\n" +
                "3. El resto de cartas formarán el mazo de robo y se dejan en el centro de la mesa, boca abajo.\n" +
                "4. Se elige al jugador inicial.\n" +
                "En su turno, el jugador activo deberá colocar una o incluso las dos cartas de su mano, encima de una de las pilas existentes (o incluso una carta en cada pila) y cogerá del mazo de robo tantas como haya jugado para volver a tener dos pasando el turno al siguiente jugador.\n" +
                "\n" +
                "Las cartas que se colocan deben seguir las siguientes reglas:\n" +
                "Las que se coloquen en la pila superior (pila descendente) deberán tener un número inferior o igual al de la carta visible en ese momento, y a su vez debe ser superior o igual al valor de la carta en la pila ascendente en ese momento.\n" +
                "Las que se coloquen en la pila inferior (pila ascendente) deberán tener un número superior o igual al de la carta visible en ese momento, y a su vez debe ser inferior o igual al valor de la carta en la pila descendente en ese momento.\n" +
                "\n" +
                "-TIP: Truco de la marcha atrás: Es posible evitar las reglas de colocacion de cartas, para ello existen los colores, estas nos permiten tirar cualquier carta en cualquier pila, sin importar sus valores, siempre y cuando el color coincida con el color de la carta actual." +
                "\n" +
                "-Comandos para la vista por consola:\n" +
                "Hay varios comandos, se indican en la partida, pero si queres saberlos pueden ser los siguientes: '1 alta', '1 baja', '2 alta', '2 baja', y 'mazo', el 1 significa que es la primer carta, y el 2 significa que es la segunda carta, seguido de La pila donde quieres poner dicha carta, \n" +
                "por ejemplo, '2 alta', pongo mi segunda carta en la pila alta, siempre y cuando sea válido, decir 'mazo', cambia de turno, siempre y cuando hayas tirado al menos una carta.\n";
        
        
        textAreaReglas.setText(reglasTexto);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vReglas = new javax.swing.JPanel();
        Volver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaReglas = new javax.swing.JTextArea();
        labelBackgroundMenu1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                try {
                    formWindowClosing(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        vReglas.setDoubleBuffered(false);
        vReglas.setOpaque(false);
        vReglas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Volver.setBackground(new java.awt.Color(255, 255, 255));
        Volver.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        Volver.setForeground(new java.awt.Color(255, 255, 255));
        Volver.setText("Volver");
        Volver.setBorder(null);
        Volver.setContentAreaFilled(false);
        Volver.setFocusPainted(false);
        Volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VolverMouseExited(evt);
            }
        });
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });
        vReglas.add(Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, -1, -1));

        jLabel1.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGLAS");
        vReglas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 170, -1));

        textAreaReglas.setColumns(20);
        textAreaReglas.setFont(new java.awt.Font("RETROTECH", 0, 18)); // NOI18N
        textAreaReglas.setForeground(new java.awt.Color(255, 255, 255));
        textAreaReglas.setRows(5);
        textAreaReglas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        textAreaReglas.setEditable(false);
        textAreaReglas.setOpaque(false);
        textAreaReglas.setBackground(new java.awt.Color(0, 0, 0, 0));
        jScrollPane1.setViewportView(textAreaReglas);

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        vReglas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 730, 400));

        labelBackgroundMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/menu/buscador_partida/buscarPartida.gif"))); // NOI18N
        vReglas.add(labelBackgroundMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        getContentPane().add(vReglas, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseEntered
        Volver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    }//GEN-LAST:event_VolverMouseEntered

    private void VolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseExited
        Volver.setBorder(null);
    }//GEN-LAST:event_VolverMouseExited

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        setVisible(false);
        vistaGrafica.menu();
    }//GEN-LAST:event_VolverActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) throws RemoteException {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        vistaGrafica.getControlador().cerrar(false);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Reglas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reglas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reglas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reglas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reglas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBackgroundMenu1;
    private javax.swing.JTextArea textAreaReglas;
    private javax.swing.JPanel vReglas;
    // End of variables declaration//GEN-END:variables
}
