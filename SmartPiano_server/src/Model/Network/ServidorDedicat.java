package Model.Network;

import Controller.MainViewController;
import Model.User;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import static Model.DDBB.SQLOperations.registreUsuari;


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
            while (isRunning) {
                //while is running(bucle infinit)
                System.out.println("estem al run");
                //dos = new DataOutputStream(sClient.getOutputStream());
                try {
                    Object object = ois.readObject();
                    //si el object es "tipus" User entra al if
                    if (object instanceof User) {
                        User usuari = (User) object;
                        registreUsuari(usuari.getName(), usuari.getMail(), usuari.getPassword());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
