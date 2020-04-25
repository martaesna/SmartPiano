package Model.DB.Network;

import Controller.MainViewController;
import Model.DB.Usuari;
import View.MainView;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;


public class ServidorDedicat extends Thread {
    private boolean isRunning;
    private Socket sClient;
    private DataInputStream dataInput;
    private ObjectOutputStream objectOut;
    private LinkedList<ServidorDedicat> clients;
    private MainView view;
    private Servidor servidor;
    private MainViewController controller;


    public ServidorDedicat(MainView view, Socket sClient, MainViewController controller, Servidor servidor) {
        this.view = view;
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
            ObjectInputStream ois = new ObjectInputStream(sClient.getInputStream());
            DataOutputStream dos = new DataOutputStream(sClient.getOutputStream());
            dos.writeUTF("Estic llest");
            int value = (Integer) ois.readObject();
            System.out.println("Client diu: " + value);
            Usuari user = (Usuari) ois.readObject();
            System.out.println("Client també diu: " + user.toString());
            try {
                sleep(10000);
            } catch (InterruptedException e) {
            }
            dos.writeUTF("Ok " + value);
            System.out.println("Ok " + value);
        } catch (IOException | ClassNotFoundException e) {

        } finally {
            try {
                sClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
