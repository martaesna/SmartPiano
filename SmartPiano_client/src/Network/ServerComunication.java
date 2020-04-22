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
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 40000;

    public ServerComunication(MainView vista) {
        try {
            this.isOn = false;
            this.view = vista;
            // connectem amb el servidor i obrim els canals de comunicacio
            this.socketToServer = new Socket(SERVER_IP,SERVER_PORT);
            this.dataOut = new DataOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("*** ESTA EL SERVIDOR EN EXECUCIO? ***");
        }
    }

}
