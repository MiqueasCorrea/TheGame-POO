package view;

import controller.Controller;
import model.enums.Estados;
import model.enums.Eventos;
import model.Jugador;
import model.Partida;
import model.Carta;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaConsola extends JFrame implements IVista{
    private Controller controlador;
    private JPanel vConsola;
    private JLabel subtitulo;
    private JTextArea textArea;
    private JTextField textField;
    private JButton enviar;
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
                    boolean existe = false;
                    for (Jugador jugador : controlador.getModelo().getUsuarios()){
                        if (jugador.getNombre().equals(textField.getText())){
                            existe = true;
                        }
                    }
                    if (existe){
                        textArea.append("\nYa existe un usuario con ese nombre.");
                    } else{
                        controlador.conectarJugador(textField.getText());
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
                    limpiarTextoTextField();
                    mostrarElegirJugador();
                } else if (opcionElegida.equals("2")){
                    limpiarTextoTextField();
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
                    limpiarTextoTextField();
                    int cantidadPartidasEnJuego = 0;
                    for (Partida partida : controlador.getModelo().getPartidas()){
                        if (!partida.getFinalizado()){
                            cantidadPartidasEnJuego++;
                        }
                    }
                    if (cantidadPartidasEnJuego > 0){
                        textArea.append("\nYa hay una partida en juego, espera a que termine.");
                    } else{
                        crearPartida();
                    }
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
    public void mostrarEsperandoJugadores(Partida partida){
        eliminarActionListeners();
        subtitulo.setText("Esperando Jugadores");
        textArea.setText("Jugadores necesarios: " + partida.getCantidadJugadoresEnLaPartida() + "/" + cantidadJugadoresPartida + "\n\n-Jugadores en la sala: \n");
        for (Jugador jugador : partida.getJugadores_en_la_partida()){
            textArea.append("- " + jugador.getNombre() + "\n");
        }

        if (partida.getCantidadJugadoresEnLaPartida() >= cantidadJugadoresPartida){
            textArea.append("\n\nSALA LLENA\n");
            textArea.append("El juego empezará pronto");
            empezarPartida(partida);
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
            if (partida.getFinalizado()){
                textArea.append("Partida " + i + ": " + partida.getCantidadJugadoresEnLaPartida() + "/" + partida.getCantidadJugadoresTotales() + " FINALIZADA\n");
            } else if (partida.getEnJuego()){
                textArea.append("Partida " + i + ": " + partida.getCantidadJugadoresEnLaPartida() + "/" + partida.getCantidadJugadoresTotales() + " EN JUEGO\n");
            } else {
                textArea.append("Partida " + i + ": " + partida.getCantidadJugadoresEnLaPartida() + "/" + partida.getCantidadJugadoresTotales() + " EN ESPERA\n");
            }
        }
        textArea.append("\nDigite el número de la partida que te quieres unir.");

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroPartida;
                try {
                    numeroPartida = Integer.parseInt(textField.getText());
                    if (numeroPartida >= 1 && numeroPartida <= controlador.getPartidas().size()){
                        Partida partida = controlador.getModelo().getPartidas().get(numeroPartida-1);
                        if (!partida.getEnJuego() && !partida.getFinalizado()){
                            partida.agregarJugador(controlador.getJugador());
                            estado = Estados.EN_SALA_ESPERA;
                            controlador.getModelo().notificarObservers(partida, Eventos.SE_UNIO_JUGADOR);
                            controlador.getModelo().notificarObservers(partida, Eventos.CAMBIO_LISTA_ESPERA);
                        } else if (partida.getEnJuego()){
                            textArea.append("\nEsa partida esta en juego.\n");
                        } else {
                            if (partida.getFinalizado()){
                                textArea.append("Esta partida finalizó");
                            }
                        }
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
        subtitulo.setText("Partida en Juego - Jugador " + controlador.getJugador().getNombre());
        if (!partida.getEnJuego()){
            partida.setEnJuego(true);
        }
        if (!partida.getRepartidas()){
            System.out.println("TEST, SE REPARTIERON");
            repartirCartas(partida);
            partida.setRepartidas(true);
            partida.establecerColaTurnos();
        } else{
            System.out.println("NO SE REPARTIERON");
        }
        mostrarJuego(partida);
    }

    public void mostrarJuego(Partida partida){
        limpiarTextoTextField();
        textArea.setText("\t\tTurno de " + partida.getTurnoActual().getNombre());
        textArea.append("\n\t\tCARTA ALTA: " + "[" + partida.getCartaAlta().getNumero() + " " + partida.getCartaAlta().getColor() + "]");
        textArea.append("\n\t\tCARTA BAJA: " + "[" + partida.getCartaBaja().getNumero() + " " + partida.getCartaBaja().getColor() + "]");
        textArea.append("\n\t\tMazo (" + partida.getMazo().getCantidadCartas() + " cartas restantes)");

        textArea.append("\n\nTus cartas\n");
        textArea.append("PRIMER CARTA: ");
        if (controlador.getJugador().getPrimeraCartaDelJugador().getEnMano()) {
            System.out.println("CARTA TEST: "+ controlador.getJugador().getPrimeraCartaDelJugador().getNumero());
            textArea.append("[" + controlador.getJugador().getPrimeraCartaDelJugador().getNumero() + " " + controlador.getJugador().getPrimeraCartaDelJugador().getColor() + "]");
        } else {
            textArea.append("(tomar carta del mazo)");
        }

        textArea.append("\nSEGUNDA CARTA: ");
        if (controlador.getJugador().getSegundaCartaDelJugador().getEnMano()){
            textArea.append("[" + controlador.getJugador().getSegundaCartaDelJugador().getNumero() + " " + controlador.getJugador().getSegundaCartaDelJugador().getColor() + "]");
        } else {
            textArea.append("(tomar carta del mazo)");
        }

        movimiento(partida);
    }

    public void repartirCartas(Partida partida){
        for (Jugador jugador : partida.getJugadores_en_la_partida()){
            Carta carta = partida.getMazo().agarrarCartaTope();
            jugador.setPrimeraCartaDelJugador(carta);

            Carta carta2 = partida.getMazo().agarrarCartaTope();
            jugador.setSegundaCartaDelJugador(carta2);
        }
    }

    public void movimiento(Partida partida){
        textArea.append("\n\nSelecciona una carta y la pila (por ejemplo, '1 alta' o '2 baja',\no escribe 'paso' luego de tirar al menor una carta) ");
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esTurnoActual(partida)){
                    String seleccion = textField.getText();
                    if (partida.getMazo().getCantidadCartas() <= 0){
                        controlador.getModelo().notificarObservers(partida, Eventos.GAME_WIN);
                    }
                    switch (seleccion){
                        case "paso":
                            manejarPaso(partida);
                            if (verificarGameOver(partida)){
                                System.out.println("GAME OVER");
                                controlador.getModelo().notificarObservers(partida, Eventos.GAME_OVER);
                            }
                            break;
                        case "1 alta":
                            manejarMovimiento(partida, seleccion);
                            break;
                        case "1 baja":
                            manejarMovimiento(partida, seleccion);
                            break;
                        case "2 alta":
                            manejarMovimiento(partida, seleccion);
                            break;
                        case "2 baja":
                            manejarMovimiento(partida, seleccion);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private boolean esTurnoActual(Partida partida) {
        return partida.getTurnoActual().getNombre().equals(controlador.getJugador().getNombre());
    }

    private void manejarPaso(Partida partida){
        if (controlador.getJugador().getCantidadCartasTiradas() >= 1) {
            manejarCartasEnMano(partida, controlador.getJugador().getPrimeraCartaDelJugador(), controlador.getJugador().getSegundaCartaDelJugador());
            controlador.getJugador().setCantidadCartasTiradas(0);
            partida.avanzarTurno();
            controlador.getModelo().notificarObservers(partida, Eventos.CAMBIO_TURNO);
        } else {
            textArea.append("\nTira al menos una carta.\n");
        }
    }

    private void manejarCartasEnMano(Partida partida, Carta carta1, Carta carta2){
        if (!carta1.getEnMano()){
            controlador.getJugador().setPrimeraCartaDelJugador(partida.getMazo().agarrarCartaTope());
            controlador.getJugador().getPrimeraCartaDelJugador().setEnMano(true);
        }
        if (!carta2.getEnMano()){
            controlador.getJugador().setSegundaCartaDelJugador(partida.getMazo().agarrarCartaTope());
            controlador.getJugador().getSegundaCartaDelJugador().setEnMano(true);
        }
    }

    private void manejarMovimiento(Partida partida, String seleccion){
        if (seleccion.equals("1 alta") || seleccion.equals("1 baja")) {
            if (controlador.getJugador().getPrimeraCartaDelJugador().getEnMano()){
                if (controlador.getJugador().getPrimeraCartaDelJugador().getNumero() <
                        partida.getCartaAlta().getNumero() &&
                        controlador.getJugador().getPrimeraCartaDelJugador().getNumero() >
                        partida.getCartaBaja().getNumero() ||
                        controlador.getJugador().getPrimeraCartaDelJugador().getColor().equals(partida.getCartaAlta().getColor()) ||
                        controlador.getJugador().getPrimeraCartaDelJugador().getColor().equals(partida.getCartaBaja().getColor())) {
                    if (seleccion.equals("1 alta")) {
                        partida.setCartaAlta(controlador.getJugador().getPrimeraCartaDelJugador());
                        controlador.getJugador().getPrimeraCartaDelJugador().setEnMano(false);
                        controlador.getJugador().incrementarCartasTiradas();
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                    }
                    if (seleccion.equals("1 baja")) {
                        partida.setCartaBaja(controlador.getJugador().getPrimeraCartaDelJugador());
                        controlador.getJugador().getPrimeraCartaDelJugador().setEnMano(false);
                        controlador.getJugador().incrementarCartasTiradas();
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                    }
                } else {
                    textArea.append("\nMovimiento invalido.");
                }
            } else {
                textArea.append("\n Ya has tirado esa carta, no la tienes mas.");
            }
        }
        else if (seleccion.equals("2 alta") || seleccion.equals("2 baja")){
            if (controlador.getJugador().getSegundaCartaDelJugador().getEnMano()){
                if (controlador.getJugador().getSegundaCartaDelJugador().getNumero() <
                        partida.getCartaAlta().getNumero() &&
                        controlador.getJugador().getSegundaCartaDelJugador().getNumero() >
                        partida.getCartaBaja().getNumero() ||
                        controlador.getJugador().getSegundaCartaDelJugador().getColor().equals(partida.getCartaAlta().getColor()) ||
                        controlador.getJugador().getSegundaCartaDelJugador().getColor().equals(partida.getCartaBaja().getColor())){
                    if (seleccion.equals("2 alta")){
                        partida.setCartaAlta(controlador.getJugador().getSegundaCartaDelJugador());
                        controlador.getJugador().getSegundaCartaDelJugador().setEnMano(false);
                        controlador.getJugador().incrementarCartasTiradas();
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                    }
                    if (seleccion.equals("2 baja")){
                        partida.setCartaBaja(controlador.getJugador().getSegundaCartaDelJugador());
                        controlador.getJugador().getSegundaCartaDelJugador().setEnMano(false);
                        controlador.getJugador().incrementarCartasTiradas();
                        controlador.getModelo().notificarObservers(partida, Eventos.ACTUALIZACION_CARTA);
                    }
                } else {
                    textArea.append("\nMovimiento invalido.");
                }
            } else{
                textArea.append("\n Ya has tirado esa carta, no la tienes mas.");
            }
        }
    }

    public boolean verificarGameOver(Partida partida){
        if (!puedeJugarCarta(partida, partida.getTurnoActual().getPrimeraCartaDelJugador()) &&
            !puedeJugarCarta(partida, partida.getTurnoActual().getSegundaCartaDelJugador())){
            return true;
        }
        return false;
    }

    public boolean puedeJugarCarta(Partida partida, Carta carta){
        if (!carta.getEnMano()){
            return true;
        }
        if ((carta.getNumero() != partida.getCartaBaja().getNumero() && carta.getNumero() != partida.getCartaAlta().getNumero()) &&
            (carta.getNumero() > partida.getCartaBaja().getNumero() && carta.getNumero() < partida.getCartaAlta().getNumero())
            || (carta.getColor().equals(partida.getCartaAlta().getColor()) || carta.getColor().equals(partida.getCartaBaja().getColor()))){
            return true;
        } else {
            return false;
        }
    }
    public void gameOver(Partida partida){
        textArea.append("\n\t\t--------GAME OVER--------\nDigite 'salir' para volver al menú.");
        controlador.getModelo().getPartidas().remove(partida);
        controlador.getModelo().notificarObservers(partida, Eventos.CAMBIO_LISTA_ESPERA);
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    partida.setFinalizado(true);
                    partida.setEnJuego(false);
                    mostrarMenuJuego();
                }
            }
        });
    }

    @Override
    public void gameWin(Partida partida){
        textArea.append("\n\t\t--------GAME WIN--------\nDigite 'salir' para volver al menú.");
        controlador.getModelo().getPartidas().remove(partida);
        controlador.getModelo().notificarObservers(partida, Eventos.CAMBIO_LISTA_ESPERA);
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    partida.setFinalizado(true);
                    partida.setEnJuego(false);
                    mostrarMenuJuego();
                }
            }
        });
    }

    // COSAS PARA OPERAR APARTE
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
