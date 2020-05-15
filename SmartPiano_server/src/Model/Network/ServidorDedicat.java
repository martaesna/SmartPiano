package Model.Network;

import Controller.MainViewController;
import Model.Missatge;
import Model.User;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import static Model.DDBB.SQLOperations.existeixUsuari;
import static Model.DDBB.SQLOperations.loginUsuariCorrecte;
import static Model.DDBB.SQLOperations.registreUsuari;


public class ServidorDedicat extends Thread {
    private boolean isRunning;
    private Socket sClient;
    private DataInputStream dataInput;
    private ObjectInputStream ois;
    private ObjectOutputStream objectOut;
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

    public void enviaMissatge (Object missatge){
        try {
            objectOut = new ObjectOutputStream(sClient.getOutputStream());
            objectOut.writeObject(missatge);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    Missatge missatge;
                    missatge = (Missatge) object;
                    String accio = missatge.getAccio();
                    String accioResposta;
                    Missatge missatgeResposta;
                    switch (accio) {
                        case "registre":
                            usuari = (User)missatge.getData();
                            if (existeixUsuari(usuari.getName())) {
                                accioResposta = "errorRegistre";
                            } else {
                                registreUsuari(usuari.getName(), usuari.getMail(), usuari.getPassword());
                                accioResposta = "registreCorrecte";
                            }
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object) missatgeResposta);
                            break;
                        case "login":
                            //retornar info al client
                            usuari = (User)missatge.getData();
                            if (!loginUsuariCorrecte(usuari.getName(), usuari.getPassword())) {
                                accioResposta = "errorLogin";
                            } else {
                                accioResposta = "loginCorrecte";
                            }
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;
                    }
                    //si el object es "tipus" User entra al if
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
