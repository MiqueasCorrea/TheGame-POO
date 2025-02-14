package cliente;//import controller.Controller;
import model.clases.Modelo;
import controller.Controller;
import view.vistas.VistaGrafica;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Modelo modelo = new Modelo();
        VistaGrafica vistaGrafica = new VistaGrafica(modelo);
        VistaGrafica vistaGrafica2 = new VistaGrafica(modelo);
        VistaGrafica vistaGrafica3 = new VistaGrafica(modelo);
        VistaGrafica vistaGrafica4 = new VistaGrafica(modelo);

//        IVista vista1 = new VistaConsola(controlador1);
//        vista1.mostrarInicioSesion();

//        Controller controlador2 = new Controller(modelo);
//        IVista vista2 = new VistaConsola(controlador2);
//        vista2.mostrarInicioSesion();
//
//        Controller controlador3 = new Controller(modelo);
//        IVista vista3 = new VistaConsola(controlador3);
//        vista3.mostrarInicioSesion();
//
//        Controller controlador4 = new Controller(modelo);
//        IVista vista4 = new VistaConsola(controlador4);
//        vista4.mostrarInicioSesion();
    }
}