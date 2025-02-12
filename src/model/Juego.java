package model;

import model.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Juego{
    private List<Jugador> usuarios;
    private List<IObserver> observadores;
    private List<Partida> partidasActuales;

    public Juego(){
        usuarios = new ArrayList<>();
        observadores = new ArrayList<>();
        partidasActuales = new ArrayList<>();
    }

    public List<Partida> getPartidas(){
        return partidasActuales;
    }

    public void agregarPartida(Partida partida_nueva){
        this.partidasActuales.add(partida_nueva);
    }

    public Jugador conectarUsuario(String nombre, IObserver observador){
        Jugador jugador = new Jugador(nombre);
        usuarios.add(jugador);
        observadores.add(observador);
        return jugador;
    }

    public List<Jugador> getUsuarios(){
        return usuarios;
    }

    public void notificarObservers(Partida partida, Object arg){
        for (IObserver observer : observadores){
            System.out.println("UN OBSERVADOR ENCONTRADO");
            observer.update(partida, arg);
        }
    }
}
