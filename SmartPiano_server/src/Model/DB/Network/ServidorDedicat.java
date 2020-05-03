package Model.DB.Network;

import Controller.MainViewController;
import Model.DB.Usuari;
import View.MainView;
import Model.DB.User_Client;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;


public class ServidorDedicat extends Thread {
    private boolean isRunning;
    private Socket sClient;
    private DataInputStream dataInput;
    private ObjectOutputStream objectOut;
    private LinkedList<ServidorDedicat> clients;
    private Servidor servidor;
    private MainViewController controller;
    private User_Client userclient;


    public ServidorDedicat(Socket sClient, MainViewController controller, Servidor servidor) {
        this.sClient = sClient;
        this.controller = controller;
        this.servidor = servidor;
    }

    public void startDedicatedServer() {
        isRunning = true;
        this.start();
    }

    public void stopDedicatedServer() {
        // aturem el servidor dedicat
        this.isRunning = false;
        this.interrupt();
    }



    @Override
    public void run () {
        try {
            ObjectInput ois = new ObjectInputStream(sClient.getInputStream());
            System.out.println("estem al run");
            DataOutput dos = new DataOutputStream(sClient.getOutputStream());
            try {
                userclient = (User_Client) ois.readObject();
                System.out.println(userclient.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
