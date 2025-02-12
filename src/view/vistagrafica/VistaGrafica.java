/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.vistagrafica;
import model.enums.Eventos;
import view.IVista;
import model.enums.Estados;
import controller.Controller;
import model.Partida;
import model.enums.Estados;

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
    
    public VistaGrafica(Controller controlador){
        this.controlador = controlador;
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
        mostrarVistaGrafica();
    }
    
    private void mostrarVistaGrafica(){
       mostrarInicioSesion();
    }
    
    @Override
    public void mostrarInicioSesion(){
        login.setVisible(true);
    }
    
    private void ocultarLoginGrafica(){
        login.setVisible(false);
    }
    
    public void mostrarMenuGrafica(){
        ocultarLoginGrafica();
        menu.setVisible(true);
    }
    
    private void ocultarMenuGrafica(){
        menu.setVisible(false);
    }
    
    public void mostrarOpciones(){
        opciones.setVisible(true);
    }
    
    public void ocultarOpciones(){
        opciones.setVisible(false);
    }
    
    public PartidaEnJuego getPartidaEnJuego(){
        return partida_en_juego;
    }
    
    public void mostrarOpcionesJuegoGrafica(){
        ocultarMenuGrafica();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                opcionesDeJuego.setVisible(true);
            }
        });
    }
    
    public void ocultarOpcionesJuegoGrafica(){
        opcionesDeJuego.setVisible(false);
    }
    
    public void mostrarReglas(){
        ocultarMenuGrafica();
        reglas.setVisible(true);
    }
    
    public List<Partida> encontrarPartidas(){
        return controlador.getPartidas();
    }
    public void conectarJugador(String nombre){
        controlador.conectarJugador(nombre);
    }

    public void empezarPartida(Partida partida){};
    public void mostrarJuego(Partida partida){};
    public void mostrarEsperandoJugadores(Partida nueva_partida){};

    @Override
    public void mostrarBuscarPartidas(){
        estado = Estados.EN_BUSCAR_PARTIDA;
        buscarPartida.encontrarPartidas();
        System.out.println("Llego al buscar");
        buscarPartida.setVisible(true);
    };
    public void crearPartida(int cantidadJugadoresPartida){
        estado = Estados.EN_SALA_ESPERA;
        Partida nueva_partida = new Partida(cantidadJugadoresPartida);
        controlador.agregarPartida(nueva_partida);
        nueva_partida.agregarJugador(controlador.getJugador());
        controlador.getModelo().notificarObservers(nueva_partida, Eventos.CAMBIO_BUSCAR_PARTIDA);
//        mostrarEsperandoJugadores(nueva_partida);
    };
    public void gameOver(Partida partida){};
    public void gameWin(Partida partida){};
    public Estados getEstado(){
        return estado;
    }
    
}
