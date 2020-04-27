package Model.Network;

import Model.NetworkConfiguration;
import View.MainView;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerComunication extends Thread  {

    private boolean isOn;
    private MainView view;
    private Socket socketToServer;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;


    public ServerComunication() {
        try {
            this.isOn = false;
            // connectem amb el servidor i obrim els canals de comunicacio
            this.socketToServer = new Socket(NetworkConfiguration.SERVER_IP,NetworkConfiguration.SERVER_PORT);
            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("*** ESTA EL SERVIDOR EN EXECUCIO? ***");
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

    public void run() {
        while (isOn) {
            System.out.println("sembla que funciona");

        }
        stopServerComunication();
    }

}
