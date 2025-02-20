/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.vistas;
import model.clases.Modelo;
import model.enums.Eventos;
import model.interfaces.IModelo;
import model.interfaces.IPartida;
import view.frames.*;
import view.interfaces.IVista;
import model.enums.Estados;
import controller.Controller;
import model.clases.Partida;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author miqueas
 */
public class VistaGrafica implements IVista{
    private Controller controlador;
    private Login login;
    private Menu menu;
    private OpcionesDeJuego opcionesDeJuego;
    private Opciones opciones;
    private BuscarPartida buscarPartida;
    private PartidaEnJuego partida_en_juego;
    private Reglas reglas;
    private Ranking ranking;
    private Estados estado;
    
    public VistaGrafica() throws RemoteException {
        this.controlador = new Controller();
        this.controlador.setVista(this);
        login = new Login();
        menu = new Menu();
        opciones = new Opciones();
        opcionesDeJuego = new OpcionesDeJuego();
        buscarPartida = new BuscarPartida();
        partida_en_juego = new PartidaEnJuego();
        reglas = new Reglas();
        ranking = new Ranking();
        
        login.setVistaGrafica(this);
        menu.setVistaGrafica(this);
        opciones.setVistaGrafica(this);
        opcionesDeJuego.setVistaGrafica(this);
        buscarPartida.setVistaGrafica(this);
        partida_en_juego.setVistaGrafica(this);
        reglas.setVistaGrafica(this);
        ranking.setVistaGrafica(this);
    }

    @Override
    public void login() {
        login.setVisible(true);
    }

    @Override
    public void menu(){

        menu.setVisible(true);
    }

    @Override
    public void opciones(){
        opciones.setVisible(true);
    }

    @Override
    public void opcionesDeJuego(){
        opcionesDeJuego.setVisible(true);
    }

    @Override
    public void reglas(){
        reglas.setVisible(true);
    }

    @Override
    public void buscarPartidas() throws RemoteException{
        setEstado(Estados.EN_BUSCAR_PARTIDA);
        buscarPartida.setVisible(true);
        buscarPartida.encontrarPartidas();
    };

    @Override
    public void crearPartida(int cantidadJugadores) throws RemoteException {
        controlador.crearPartida(cantidadJugadores);
        controlador.agregarJugadorAPartida();
    };

    @Override
    public void esperandoJugadores() throws RemoteException{
        partida_en_juego.setVisible(true);
        partida_en_juego.mostrarJugadoresEnMesa();
    };

    @Override
    public void empezarPartida() throws RemoteException{
        controlador.empezarPartida();
        System.out.println("EMPIEZA LA PARTIDA!");
        mostrarPartida();
    };

    @Override
    public void mostrarPartida() throws RemoteException{
        partida_en_juego.setVisible(true);
        partida_en_juego.mostrarJugadoresEnMesa();
        partida_en_juego.mostrarCartas();
        partida_en_juego.mostrarTurno();
        partida_en_juego.mostrarTablero();
        partida_en_juego.mostrarCartasRestantes();
    }

    @Override
    public void mostrarGameOver(){
        partida_en_juego.mostrarGameOver();
    }

    @Override
    public void mostrarGameWin() throws RemoteException {
        partida_en_juego.mostrarGameWin();
    }

    @Override
    public Estados getEstado(){
        return estado;
    }

    @Override
    public Controller getControlador(){
        return controlador;
    }

    @Override
    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    @Override
    public void ranking() throws RemoteException {
        ranking.setVisible(true);
        ranking.datosRanking();
    }

    public void resetPartidaEnJuego(){
        this.partida_en_juego = new PartidaEnJuego();
        getControlador().setId_partida_actual(-1);
        partida_en_juego.setVistaGrafica(this);
    }
}
