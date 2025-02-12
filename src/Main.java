//import controller.Controller;
import model.*;
import view.IVista;
import view.VistaConsola;
import controller.Controller;
import view.vistagrafica.Login;
import view.vistagrafica.VistaGrafica;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Juego modelo = new Juego();
        Controller controlador1 = new Controller(modelo);
        VistaGrafica vistaGrafica = new VistaGrafica(controlador1);

        Controller controlador2 = new Controller(modelo);
        VistaGrafica vistaGrafica2 = new VistaGrafica(controlador2);
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