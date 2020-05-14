package Model.Network;

import Model.Missatge;
import Model.NetworkConfiguration;
import View.MainView;

import java.io.*;
import java.net.Socket;
import Model.Json.*;
import Model.User;
public class ServerComunication extends Thread  {

    private boolean isOn;
    private MainView view;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;

    public ServerComunication() {
        try {
            ReadJson Rj = new ReadJson();
            Rj.llegeixJSON();
            this.isOn = false;
            // connectem amb el servidor i obrim els canals de comunicacio
            this.socketToServer = new Socket(Rj.getDades().getServerIP(),Rj.getDades().getListeningPort());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.dataIn = new DataInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("** ESTA EL SERVIDOR EN EXECUCIO? ***");
        }
    }

    public void startServerComunication() {
        // iniciem la comunicacio amb el servidor
        isOn = true;
        this.start();
    }

    public void stopServerComunication() {
        // aturem la comunicacio amb el servidor
        this.isOn = false;
        this.interrupt();
    }

    //------------------------------
    //------------PETA--------------
    //------------------------------
    public void enviaMissatge (Object missatge){
        try {
            objectOut.writeObject(missatge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (isOn) {
            try {
                objectIn = new ObjectInputStream(socketToServer.getInputStream());
                Object object = objectIn.readObject();
                System.out.println("skr");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            //System.out.println("sembla que funciona");
        }
        stopServerComunication();
    }
}