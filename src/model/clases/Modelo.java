package model.clases;

import controller.interfaces.IObserver;
import model.enums.Eventos;
import model.interfaces.IJugador;
import model.interfaces.IPartida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modelo {
    private List<IJugador> usuarios;
    private List<IObserver> observadores;
    private final Map<Integer, IPartida> partidas;

    public Modelo(){
        usuarios = new ArrayList<>();
        observadores = new ArrayList<>();
        partidas = new HashMap<>();
    }

    // GESTION PARTIDAS
    public IPartida crearPartida(int cantidadJugadores){
        IPartida nueva_partida = new Partida(cantidadJugadores);
        this.partidas.put(nueva_partida.getId(), nueva_partida);
        notificarObservers(nueva_partida.getId(), Eventos.CAMBIO_BUSCAR_PARTIDA);
        return nueva_partida;
    }

    public List<IPartida> getPartidas(){
        return new ArrayList<>(partidas.values());
    }

    public IPartida getPartida(int id){
        return partidas.get(id);
    }

    public void agregarJugadorAPartida(IPartida partida, IJugador jugador){
        partida.agregarJugador(jugador);
        notificarObservers(partida.getId(), Eventos.CAMBIO_ESPERANDO_JUGADORES);
        notificarObservers(partida.getId(), Eventos.CAMBIO_BUSCAR_PARTIDA);
    }

    // GESTION USUARIOS-OBSERVADORES
    public IJugador conectarUsuario(String nombre, IObserver observador){
        IJugador jugador = new Jugador(nombre);
        usuarios.add(jugador);
        observadores.add(observador);
        return jugador;
    }

    public List<IJugador> getUsuarios(){
        return usuarios;
    }

    public void notificarObservers(int id_partida, Object arg){
        for (IObserver observer : observadores){
            observer.update(id_partida, arg);
        }
    }
}
