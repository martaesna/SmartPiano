package model.network;

import controller.AmicViewController;
import model.Amic;
import model.Missatge;
import view.*;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import model.json.*;
import model.User;

import javax.swing.JOptionPane;

public class ServerComunication extends Thread  {

    private boolean isOn;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private static ObjectOutputStream objectOut;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    private MenuView mv;
    private MainView mnv;
    private LoginView lv;
    private RegisterView rv;
    private AmicView av;

    public ServerComunication(MenuView mv, LoginView lv, RegisterView rv,MainView mnv) {
        try {
            this.rv = rv;
            this.mv = mv;
            this.lv = lv;
            this.mnv = mnv;
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
    public static void enviaMissatge(Object missatge){
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

                System.out.println("skr");
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
                    case "delete":
                        mnv.setVisible(true);
                    case "amicsTrobats":
                        LinkedList<Amic> amics = new LinkedList<>();
                        amics = (LinkedList<Amic>)missatge.getData();
                        AmicView av = new AmicView(amics);
                        AmicViewController avc = new AmicViewController(av);
                        av.amicController(avc);
                        av.setVisible(true);
                        break;
                    case "amicAfegit":
                        try {
                            amics = (LinkedList<Amic>)missatge.getData();
                            av = new AmicView(amics);
                            avc = new AmicViewController(av);
                            av.amicController(avc);
                            av.setVisible(true);
                        } catch (ClassCastException ignore) {}
                        break;
                    case "amicJaExisteix":
                        try {
                            //mostra error amic ja existeix
                            amics = (LinkedList<Amic>)missatge.getData();
                            av = new AmicView(amics);
                            avc = new AmicViewController(av);
                            av.amicController(avc);
                            av.setVisible(true);
                        } catch (ClassCastException ignore) {}
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