package controller;
import model.*;
import view.IVista;
import java.util.List;

public class Controller implements IObserver {
    private IJuego modelo;
    private Jugador jugador;
    private IVista vista;

    public Controller(IJuego modelo) {
        this.modelo = modelo;
    }

    public IJuego getModelo(){
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




//    public void actualizarCartas(Carta cartaAlta, Carta cartaBaja) {
//        if (cartaAlta != null){
//            modelo.setCartaAlta(cartaAlta);
//        }
//        if (cartaBaja != null){
//            modelo.setCartaBaja(cartaBaja);
//        }
//    }


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
                default:
                    break;
            }
        }
    }
}
