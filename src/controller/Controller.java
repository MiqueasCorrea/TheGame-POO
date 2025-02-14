package controller;
import model.clases.Modelo;
import model.clases.Jugador;
import model.clases.Partida;
import model.enums.Estados;
import model.enums.Eventos;
import controller.interfaces.IObserver;
import model.interfaces.IJugador;
import model.interfaces.IPartida;
import view.interfaces.IVista;
import java.util.List;

public class Controller implements IObserver {
    private Modelo modelo;
    private IJugador jugador;
    private IVista vista;
    private int id_partida_actual;

    public Controller(Modelo modelo) {
        this.modelo = modelo;
    }

    public Modelo getModelo(){
        return this.modelo;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    // GESTION PARTIDAS
    public List<IPartida> getPartidas(){
        return this.modelo.getPartidas();
    }

    public IPartida crearPartida(int cantidadJugadores){
        return modelo.crearPartida(cantidadJugadores);
    }

    public void agregarJugadorAPartida(IPartida partida, IJugador jugador){
        modelo.agregarJugadorAPartida(partida, jugador);
    }

    public void setId_partida_actual(int id){
        this.id_partida_actual = id;
    }

    public IPartida getPartidaActual(){
        return modelo.getPartida(id_partida_actual);
    }

    // GESTION USUARIOS-OBSERVADORES
    public void conectarJugador(String nombre){
        this.jugador = this.modelo.conectarUsuario(nombre, this);
    }

    public IJugador getJugador(){
        return jugador;
    }

    public String getNombreJugador(){
        return jugador.getNombre();
    }

    public int getIdJugador(){
        return jugador.getId();
    }

    @Override
    public void update(int id_partida, Object arg) {
        if (arg instanceof Eventos evento) {

            switch (evento) {
                case CAMBIO_ESPERANDO_JUGADORES -> {
                    if (id_partida_actual != id_partida){return;}
                    if (vista.getEstado() == Estados.EN_ESPERANDO_JUGADORES) {
                        vista.esperandoJugadores();
                    }
                }
                case CAMBIO_BUSCAR_PARTIDA -> {
                    if (vista.getEstado() == Estados.EN_BUSCAR_PARTIDA) {
                        vista.buscarPartidas();
                    }
                }
//                case ACTUALIZACION_CARTA:
//                    if (id_partida_actual != id_partida){return;}
//                    if (this.vista.getEstado() == Estados.EN_JUEGO){
//                        this.vista.mostrarJuego(partida);
//                    }
//                    break;
//                case CAMBIO_TURNO:
//                    if (this.vista.getEstado() == Estados.EN_JUEGO){
//                        this.vista.mostrarJuego(partida);
//                    }
//                    break;
//                case GAME_OVER:
//                    if (this.vista.getEstado() == Estados.EN_JUEGO){
//                        this.vista.gameOver(partida);
//                    }
//                    break;
//                case GAME_WIN:
//                    if (this.vista.getEstado() == Estados.EN_JUEGO){
//                        this.vista.gameWin(partida);
//                    }
//                    break;
                default -> {}
            }
        }
    }
}
