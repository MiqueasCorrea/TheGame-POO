package model;

import model.interfaces.IJuego;
import model.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Juego implements IJuego {
    private List<Jugador> usuarios;
    private List<IObserver> observadores;
    private List<Partida> partidasActuales;

    public Juego(){
        usuarios = new ArrayList<>();
        observadores = new ArrayList<>();
        partidasActuales = new ArrayList<>();
    }

    @Override
    public List<Partida> getPartidas(){
        return partidasActuales;
    }

    public void agregarPartida(Partida partida_nueva){
        this.partidasActuales.add(partida_nueva);
    }

    @Override
    public Jugador conectarUsuario(String nombre, IObserver observador){
        Jugador jugador = new Jugador(nombre);
        usuarios.add(jugador);
        observadores.add(observador);
        return jugador;
    }

    @Override
    public List<Jugador> getUsuarios(){
        return usuarios;
    }

    @Override
    public void notificarObservers(Partida partida, Object arg){
        for (IObserver observer : observadores){
            observer.update(partida, arg);
        }
    }
}
