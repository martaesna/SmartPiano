package Model.Network;

import Model.Missatge;
import Model.NetworkConfiguration;
import View.LoginView;
import View.MainView;
import java.io.*;
import java.net.Socket;
import Model.Json.*;
import Model.User;
import View.MenuView;
import View.RegisterView;

import javax.swing.JOptionPane;

public class ServerComunication extends Thread  {

    private boolean isOn;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    private MenuView mv;
    private LoginView lv;
    private RegisterView rv;

    public ServerComunication(MenuView mv, LoginView lv, RegisterView rv) {
        try {
            this.rv = rv;
            this.mv = mv;
            this.lv = lv;
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
    public void enviaLogin (User usuari){
        try{
            objectOut.writeObject(usuari);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        while (isOn) {
            try {
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                objectIn = new ObjectInputStream(socketToServer.getInputStream());
                Object object = objectIn.readObject();
                Missatge missatge;
                missatge = (Missatge) object;
                String accio = missatge.getAccio();

                switch (accio) {
                    case "errorRegistre":
                        rv.setVisible(true);
                        JOptionPane.showMessageDialog(null, "L'usuari ja existeix", "Error Registre", JOptionPane.ERROR_MESSAGE);
                        break;
                    case "errorLogin":
                        JOptionPane.showMessageDialog(null, "L'usuari o contrasenya no son correctes", "Error Login", JOptionPane.ERROR_MESSAGE);
                        mv.setVisible(false);
                        break;
                    case "loginCorrecte":
                        mv.setVisible(true);
                        lv.setVisible(false);
                        break;
                    case "registreCorrecte":
                        lv.setVisible(true);
                        JOptionPane.showMessageDialog(null, "T'has registrat amb èxit!", "Registre Correcte", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        stopServerComunication();
    }
}