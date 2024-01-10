/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.vistagrafica;

/**
 *
 * @author miqueas
 */
public class VistaGrafica {
    private Login login;
    private Menu menu;
    private OpcionesDeJuego opcionesDeJuego;
    private Reglas reglas;
    
    public VistaGrafica(){
        login = new Login();
        menu = new Menu();
        opcionesDeJuego = new OpcionesDeJuego();
        reglas = new Reglas();
        
        login.setVistaGrafica(this);
        menu.setVistaGrafica(this);
        opcionesDeJuego.setVistaGrafica(this);
        reglas.setVistaGrafica(this);
        mostrarVistaGrafica();
    }
    
    private void mostrarVistaGrafica(){
       mostrarLoginGrafica();
    }
    
    private void mostrarLoginGrafica(){
        login.setVisible(true);
    }
    
    private void ocultarLoginGrafica(){
        login.setVisible(false);
    }
    
    public void mostrarMenuGrafica(){
        ocultarLoginGrafica();
        ocultarOpcionesJuegoGrafica();
        menu.setVisible(true);
    }
    
    private void ocultarMenuGrafica(){
        menu.setVisible(false);
    }
    
    public void mostrarOpcionesJuegoGrafica(){
        ocultarMenuGrafica();
        opcionesDeJuego.setVisible(true);
    }
    
    public void ocultarOpcionesJuegoGrafica(){
        opcionesDeJuego.setVisible(false);
    }
    
    public void mostrarReglas(){
        ocultarMenuGrafica();
        reglas.setVisible(true);
    }
    
}
