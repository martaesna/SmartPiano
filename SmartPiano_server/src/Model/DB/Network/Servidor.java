package Model.DB.Network;
import Controller.MainViewController;
import Model.DB.Usuari;
import View.MainView;
import Network.NetworkConfiguration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor extends Thread{

    private boolean isOn;
    private String user;
    private String password;
    private int portPeticions;
    private ServerSocket sSocket;
    private LinkedList<ServidorDedicat> dServers; // els servidors dedicats
    private MainViewController controller; // relació amb el controlador de la vista

    //constructor del servidor
    public Servidor() {
        try {
            this.isOn = false;
            //creem un socket al port 2222
            this.controller = controller;
            this.sSocket = new ServerSocket(NetworkConfiguration.SERVER_PORT);
            this.dServers = new LinkedList<ServidorDedicat>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //////////////////A IMPLEMENTAR/////////////////////////

    //public boolean registerUser(Usuari u){}
    //ublic boolean loginUser(Usuari u){}
    //public void gestionaRepros(Song c){}


    //////////////////A IMPLEMENTAR/////////////////////////

    //iniciem el thread del servidor
    public void startServer() {
        isOn = true;
        System.out.println("Servidor iniciat...");
        this.start();
    }

    //aturem el thread del servidor
    public void stopServer() {
        isOn = false;
        this.interrupt();
    }
    public void mostraClients(){
        System.out.println("El servidor te" + dServers.size() + "clients connectats");
    }

    public void run() {
        Usuari user;
        while (isOn) {
            try {
                // esperem peticions de connexio de clients
                System.out.println("Esperant peticio...");
                Socket sClient = sSocket.accept();

                //segurament caldra afegir mes parametres
                ServidorDedicat dsClient = new ServidorDedicat(sClient, controller, this);

                //afegim a la cua de servidors dedicats el client q sacaba de conectar
                dServers.add(dsClient);

                //encenem el servidor dedicat
                dsClient.startDedicatedServer();
                mostraClients();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // aturem tots els servidors dedicats creats
        // quan ja no atenem mes peticions de clients
        for (ServidorDedicat dServer : dServers) {
            dServer.startDedicatedServer();
        }
    }
}

