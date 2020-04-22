package Network;

import View.MainView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerComunication extends Thread  {

    private boolean isOn;
    private MainView view;
    private Socket socketToServer;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;

    public ServerComunication(MainView vista) {
        try {
            this.isOn = false;
            this.view = vista;
            // connectem amb el servidor i obrim els canals de comunicacio
            this.socketToServer = new Socket(50000));
            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("*** ESTA EL SERVIDOR EN EXECUCIO? ***");
        }
    }

}
//sss