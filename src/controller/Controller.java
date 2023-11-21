package controller;
import model.*;
import model.enums.Estados;
import model.enums.Eventos;
import model.interfaces.IObserver;
import view.IVista;
import java.util.List;

public class Controller implements IObserver {
    private Juego modelo;
    private Jugador jugador;
    private IVista vista;

    public Controller(Juego modelo) {
        this.modelo = modelo;
    }

    public Juego getModelo(){
        return this.modelo;
    }

    public List<Partida> getPartidas(){
        return this.modelo.getPartidas();
    }
    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public void conectarJugador(String nombre){ // set Jugador
        this.jugador = this.modelo.conectarUsuario(nombre, this);
    }

    public Jugador getJugador(){
        return jugador;
    }


    public void agregarPartida(Partida partida){
        this.modelo.agregarPartida(partida);
    }


    @Override
    public void update(Partida partida, Object arg){
        if (arg instanceof Eventos){
            switch ((Eventos) arg){
                case CAMBIO_LISTA_ESPERA:
                    if (this.vista.getEstado() == Estados.EN_BUSCAR_PARTIDA){
                        this.vista.mostrarBuscarPartidas();
                    }
                    break;
                case SE_UNIO_JUGADOR:
                    if (this.vista.getEstado() == Estados.EN_SALA_ESPERA){
                        this.vista.mostrarEsperandoJugadores(partida);
                    }
                    break;
                case ACTUALIZACION_CARTA:
                    if (this.vista.getEstado() == Estados.EN_JUEGO){
                        this.vista.mostrarJuego(partida);
                    }
                    break;
                case CAMBIO_TURNO:
                    if (this.vista.getEstado() == Estados.EN_JUEGO){
                        this.vista.mostrarJuego(partida);
                    }
                    break;
                case GAME_OVER:
                    if (this.vista.getEstado() == Estados.EN_JUEGO){
                        this.vista.gameOver(partida);
                    }
                    break;
                case GAME_WIN:
                    if (this.vista.getEstado() == Estados.EN_JUEGO){
                        this.vista.gameWin(partida);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
