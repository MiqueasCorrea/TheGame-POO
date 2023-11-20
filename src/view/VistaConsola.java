package view;

import controller.Controller;
import model.Estados;
import model.Eventos;
import model.Jugador;
import model.Partida;
import model.Carta;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VistaConsola extends JFrame implements IVista{
    private Controller controlador;
    private JPanel vConsola;
    private JLabel subtitulo;
    private JTextArea textArea;
    private JTextField textField;
    private JButton enviar;
    private boolean nombreGuardado = false;
    private int cantidadJugadoresPartida;
    private Estados estado;

    public VistaConsola(Controller controlador){
        this.controlador = controlador;
        this.controlador.setVista(this);

        setContentPane(vConsola);
        setTitle("The Game - Consola");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("")){

                    // Si no tiene nombre de usuario, se le asigna
                    if (!nombreGuardado){
                        controlador.conectarJugador(textField.getText());
                        nombreGuardado = true;
                        mostrarMenuJuego();
                    }
                }
            }
        });
        SwingUtilities.getRootPane(enviar).setDefaultButton(enviar);
    }

    private void mostrarMenuJuego(){
        subtitulo.setText("Menú");
        estado = Estados.EN_MENU;
        limpiarTextoTextField();
        textArea.setText("1. Jugar\n2. Reglas");

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionElegida = textField.getText();
                if (opcionElegida.equals("1")){
                    mostrarOpcionesJugar();
                } else if (opcionElegida.equals("2")){
                    mostrarReglas();
                }
            }
        });
    }


    private void mostrarOpcionesJugar(){
        subtitulo.setText("Jugar");
        limpiarTextoTextField();
        textArea.setText("1. Iniciar Partida\n2. Buscar Partida\n\n3. Volver al menú");

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionElegida = textField.getText();
                if (opcionElegida.equals("1")){
                    mostrarElegirJugador();
                } else if (opcionElegida.equals("2")){
                    estado = Estados.EN_BUSCAR_PARTIDA;
                    mostrarBuscarPartidas();
                } else if (opcionElegida.equals("3")){
                    mostrarMenuJuego();
                }
            }
        });
    }
    private void mostrarElegirJugador(){
        subtitulo.setText("Elegir cantidad de Jugadores");
        textArea.setText("2. Jugadores\n3. Jugadores\n4. Jugadores\n5. Jugadores\n\n6. Volver al menú");
        limpiarTextoTextField();

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("2") || textField.getText().equals("3") || textField.getText().equals("4") || textField.getText().equals("5")){
                    cantidadJugadoresPartida = Integer.parseInt(textField.getText());
                    crearPartida();
                }
                if (textField.getText().equals("6")){
                    mostrarMenuJuego();
                }
            }
        });
    }

    private void mostrarReglas(){
        subtitulo.setText("Reglas");
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
                "Las que se coloquen en la pila inferior (pila ascendente) deberán tener un número superior al de la carta visible en ese momento.\n" +
                "Las que se coloquen en la pila superior (pila descendente) deberán tener un número inferior al de la carta visible en ese momento.\n" +
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
                "Variante para profesionales\n" +
                "En esta variante, los jugadores solo podrán poner exactamente una carta en cada turno y además, quedará prohibido dar pistas sobre el valor de los números. Solo podrán indicarse el color de la carta y en qué pila le gustaría poner una carta o como mucho, en cuál de ellas se podría aplicar el truco de la marcha atrás.\n" +
                "\nPresione enter para volver al menú.";

        textArea.setText(reglasTexto);
        limpiarTextoTextField();

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTextoTextField();
                mostrarMenuJuego();
            }
        });
    }


    public void crearPartida(){
        estado = Estados.EN_SALA_ESPERA;
        Partida nueva_partida = new Partida(cantidadJugadoresPartida);
        controlador.agregarPartida(nueva_partida);
        nueva_partida.agregarJugador(controlador.getJugador());
        controlador.getModelo().notificarObservers(nueva_partida, Eventos.CAMBIO_LISTA_ESPERA);
        mostrarEsperandoJugadores(nueva_partida);
    }
    @Override
    public void mostrarEsperandoJugadores(Partida nueva_partida){
        eliminarActionListeners();
        subtitulo.setText("Esperando Jugadores");
        textArea.setText("Jugadores necesarios: " + nueva_partida.getCantidadJugadoresEnLaPartida() + "/" + cantidadJugadoresPartida + "\n\n-Jugadores en la sala: \n");
        for (Jugador jugador : nueva_partida.getJugadores_en_la_partida()){
            textArea.append("- " + jugador.getNombre() + "\n");
        }

        if (nueva_partida.getCantidadJugadoresEnLaPartida() >= cantidadJugadoresPartida){
            textArea.append("\n\nSALA LLENA\n");
            textArea.append("El juego empezará pronto");

            empezarPartida(nueva_partida);
        }
    }

    @Override
    public void mostrarBuscarPartidas(){
        subtitulo.setText("Buscando Partidas");
        textArea.setText("Partidas actuales\n'salir'. Para volver al Menú\n\n");
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    mostrarMenuJuego();
                }
            }
        });
        int i = 1;
        for (Partida partida : controlador.getPartidas()){
            textArea.append("Partida " + i + ": " + partida.getCantidadJugadoresEnLaPartida() + "/" + partida.getCantidadJugadoresTotales() + "\n");
        }
        textArea.append("\nDigite el número de la partida que te quieres unir.");

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroPartida;
                try {
                    numeroPartida = Integer.parseInt(textField.getText());
                    if (numeroPartida >= 1 && numeroPartida <= controlador.getPartidas().size()){
                        Partida partida = controlador.getModelo().getPartidas().get(numeroPartida-1);
                        partida.agregarJugador(controlador.getJugador());
                        estado = Estados.EN_SALA_ESPERA;
                        controlador.getModelo().notificarObservers(partida, Eventos.SE_UNIO_JUGADOR);
                        eliminarActionListeners();
                    }
                } catch (NumberFormatException ex){
                    System.err.println("NÚMERO INVALIDO");
                }
            }
        });

    }






    // LOGICA DE UNA PARTIDA
    @Override
    public void empezarPartida(Partida partida){
        estado = Estados.EN_JUEGO;
        subtitulo.setText("Partida en Juego");
        repartirCartas(partida);
        mostrarJuego(partida);
    }

    public void mostrarJuego(Partida partida){
        textArea.setText("CARTA ALTA: " + "[" + partida.getCartaAlta().getNumero() + " " + partida.getCartaAlta().getColor() + "]");
        textArea.append("\nCARTA BAJA: " + "[" + partida.getCartaBaja().getNumero() + " " + partida.getCartaBaja().getColor() + "]");
        textArea.append("\nMazo (" + partida.getMazo().getCantidadCartas() + " cartas restantes)");

        textArea.append("\n\nTus cartas\n");
        textArea.append("PRIMER CARTA: " + "[" + controlador.getJugador().getPrimeraCartaDelJugador().getNumero() + " " + controlador.getJugador().getPrimeraCartaDelJugador().getColor() + "]");
        textArea.append("\nSEGUNDA CARTA: " + "[" + controlador.getJugador().getSegundaCartaDelJugador().getNumero() + " " + controlador.getJugador().getSegundaCartaDelJugador().getColor() + "]");

        movimiento(partida);
    }
    public void repartirCartas(Partida partida){
        for (Jugador jugador : partida.getJugadores_en_la_partida()){
            Carta carta = partida.getMazo().agarrarCartaTope();
            jugador.setPrimeraCartaDelJugador(carta);

            carta = partida.getMazo().agarrarCartaTope();
            jugador.setSegundaCartaDelJugador(carta);
        }
    }

    public void movimiento(Partida partida){
        textArea.append("\n\nSelecciona una carta y la pila (por ejemplo, '1 alta', '2 baja') ");
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (textField.getText()){
                    case "1 alta":
                        partida.setCartaAlta(controlador.getJugador().getPrimeraCartaDelJugador());
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                        break;
                    case "1 baja":
                        partida.setCartaBaja(controlador.getJugador().getPrimeraCartaDelJugador());
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                        break;
                    case "2 alta":
                        partida.setCartaAlta(controlador.getJugador().getSegundaCartaDelJugador());
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                        break;
                    case "2 baja":
                        partida.setCartaBaja(controlador.getJugador().getSegundaCartaDelJugador());
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                        break;
                }
            }
        });
    }















    private void limpiarTextoTextField(){
        textField.setText("");
    }

    private void eliminarActionListeners() {
        ActionListener[] listeners = enviar.getActionListeners();
        for (ActionListener listener : listeners) {
            enviar.removeActionListener(listener);
        }
    }

    public Estados getEstado(){
        return estado;
    }
    @Override
    public void mostrarInicioSesion(){
        this.setVisible(true);
    }
}
