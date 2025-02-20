/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.frames;

import controller.Controller;
import model.clases.Carta;
import model.clases.Jugador;
import model.enums.EnumColor;
import model.enums.EstadoJugador;
import model.enums.EstadoPartida;
import model.interfaces.ICarta;
import model.interfaces.IJugador;
import model.interfaces.IMazo;
import model.interfaces.IPartida;
import view.vistas.VistaGrafica;

import java.rmi.RemoteException;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author miqueas
 */
public class PartidaEnJuego extends javax.swing.JFrame {
    private List<JPanel> centerZone = new ArrayList<>();
    private List<JPanel> handZone = new ArrayList<>();
    private Draggable draggable;
    private Draggable draggable2;
    private boolean action = false;

    /**
     * Creates new form BuscarPartida
     */
    public PartidaEnJuego() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        inicializarZonas();
        agregarCartasDraggables();
    }

    private VistaGrafica vistaGrafica;


    public void setVistaGrafica(VistaGrafica vistaGrafica){
        this.vistaGrafica = vistaGrafica;
    }

    public Controller getController(){
        return vistaGrafica.getControlador();
    }


    private void inicializarZonas() {
        // Crear paneles donde se pueden soltar cartas
        centerZone.add(zonaCartaAlta);
        centerZone.add(zonaCartaBaja);

        // Crear paneles donde están las cartas en la mano
        handZone.add(zonaManoCarta1);
        handZone.add(zonaManoCarta2);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void agregarCartasDraggables() {
        draggable = new Draggable(carta1, handZone.get(0).getX(), handZone.get(0).getY(), this, centerZone, handZone);
        draggable2 = new Draggable(carta2, handZone.get(1).getX(), handZone.get(1).getY(), this, centerZone, handZone);
        this.getContentPane().add(draggable);
        this.getContentPane().add(draggable2);
        this.getContentPane().setComponentZOrder(draggable, 0);
        this.getContentPane().setComponentZOrder(draggable2, 0);
        this.getContentPane().setComponentZOrder(dedo, 0);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
//    mazo.setBorder(null);
//    mazo.setContentAreaFilled(false);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vPartidaEnJuego = new javax.swing.JPanel();
        mazo = new javax.swing.JButton();
        Volver = new javax.swing.JButton();
        labelgameOver = new javax.swing.JLabel();
        labelEstadoPartida = new javax.swing.JLabel();
        labelCartasRestantes = new javax.swing.JLabel();
        nombreJugador2 = new javax.swing.JLabel();
        nombreJugador3 = new javax.swing.JLabel();
        nombreJugador4 = new javax.swing.JLabel();
        carta1 = new javax.swing.JLabel();
        carta2 = new javax.swing.JLabel();
        zonaManoCarta2 = new javax.swing.JPanel();
        zonaManoCarta1 = new javax.swing.JPanel();
        zonaCartaAlta = new javax.swing.JPanel();
        labelCartaAlta = new javax.swing.JLabel();
        zonaCartaBaja = new javax.swing.JPanel();
        labelCartaBaja = new javax.swing.JLabel();
        dedo = new javax.swing.JLabel();
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

        vPartidaEnJuego.setDoubleBuffered(false);
        vPartidaEnJuego.setOpaque(false);
        vPartidaEnJuego.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mazo.setBorder(null);
        mazo.setContentAreaFilled(false);
        mazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    mazoActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        vPartidaEnJuego.add(mazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 60, 90));

        Volver.setBackground(new java.awt.Color(255, 255, 255));
        Volver.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        Volver.setForeground(new java.awt.Color(255, 255, 255));
        Volver.setText("Volver");
        Volver.setBorder(null);
        Volver.setContentAreaFilled(false);
        Volver.setFocusPainted(false);
        Volver.setVisible(false);
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
        vPartidaEnJuego.add(Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 570, 120, 40));

        labelgameOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/menu/creador_partida/gameOver.png"))); // NOI18N
        labelgameOver.setVisible(false);
        vPartidaEnJuego.add(labelgameOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        labelEstadoPartida.setFont(new java.awt.Font("RETROTECH", 0, 36)); // NOI18N
        labelEstadoPartida.setForeground(new java.awt.Color(255, 255, 255));
        labelEstadoPartida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEstadoPartida.setText("Esperando jugadores...");
        vPartidaEnJuego.add(labelEstadoPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 580, -1));

        labelCartasRestantes.setFont(new java.awt.Font("RETROTECH", 0, 21)); // NOI18N
        labelCartasRestantes.setForeground(new java.awt.Color(255, 255, 255));
        labelCartasRestantes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCartasRestantes.setText("CARTAS RESTANTES: ");
        labelCartasRestantes.setVisible(false);
        vPartidaEnJuego.add(labelCartasRestantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 290, 30));

        nombreJugador2.setFont(new java.awt.Font("RETROTECH", 0, 24)); // NOI18N
        nombreJugador2.setForeground(new java.awt.Color(255, 255, 255));
        nombreJugador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vPartidaEnJuego.add(nombreJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 270, 30));

        nombreJugador3.setFont(new java.awt.Font("RETROTECH", 0, 24)); // NOI18N
        nombreJugador3.setForeground(new java.awt.Color(255, 255, 255));
        nombreJugador3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vPartidaEnJuego.add(nombreJugador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 101, 300, 30));

        nombreJugador4.setFont(new java.awt.Font("RETROTECH", 0, 24)); // NOI18N
        nombreJugador4.setForeground(new java.awt.Color(255, 255, 255));
        nombreJugador4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vPartidaEnJuego.add(nombreJugador4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 270, 30));

        carta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/AMARILLO/AMARILLO1.png"))); // NOI18N
        carta1.setVisible(false);
        vPartidaEnJuego.add(carta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, -1, -1));

        carta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/AMARILLO/AMARILLO1.png"))); // NOI18N
        carta2.setVisible(false);
        vPartidaEnJuego.add(carta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, -1, -1));

        zonaManoCarta2.setOpaque(false);

        javax.swing.GroupLayout zonaManoCarta2Layout = new javax.swing.GroupLayout(zonaManoCarta2);
        zonaManoCarta2.setLayout(zonaManoCarta2Layout);
        zonaManoCarta2Layout.setHorizontalGroup(
            zonaManoCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        zonaManoCarta2Layout.setVerticalGroup(
            zonaManoCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        vPartidaEnJuego.add(zonaManoCarta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, 140, 190));

        zonaManoCarta1.setOpaque(false);

        javax.swing.GroupLayout zonaManoCarta1Layout = new javax.swing.GroupLayout(zonaManoCarta1);
        zonaManoCarta1.setLayout(zonaManoCarta1Layout);
        zonaManoCarta1Layout.setHorizontalGroup(
            zonaManoCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        zonaManoCarta1Layout.setVerticalGroup(
            zonaManoCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        vPartidaEnJuego.add(zonaManoCarta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 140, 220));

        zonaCartaAlta.setOpaque(false);

        labelCartaAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/perspectiva/ROJO/perspV1 (2).png"))); // NOI18N
        labelCartaAlta.setVisible(false);

        javax.swing.GroupLayout zonaCartaAltaLayout = new javax.swing.GroupLayout(zonaCartaAlta);
        zonaCartaAlta.setLayout(zonaCartaAltaLayout);
        zonaCartaAltaLayout.setHorizontalGroup(
            zonaCartaAltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaCartaAltaLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(labelCartaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        zonaCartaAltaLayout.setVerticalGroup(
            zonaCartaAltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaCartaAltaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(labelCartaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        vPartidaEnJuego.add(zonaCartaAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 350, 110));

        zonaCartaBaja.setOpaque(false);

        labelCartaBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/perspectiva/ROJO/perspV1 (2).png"))); // NOI18N
        labelCartaBaja.setVisible(false);

        javax.swing.GroupLayout zonaCartaBajaLayout = new javax.swing.GroupLayout(zonaCartaBaja);
        zonaCartaBaja.setLayout(zonaCartaBajaLayout);
        zonaCartaBajaLayout.setHorizontalGroup(
            zonaCartaBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaCartaBajaLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(labelCartaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );
        zonaCartaBajaLayout.setVerticalGroup(
            zonaCartaBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaCartaBajaLayout.createSequentialGroup()
                .addComponent(labelCartaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        vPartidaEnJuego.add(zonaCartaBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 440, 90));

        dedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/mesa/dedonuevo.png"))); // NOI18N
        vPartidaEnJuego.add(dedo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));

        labelBackgroundMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/mesa/mesa4jugadores.png"))); // NOI18N
        vPartidaEnJuego.add(labelBackgroundMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(vPartidaEnJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(vPartidaEnJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mazoActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_mazoActionPerformed
        // TODO add your handling code here:
        getController().siguienteTurno();
        if (!getController().verificarGameOver()){
            getController().verificarGameWin();
        }
    }//GEN-LAST:event_mazoActionPerformed

    private void VolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseEntered
        Volver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    }//GEN-LAST:event_VolverMouseEntered

    private void VolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverMouseExited
        Volver.setBorder(null);
    }//GEN-LAST:event_VolverMouseExited

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        setVisible(false);
        vistaGrafica.resetPartidaEnJuego();
        vistaGrafica.opciones();
    }//GEN-LAST:event_VolverActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) throws RemoteException {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        getController().cerrar(true);
    }//GEN-LAST:event_formWindowClosing

    public void mostrarJugadoresEnMesa() throws RemoteException {
        IPartida partida = vistaGrafica.getControlador().getPartidaActual();

        // Obtener la posición de "mi jugador" en la partida
        int miPosicion = partida.getPosicionJugador(vistaGrafica.getControlador().getNombreJugador());

        // Obtener el total de jugadores en la partida
        int cantidadJugadores = partida.getCantidadJugadoresEnLaPartida();

        // Ajustar nombres en orden relativo a miPosicion
        if (partida.getEstado() == EstadoPartida.EN_JUEGO){
            cantidadJugadores = partida.getCantidadJugadoresTotales();
        }
        switch (cantidadJugadores) {
            case 0,1 -> {
                labelBackgroundMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/mesa/mesa1jugadores.png")));
            }
            case 2 -> {
                nombreJugador2.setText(partida.getJugador((miPosicion + 1) % cantidadJugadores).getNombre());
                labelBackgroundMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/mesa/mesa2jugadores.png")));
                mostrarEstadoJugador(partida.getJugador((miPosicion + 1) % cantidadJugadores), nombreJugador2);
            }
            case 3 -> {
                nombreJugador2.setText(partida.getJugador((miPosicion + 1) % cantidadJugadores).getNombre());
                nombreJugador3.setText(partida.getJugador((miPosicion + 2) % cantidadJugadores).getNombre());
                labelBackgroundMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/mesa/mesa3jugadores.png")));
                mostrarEstadoJugador(partida.getJugador((miPosicion + 1) % cantidadJugadores), nombreJugador2);
                mostrarEstadoJugador(partida.getJugador((miPosicion + 2) % cantidadJugadores), nombreJugador3);
            }
            case 4 -> {
                nombreJugador2.setText(partida.getJugador((miPosicion + 1) % cantidadJugadores).getNombre());
                nombreJugador3.setText(partida.getJugador((miPosicion + 2) % cantidadJugadores).getNombre());
                nombreJugador4.setText(partida.getJugador((miPosicion + 3) % cantidadJugadores).getNombre());
                labelBackgroundMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/mesa/mesa4jugadores.png")));
                mostrarEstadoJugador(partida.getJugador((miPosicion + 1) % cantidadJugadores), nombreJugador2);
                mostrarEstadoJugador(partida.getJugador((miPosicion + 2) % cantidadJugadores), nombreJugador3);
                mostrarEstadoJugador(partida.getJugador((miPosicion + 3) % cantidadJugadores), nombreJugador4);
            }
            default -> throw new IllegalArgumentException("Cantidad de jugadores no soportada");
        }

        verificarIniciarPartida(partida);
    }

    public void mostrarEstadoJugador(IJugador jugador, JLabel label){
        switch (jugador.getEstadoJugador()){
            case EstadoJugador.CONECTADO -> label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green));
            case EstadoJugador.DESCONECTADO -> label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));
            default -> throw new IllegalArgumentException("Estado del jugador invalido.");
        }
    }

    public void verificarIniciarPartida(IPartida partida) throws RemoteException {
        if (partida.getCantidadJugadoresTotales() == partida.getCantidadJugadoresEnLaPartida() && !action){
            action = true;
            labelCartasRestantes.setVisible(true);
            vistaGrafica.empezarPartida();
        }
    }

    public void mostrarCartas() throws RemoteException {
        for (IJugador jugador : getController().getPartidaActual().getJugadoresEnLaPartida()) {
            if (jugador.getNombre().equals(getController().getNombreJugador())) {
                System.out.println("-------------------");
                ICarta carta1Vista = jugador.getPrimeraCartaDelJugador();
                ICarta carta2Vista = jugador.getSegundaCartaDelJugador();

                SwingUtilities.invokeLater(() -> {
                    if (jugador.isPrimeraCarta_en_mano()) {
                        carta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/" + carta1Vista.getColor().name() + "/" + carta1Vista.getColor().name() + carta1Vista.getNumero() + ".png")));
                        carta1.setVisible(true);
                    } else {
                        draggable.setAction(false);
                        carta1.setVisible(false);
                    }

                    if (jugador.isSegundaCarta_en_mano()) {
                        carta2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/" + carta2Vista.getColor().name() + "/" + carta2Vista.getColor().name() + carta2Vista.getNumero() + ".png")));
                        carta2.setVisible(true);
                    } else {
                        draggable2.setAction(false);
                        carta2.setVisible(false);
                    }
                });
            }
        }
    }

    public void mostrarTurno() throws RemoteException {
        IJugador turno_jugador = getController().getTurno();
        labelEstadoPartida.setText("Turno de " + turno_jugador.getNombre());
        if (turno_jugador.getNombre().equals(getController().getNombreJugador())){
            mazo.setVisible(true);
            draggable.setAction(true);
            draggable2.setAction(true);
        } else{
            mazo.setVisible(false);
            draggable.setAction(false);
            draggable2.setAction(false);
        }
    }

    public void mostrarTablero() throws RemoteException{
        ICarta cartaAlta = getController().getPartidaActual().getCartaAlta();
        ICarta cartaBaja = getController().getPartidaActual().getCartaBaja();
        
        if (cartaBaja.getColor() != EnumColor.PURPURA){
            labelCartaBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/perspectiva/" + cartaBaja.getColor().name() + "/" + cartaBaja.getColor().name() + cartaBaja.getNumero() + ".png")));
            labelCartaBaja.setVisible(true);
        }
        if (cartaAlta.getColor() != EnumColor.PURPURA){
            labelCartaAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/cartas/perspectiva/" + cartaAlta.getColor().name() + "/" + cartaAlta.getColor().name() + cartaAlta.getNumero() + ".png")));
            labelCartaAlta.setVisible(true);
        }
        System.out.println("-------------------");
        System.out.println("CARTA ALTA: " + "[" + cartaAlta.getColor().name() + " " + cartaAlta.getNumero() + "]");
        System.out.println("CARTA BAJA: " + "[" + cartaBaja.getColor().name() + " " + cartaBaja.getNumero() + "]");
    }

    public void mostrarCartasRestantes() throws RemoteException{
        IMazo mazo = getController().getPartidaActual().getMazo();
        labelCartasRestantes.setText("CARTAS RESTANTES: " + mazo.getCantidadCartas());
    }

    public void mostrarGameOver(){
        System.out.println("GAME OVER");
        draggable.setAction(false);
        draggable2.setAction(false);
        mazo.setVisible(false);
        this.getContentPane().setComponentZOrder(labelgameOver, 0);
        this.getContentPane().setComponentZOrder(Volver, 0);
        labelgameOver.setVisible(true);
        Volver.setVisible(true);
    }

    public void mostrarGameWin() throws RemoteException {
        System.out.println("GANARON LOS JUGADORES");
        draggable.setAction(false);
        draggable2.setAction(false);
        mazo.setVisible(false);
        this.getContentPane().setComponentZOrder(Volver, 0);
        Volver.setVisible(true);
        vistaGrafica.getControlador().actualizarRanking(vistaGrafica.getControlador().getNombreJugador());
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
            java.util.logging.Logger.getLogger(PartidaEnJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartidaEnJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartidaEnJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartidaEnJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartidaEnJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Volver;
    private javax.swing.JLabel carta1;
    private javax.swing.JLabel carta2;
    private javax.swing.JLabel dedo;
    private javax.swing.JLabel labelBackgroundMenu1;
    private javax.swing.JLabel labelCartaAlta;
    private javax.swing.JLabel labelCartaBaja;
    private javax.swing.JLabel labelCartasRestantes;
    private javax.swing.JLabel labelEstadoPartida;
    private javax.swing.JLabel labelgameOver;
    private javax.swing.JButton mazo;
    private javax.swing.JLabel nombreJugador2;
    private javax.swing.JLabel nombreJugador3;
    private javax.swing.JLabel nombreJugador4;
    private javax.swing.JPanel vPartidaEnJuego;
    private javax.swing.JPanel zonaCartaAlta;
    private javax.swing.JPanel zonaCartaBaja;
    private javax.swing.JPanel zonaManoCarta1;
    private javax.swing.JPanel zonaManoCarta2;
    // End of variables declaration//GEN-END:variables
}
