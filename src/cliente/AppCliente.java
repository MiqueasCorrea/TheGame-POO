package cliente;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import controller.Controller;
import view.interfaces.IVista;
import view.vistas.VistaConsola;
import view.vistas.VistaGrafica;

public class AppCliente {

    public static void main(String[] args) throws RemoteException {
        String[] opciones = {"Vista Consola", "Vista Grafica"};
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un tipo de vista",
                "TIPO VISTA",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        boolean vista_grafica = "Vista Grafica".equals(seleccion);
        boolean vista_consola = !vista_grafica;

        ArrayList<String> ips = Util.getIpDisponibles();
        String ip = "127.0.0.1";
//        String ip = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                ips.toArray(),
//                null
//        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                9999
        );
        String ipServidor = "127.0.0.1";
//        String ipServidor = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione la IP en la corre el servidor", "IP del servidor",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                null,
//                null
//        );
        String portServidor = "8888";
//        String portServidor = (String) JOptionPane.showInputDialog(
//                null,
//                "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                null,
//                8888
//        );
        IVista vista;
        if (vista_grafica){
            vista = new VistaGrafica();
        } else{
            vista = new VistaConsola();
        }
        Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
        vista.login();
        try {
            c.iniciar(vista.getControlador());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RMIMVCException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}