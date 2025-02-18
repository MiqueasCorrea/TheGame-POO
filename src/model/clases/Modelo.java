package model.clases;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import controller.interfaces.IObserver;
import model.enums.EstadoPartida;
import model.enums.Eventos;
import model.extras.GeneradorUsuarioID;
import model.interfaces.ICarta;
import model.interfaces.IJugador;
import model.interfaces.IModelo;
import model.interfaces.IPartida;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modelo extends ObservableRemoto implements IModelo, Serializable {
    private List<IJugador> usuarios;
    private final Map<Integer, IPartida> partidas;

    public Modelo(){
        usuarios = new ArrayList<>();
        partidas = new HashMap<>();
    }

    // GESTION PARTIDAS
    @Override
    public IPartida crearPartida(int cantidadJugadores) throws RemoteException {
        IPartida nueva_partida = new Partida(cantidadJugadores);
        nueva_partida.setEstado(EstadoPartida.EN_ESPERA);
        this.partidas.put(nueva_partida.getId(), nueva_partida);
        notificarObservadores(new ManejadorEventos(nueva_partida.getId(), Eventos.CAMBIO_BUSCAR_PARTIDA));
        return nueva_partida;
    }

    @Override
    public List<IPartida> getPartidas() throws RemoteException{
        return new ArrayList<>(partidas.values());
    }

    @Override
    public IPartida getPartida(int id) throws RemoteException{
        return partidas.get(id);
    }

    @Override
    public void agregarJugadorAPartida(int id_partida, IJugador jugador) throws RemoteException{
        IPartida partida = partidas.get(id_partida);
        partida.agregarJugador(jugador);
        notificarObservadores(new ManejadorEventos(partida.getId(), Eventos.CAMBIO_ESPERANDO_JUGADORES));
        notificarObservadores(new ManejadorEventos(partida.getId(), Eventos.CAMBIO_BUSCAR_PARTIDA));
    }

    @Override
    public void empezarPartida(int id_partida_actual) throws RemoteException{
        IPartida partida = getPartida(id_partida_actual);
        if (partida.getEstado() != EstadoPartida.EN_JUEGO){
            partida.setEstado(EstadoPartida.EN_JUEGO);
            partida.establecerTurnos();
            partida.repartirCartas();
        }
    }

    @Override
    public IJugador getTurno(int id_partida) throws RemoteException {
        return getPartida(id_partida).getTurno();
    }

    @Override
    public void jugarTurno(int id_partida, int zonasMano, int zonasCentro) throws RemoteException {
        getPartida(id_partida).jugarTurno(zonasMano, zonasCentro);
        notificarObservadores(new ManejadorEventos(id_partida, Eventos.ACTUALIZACION_CARTA));
    }

    // GESTION USUARIOS-OBSERVADORES
    @Override
    public IJugador conectarUsuario(String nombre) throws RemoteException{
        IJugador jugador = new Jugador(nombre);
        usuarios.add(jugador);
        return jugador;
    }

    @Override
    public List<IJugador> getUsuarios() throws RemoteException{
        return usuarios;
    }

}
