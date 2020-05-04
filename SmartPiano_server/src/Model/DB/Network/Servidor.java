package Model.DB.Network;
import Controller.MainViewController;
import Model.DB.Json.Data;
import Model.DB.Json.JsonReader;
import Model.DB.Usuari;
import View.MainView;
import Model.NetworkConfiguration;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor extends Thread{

    private String user;
    private String password;
    private int portPeticions;
    private ServerSocket sSocket;
    private boolean isRunning;
    private LinkedList<ServidorDedicat> dServers; // els servidors dedicats
    private MainViewController controller;
    private Data data;
    private MainView view;



    /////////////NO ESTAN AL UML/////////////
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
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

    //iniciem servidor
    public void startServer() {
        isRunning = true;
        System.out.println("Servidor iniciat...");
        this.start();
    }

    //aturem servidor
    public void stopServer() {
        isRunning = false;
        this.interrupt();
    }
    public void mostraClients(){
        System.out.println("El servidor te " + dServers.size() + " clients connectats");
    }

    public void run() {
        Usuari user;
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
                dsClient.run();


                // llegim objecte usuari
                objectIn = new ObjectInputStream(sClient.getInputStream());
                user = (Usuari)objectIn.readObject();


                // tanquem la connexio amb el client
                //sClient.close();

                //System.out.println(user.getNickname);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }





}

