package model.network;
import controller.MainViewController;
import model.json.Data;
import model.json.JsonReader;
import model.User;
import view.MainView;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor extends Thread{

    private User user;
    private String password;
    private int portPeticions;
    private ServerSocket sSocket;
    private boolean isRunning;
    private LinkedList<ServidorDedicat> dServers; // els servidors dedicats
    private MainViewController controller;
    private Data data;
    private MainView view;



    /////////////NO ESTAN AL UML/////////////
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private Socket sClient;
    /////////////NO ESTAN AL UML/////////////



    //constructor del servidor
    public Servidor() {
        try {
            JsonReader Rj = new JsonReader();
            Rj.llegeixJSON();
            //creem un socket al port 40000
            this.controller = controller;
            this.sSocket = new ServerSocket(Rj.getDades().getListeningPort());
            this.isRunning = false;
            this.dServers = new LinkedList<ServidorDedicat>();
            this.view = view;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //////////////////A IMPLEMENTAR/////////////////////////

    //public boolean registerUser(Usuari u){}
    //public boolean loginUser(Usuari u){}
    //public void gestionaRepros(Song c){}


    //////////////////A IMPLEMENTAR/////////////////////////

    /**
     * inicialitzem el servidor
     */
    public void startServer() {
        isRunning = true;
        System.out.println("Servidor iniciat...");
        this.start();
    }

    /**
     * aturem el servidor
     */
    public void stopServer() {
        isRunning = false;
        this.interrupt();
    }

    /**
     * mostra el nombre de clients connectats al servidor
     */
    public void mostraClients(){
        System.out.println("El servidor te " + dServers.size() + " clients connectats");
    }

    /**
     * manté el thread a l'espera de nous clients
     */
    public void run() {
        while (isRunning) {
            try {
                // esperem peticions de connexio de clients
                System.out.println("Esperant peticio...");
                sClient = sSocket.accept();

                //segurament caldra afegir mes parametres
                ServidorDedicat dsClient = new ServidorDedicat(sClient,controller,this);

                //afegim a la cua de servidors dedicats el client q sacaba de conectar
                dServers.add(dsClient);

                //encenem el servidor dedicat
                dsClient.startDedicatedServer();
                mostraClients();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

