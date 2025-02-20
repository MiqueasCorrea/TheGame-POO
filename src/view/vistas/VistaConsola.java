package view.vistas;

import controller.Controller;
import model.enums.EstadoPartida;
import model.enums.Estados;
import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;
import model.excepciones.PasswordIncorrecta;
import model.interfaces.ICarta;
import model.interfaces.IJugador;
import model.interfaces.IMazo;
import model.interfaces.IPartida;
import view.interfaces.IVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Map;

public class VistaConsola extends JFrame implements IVista {
    private Controller controlador;
    private JPanel vConsola;
    private JLabel subtitulo;
    private JTextArea textArea;
    private JTextField textField;
    private JButton enviar;
    private Estados estado;
    private boolean action;
    private boolean actionCarta1;
    private boolean actionCarta2;

    public VistaConsola() throws RemoteException {
        this.controlador = new Controller();
        this.controlador.setVista(this);

        setContentPane(vConsola);
        setTitle("The Game - Consola");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        SwingUtilities.getRootPane(enviar).setDefaultButton(enviar);
        login();
        setVisible(true);
    }

    @Override
    public void login() throws RemoteException{
        eliminarActionListeners();
        textArea.setText("The Game - Quick & Easy\n\n1. Iniciar Sesion\n2. Registrarse");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionElegida = textField.getText();
                switch (opcionElegida){
                    case "1" -> iniciarSesion(false, "");
                    case "2" -> registrarse(false, "");
                    default -> {}
                }
            }
        });
    }

    public void iniciarSesion(boolean fallo, String mensaje){
        limpiarTextoTextField();
        eliminarActionListeners();

        textArea.setText("The Game - Quick & Easy");
        if (fallo){
            textArea.append("\n" + mensaje);
        }
        textArea.append("\n\nIngrese nombre de usuario");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isBlank()){
                    pedirPasswordLogin(textField.getText());
                }
            }
        });
    }

    public void registrarse(boolean fallo, String mensaje){
        limpiarTextoTextField();
        eliminarActionListeners();

        textArea.setText("The Game - Quick & Easy");
        if (fallo){
            textArea.append("\n" + mensaje);
        }

        textArea.append("\n\nIngrese nombre de usuario a registrar");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isBlank()){
                    pedirPasswordRegistro(textField.getText());
                }
            }
        });
    }

    public void pedirPasswordLogin(String nombre){
        limpiarTextoTextField();
        eliminarActionListeners();
        textArea.append("\nIngrese el password");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isBlank()){
                    try {
                        controlador.iniciarSesion(nombre, textField.getText());
                    } catch (JugadorNoExistente | PasswordIncorrecta a){
                        textArea.append("\n\n" + a.getMessage());
                        iniciarSesion(true, a.getMessage());
                        return;
                    } catch (RemoteException b) {
                        throw new RuntimeException(b);
                    }
                    menu();
                }
            }
        });
    }

    public void pedirPasswordRegistro(String nombre){
        limpiarTextoTextField();
        eliminarActionListeners();
        textArea.append("\nIngrese el password");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isBlank()){
                    try {
                        controlador.registrarUsuario(nombre, textField.getText());
                    } catch (JugadorExistente a){
                        registrarse(true, a.getMessage());
                        return;
                    } catch (RemoteException b) {
                        throw new RuntimeException(b);
                    }
                    menu();
                }
            }
        });
    }

    @Override
    public void menu(){
        subtitulo.setText("Menú");
        limpiarTextoTextField();
        eliminarActionListeners();
        textArea.setText("The Game - Quick & Easy\n\n1. Jugar\n2. Ranking\n3. Reglas");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionElegida = textField.getText();
                switch (opcionElegida){
                    case "1" -> opciones();
                    case "2" -> {
                        try {
                            ranking();
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "3" -> reglas();
                    default -> {}
                }
            }
        });
    }

    @Override
    public void opciones(){
        subtitulo.setText("Jugar");
        limpiarTextoTextField();
        eliminarActionListeners();
        textArea.setText("The Game - Quick & Easy\n\n1. Crear Partida\n2. Buscar Partida\n\n3. Volver");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionElegida = textField.getText();
                switch (opcionElegida){
                    case "1" -> opcionesDeJuego();
                    case "2" -> {
                        try {
                            buscarPartidas();
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "3" -> menu();
                    default -> {}
                }
            }
        });
    }

    @Override
    public void opcionesDeJuego(){
        subtitulo.setText("Elegir cantidad de Jugadores");
        textArea.setText("The Game - Quick & Easy\n\n2. Jugadores\n3. Jugadores\n4. Jugadores\n5. Volver");
        limpiarTextoTextField();
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (textField.getText()){
                    case "2", "3", "4" -> {
                        int cantidadJugadoresPartida = Integer.parseInt(textField.getText());
                        limpiarTextoTextField();
                        try {
                            crearPartida(cantidadJugadoresPartida);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "5" -> opciones();
                    default -> {}

                }
            }
        });
    }

    @Override
    public void reglas() {
        limpiarTextoTextField();
        eliminarActionListeners();
        subtitulo.setText("Reglas");
        String reglasTexto = "The Game Quick & Easy \n" +
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

        textArea.setText(reglasTexto);
        limpiarTextoTextField();

        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTextoTextField();
                menu();
            }
        });
    }

    @Override
    public void buscarPartidas() throws RemoteException{
        setEstado(Estados.EN_BUSCAR_PARTIDA);
        encontrarPartidas();
    }

    private void encontrarPartidas() throws RemoteException {
        subtitulo.setText("Buscando Partidas");
        textArea.setText("Partidas actuales\n'salir' para volver al Menú\n\n");
        limpiarTextoTextField();
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    opciones();
                }
            }
        });

        int i = 1;
        for (IPartida partida : controlador.getPartidas()){
            textArea.append("Partida " + i + ", Jugadores: " + partida.getCantidadJugadoresEnLaPartida() + " de " + partida.getCantidadJugadoresTotales() + " - " + partida.getEstado().name() + "\n");
            i++;
        }

        textArea.append("\nDigite el número de la partida que te quieres unir.");

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroPartida;
                try {
                    numeroPartida = Integer.parseInt(textField.getText());
                    if (numeroPartida >= 1 && numeroPartida <= controlador.getPartidas().size()){
                        IPartida partida = controlador.getPartidas().get(numeroPartida-1);
                        if (partida.getEstado() == EstadoPartida.EN_ESPERA){
                            setEstado(Estados.EN_JUEGO);
                            controlador.agregarJugadorAPartida(partida.getId());
                        }
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (NumberFormatException ex){
                    System.err.println("NÚMERO INVALIDO");
                }
            }
        });
    }

    @Override
    public void crearPartida(int cantidadJugadores) throws RemoteException{
        controlador.crearPartida(cantidadJugadores);
        controlador.agregarJugadorAPartida();
    }

    @Override
    public void esperandoJugadores() throws RemoteException{
        limpiarTextoTextField();
        eliminarActionListeners();
        subtitulo.setText("Esperando jugadores");
        mostrarJugadoresEnMesa();
    }

    private void mostrarJugadoresEnMesa() throws RemoteException{
        IPartida partida = controlador.getPartidaActual();
        textArea.setText("JUGADORES " + "[" + partida.getCantidadJugadoresEnLaPartida() + " de " + partida.getCantidadJugadoresTotales() + "]\n");
        int i = 1;
        for (IJugador jugador : partida.getJugadoresEnLaPartida()){
            textArea.append(i + ". " + jugador.getNombre() + "\n");
        }
        verificarIniciarPartida(partida);
    }

    private void verificarIniciarPartida(IPartida partida) throws RemoteException{
        if (partida.getCantidadJugadoresTotales() == partida.getCantidadJugadoresEnLaPartida()){
            empezarPartida();
        }
    }

    @Override
    public void empezarPartida() throws RemoteException{
        subtitulo.setText("Jugador: " + controlador.getNombreJugador());
        controlador.empezarPartida();
        mostrarPartida();
    }

    @Override
    public void mostrarPartida() throws RemoteException{
        mostrarTurno();
        mostrarTablero();
        mostrarCartasRestantes();
        mostrarCartas();
        jugarTurno();
    }

    private void mostrarCartas() throws RemoteException {
        textArea.append("\n\nTus cartas\n");
        for (IJugador jugador : controlador.getPartidaActual().getJugadoresEnLaPartida()) {
            if (jugador.getNombre().equals(controlador.getNombreJugador())) {
                System.out.println("-------------------");
                ICarta carta1Vista = jugador.getPrimeraCartaDelJugador();
                ICarta carta2Vista = jugador.getSegundaCartaDelJugador();

                if (jugador.isPrimeraCarta_en_mano()) {
                    actionCarta1 = true;
                    textArea.append("\nPRIMER CARTA: ");
                    textArea.append("[" + carta1Vista.getNumero() + " " + carta1Vista.getColor().name() + "]");
                } else {
                    actionCarta1 = false;
                    textArea.append("(tomar carta del mazo)");
                }

                if (jugador.isSegundaCarta_en_mano()) {
                    actionCarta2 = true;
                    textArea.append("\nSEGUNDA CARTA: ");
                    textArea.append("[" + carta2Vista.getNumero() + " " + carta2Vista.getColor().name() + "]");
                } else {
                    actionCarta2 = false;
                    textArea.append("(tomar carta del mazo)");
                }
            }
        }
    }

    private void mostrarTurno() throws RemoteException {
        IJugador turno_jugador = controlador.getTurno();
        textArea.setText("\t\tTurno de " + turno_jugador.getNombre());
        if (turno_jugador.getNombre().equals(controlador.getNombreJugador())){
            textField.setEditable(true);
            action = true;
        } else{
            textField.setEditable(false);
            action = false;
        }
    }

    private void mostrarTablero() throws RemoteException {
        ICarta cartaAlta = controlador.getPartidaActual().getCartaAlta();
        ICarta cartaBaja = controlador.getPartidaActual().getCartaBaja();
        textArea.append("\n\t\tCARTA ALTA: " + "[" + cartaAlta.getNumero() + " " + cartaAlta.getColor() + "]");
        textArea.append("\n\t\tCARTA BAJA: " + "[" + cartaBaja.getNumero() + " " + cartaBaja.getColor() + "]");
    }

    private void mostrarCartasRestantes() throws RemoteException{
        IMazo mazo = controlador.getPartidaActual().getMazo();
        textArea.append("\n\t\tMazo (" + mazo.getCantidadCartas() + " cartas restantes)");
    }

    @Override
    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    private void jugarTurno(){
        textArea.append("\n\nSelecciona una carta y la pila (por ejemplo, '1 alta' o '2 baja',\no escribe 'mazo' luego de tirar al menos una carta) ");
        limpiarTextoTextField();
        eliminarActionListeners();
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (action){
                    String seleccion = textField.getText();
                    switch (seleccion){
                        case "mazo" -> {
                            try {
                                controlador.siguienteTurno();
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                            try {
                                if (!controlador.verificarGameOver()){
                                    controlador.verificarGameWin();
                                }
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        case "1 alta" -> {
                            try {
                                if (actionCarta1){
                                    controlador.jugarTurno(0,0);
                                }
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        case "1 baja" -> {
                            try {
                                if (actionCarta1){
                                    controlador.jugarTurno(0,1);
                                }
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        case "2 alta" -> {
                            try {
                                if (actionCarta2){
                                    controlador.jugarTurno(1,0);
                                }
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        case "2 baja" -> {
                            try {
                                if (actionCarta2){
                                    controlador.jugarTurno(1,1);
                                }
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        default -> {}
                    }
                }
            }
        });
    }

    @Override
    public void mostrarGameOver(){
        limpiarTextoTextField();
        eliminarActionListeners();
        textField.setEditable(true);
        textArea.append("\n\t\t--------GAME OVER--------\nDigite 'salir' para volver al menú.");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    resetPartidaEnJuego();
                    menu();
                }
            }
        });
    }

    @Override
    public void mostrarGameWin() throws RemoteException {
        limpiarTextoTextField();
        eliminarActionListeners();
        textField.setEditable(true);
        textArea.append("\n\t\t--------GAME WIN--------\nDigite 'salir' para volver al menú.");
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    resetPartidaEnJuego();
                    menu();
                }
            }
        });
    }

    @Override
    public void ranking() throws RemoteException {
        limpiarTextoTextField();
        eliminarActionListeners();
        textArea.setText("\t\tRANKING\n\n");

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("salir")){
                    resetPartidaEnJuego();
                    menu();
                }
            }
        });

        Map<String, Integer> rank = controlador.getRanking();
        int i = 1;

        for (Map.Entry<String, Integer> entry: rank.entrySet()){
            textArea.append(i + ". " + "Jugador: " + entry.getKey() + "    -    " + entry.getValue() + "  P-G\n");
            i++;
        }
        textArea.append("\nDigite 'salir' para volver al menú.");

    }


    // COSAS PARA OPERAR APARTE
    private void limpiarTextoTextField(){
        textField.setText("");
    }

    public void resetPartidaEnJuego(){
        controlador.setId_partida_actual(-1);
    }

    private void eliminarActionListeners() {
        ActionListener[] listeners = enviar.getActionListeners();
        for (ActionListener listener : listeners) {
            enviar.removeActionListener(listener);
        }
    }

    @Override
    public Estados getEstado(){
        return estado;
    }

    @Override
    public Controller getControlador() {
        return controlador;
    }
}
