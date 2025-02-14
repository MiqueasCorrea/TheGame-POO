/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.vistas;
import model.clases.Modelo;
import model.enums.Eventos;
import model.interfaces.IPartida;
import view.frames.*;
import view.interfaces.IVista;
import model.enums.Estados;
import controller.Controller;
import model.clases.Partida;

import javax.swing.*;
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
    private Estados estado;
    
    public VistaGrafica(Modelo modelo){
        this.controlador = new Controller(modelo);
        this.controlador.setVista(this);
        login = new Login();
        menu = new Menu();
        opciones = new Opciones();
        opcionesDeJuego = new OpcionesDeJuego();
        buscarPartida = new BuscarPartida();
        partida_en_juego = new PartidaEnJuego();
        reglas = new Reglas();
        
        login.setVistaGrafica(this);
        menu.setVistaGrafica(this);
        opciones.setVistaGrafica(this);
        opcionesDeJuego.setVistaGrafica(this);
        buscarPartida.setVistaGrafica(this);
        partida_en_juego.setVistaGrafica(this);
        reglas.setVistaGrafica(this);
        login();
    }

    @Override
    public void login(){
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
    public void buscarPartidas(){
        estado = Estados.EN_BUSCAR_PARTIDA;
        buscarPartida.setVisible(true);
        buscarPartida.encontrarPartidas();
    };

    @Override
    public void crearPartida(int cantidadJugadores){
        IPartida partida = controlador.crearPartida(cantidadJugadores);
        controlador.agregarJugadorAPartida(partida, controlador.getJugador());
        controlador.setId_partida_actual(partida.getId());
//        esperandoJugadores();
    };

    @Override
    public void esperandoJugadores(){
        estado = Estados.EN_ESPERANDO_JUGADORES;
        partida_en_juego.setVisible(true);
        partida_en_juego.mostrarJugadoresEnMesa();
        validarEmpezarPartida();
    };

    public void validarEmpezarPartida(){
        if (controlador.getPartidaActual().getCantidadJugadoresEnLaPartida() == controlador.getPartidaActual().getCantidadJugadoresTotales()){
            empezarPartida();
        }
    }

    @Override
    public void empezarPartida(){
        estado = Estados.EN_JUEGO;
        System.out.println("EMPIEZA LA PARTIDA!");
    };

    @Override
    public Estados getEstado(){
        return estado;
    }

    public Controller getControlador(){
        return controlador;
    }
}
