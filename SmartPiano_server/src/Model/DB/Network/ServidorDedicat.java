package Model.DB.Network;

import Controller.MainViewController;
import Model.DB.Usuari;
import View.MainView;
import Model.DB.User;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;


public class ServidorDedicat extends Thread {
    private boolean isRunning;
    private Socket sClient;
    private DataInputStream dataInput;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private LinkedList<ServidorDedicat> clients;
    private Servidor servidor;
    private MainViewController controller;
    private User usuari;


    public ServidorDedicat(Socket sClient, MainViewController controller, Servidor servidor) {
        this.sClient = sClient;
        this.controller = controller;
        this.servidor = servidor;
        this.isRunning = false;
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
            ois = new ObjectInputStream(sClient.getInputStream());
            System.out.println("estem al run");
            //dos = new DataOutputStream(sClient.getOutputStream());
            try {
                usuari = (User)ois.readObject();
                System.out.println(usuari.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
