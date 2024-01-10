/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.vistagrafica;
//import java.awt.Color;
/**
 *
 * @author miqueas
 */
public class Reglas extends javax.swing.JFrame {
    private VistaGrafica vistaGrafica;
    
    public void setVistaGrafica(VistaGrafica vistaGrafica){
        this.vistaGrafica = vistaGrafica;
    }
    /**
     * Creates new form Reglas
     */
    public Reglas() {
        initComponents();
        
        String reglasTexto = "En The Game Quick & Easy, deberemos jugar en equipo las cincuenta cartas del mazo en dos pilas diferentes. En una de ellas, podremos jugar una carta más alta de la que haya en ese momento, y en la otra, una carta más baja. Solo existe una excepción a ésta regla y es que se puede jugar cualquier carta en una de las dos pilas si es del mismo color que la carta superior actual.\n" +
                "Si algún jugador no puede poner ninguna de sus dos cartas siguiendo éstas sencillas reglas, el juego habrá terminado y habremos perdido. Si por el contrario, conseguimos poner todas las cartas del mazo, los jugadores serán los vencedores.\n" +
                "\n" +
                "¿Cómo se juega?\n" +
                "Preparar la partida es muy sencillo. Simplemente deberemos:\n" +
                "1. Colocar las dos cartas de indicación (ascendente y descendente) visibles en el centro de la mesa, una debajo de la otra.\n" +
                "2. Barajar el mazo de 50 cartas y dar dos a cada jugador. El mazo consta de cincuenta cartas que vienen en cinco colores numeradas del 1 al 10 por cada color.\n" +
                "3. El resto de cartas formarán el mazo de robo y se dejan en el centro de la mesa, boca abajo.\n" +
                "4. Elegimos al jugador inicial.\n" +
                "En su turno, el jugador activo deberá colocar una o incluso las dos cartas de su mano, encima de una de las pilas existentes (o incluso una carta en cada pila) y cogerá del mazo de robo tantas como haya jugado para volver a tener dos pasando el turno al siguiente jugador.\n" +
                "\n" +
                "Las cartas que se colocan deben seguir las siguientes reglas:\n" +
                "Las que se coloquen en la pila superior (pila descendente) deberán tener un número inferior al de la carta visible en ese momento.\n" +
                "Las que se coloquen en la pila inferior (pila ascendente) deberán tener un número superior al de la carta visible en ese momento.\n" +
                "\n" +
                "TIP: Truco de la marcha atrás: Si se coloca una carta del mismo color que la carta superior de la pila donde se desea poner, el valor numérico podrá ser cualquiera, es decir, podrá incluso ponerse un número contrario a las normas anteriores, con el fin de acomodar a nuestros gustos las cartas, por ejemplo, en la carta menor pongo un 10 azul, pero como tengo otra carta azul, la cual es un 2, puedo tirarla y descartas el 10 y dejar el 2 como menor, lo que le sirve mucho al siguiente jugador ya que es una carta bastante baja para no perder.\n" +
                "\n" +
                "La comunicación entre los jugadores es muy importante en este juego y podrán intercambiarse información siempre que lo deseen cumpliendo con la siguientes norma:\n" +
                "\n" +
                "Los valores numéricos no pueden indicarse.\n" +
                "Es decir, por ejemplo, no se podrá decir que mi carta roja es un 6, ni que mi carta amarilla es 3 números más pequeño que esa. Pero si que se podrá indicar que, por ejemplo, mi carta azul es alta y mi carta verde es intermedia.\n" +
                "\n" +
                "Como termina una partida?:\n" +
                "El juego puede terminar de dos formas:\n" +
                "\n" +
                "1.El jugador activo no puede poner ninguna de sus cartas en ninguna de las pilas cumpliendo las reglas. Los jugadores habrán perdido\n" +
                "2.Se acaba el mazo de robo y se han puesto todas las cartas en ambas pilas. Los jugadores habrán ganado.\n" +
                "\n" +
                "Comandos\n" +
                "Hay varios comandos, se indican en la partida, pero si quieres saberlos pueden ser los siguientes: '1 alta', '1 baja', '2 alta', '2 baja', y 'paso', el 1 significa que es la primer carta, y el 2 significa que es la segunda carta, seguido de La pila donde quieres poner dicha carta, por ejemplo, '2 alta', pongo mi segunda carta en la pila alta, siempre y cuando sea válido, decir 'paso', cambia de turno, siempre y cuando hayas tirado al menos una carta.\n" +
                "\nPresione enter para volver al menú.";
        
        //textoReglas.setText(reglasTexto);
        
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

        vMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelBackgroundMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(0,0,0,1)
        );
        jScrollPane1.setOpaque(false);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0,0,0,0)
        );
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("pepee\nssssssssssssssssssssssssssssssssssssssssssssssssssssssssss\n\n");
        jTextArea1.setBorder(null);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        vMenu.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        labelBackgroundMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagenes/file.gif"))); // NOI18N
        vMenu.add(labelBackgroundMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 530));

        getContentPane().add(vMenu, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelBackgroundMenu;
    private javax.swing.JPanel vMenu;
    // End of variables declaration//GEN-END:variables
}
