package model.network;

import controller.AmicViewController;
import controller.Controller;
import controller.ControllerTeclado;
import model.*;
import view.*;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import model.json.*;

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
    private ControllerTeclado ct;
    private String codiAmic;

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
    /**
     * iniciem la comunicacio amb el servidor
     *
     */
    public void startServerComunication() {

        isOn = true;
        this.start();
    }
    /**
     * aturem la comunicacio amb el servidor
     *
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Enviem missatge
     * @param missatge Missatge a enviar
     */
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
    /**
     * executem el sevidor
     *
     */
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
                        this.codiAmic = (String) missatge.getData();
                        break;
                    case "delete":
                        mnv.setVisible(true);
                    case "amicsTrobats":
                        LinkedList<Amic> amics = new LinkedList<>();
                        amics = (LinkedList<Amic>)missatge.getData();
                        AmicView av = new AmicView(amics, this);
                        AmicViewController avc = new AmicViewController(av, this);
                        av.amicController(avc);
                        av.setVisible(true);
                        break;
                    case "amicAfegit":
                        try {
                            JOptionPane.showMessageDialog(null, "Ja sou amics!!", "Amistat Correcte", JOptionPane.INFORMATION_MESSAGE);
                            amics = (LinkedList<Amic>)missatge.getData();
                            av = new AmicView(amics, this);
                            avc = new AmicViewController(av, this);
                            av.amicController(avc);
                            av.setVisible(true);
                        } catch (ClassCastException ignore) {}
                        break;
                    case "amicJaExisteix":
                        try {
                            JOptionPane.showMessageDialog(null, "Aquest amic ja el tens afegit", "Amic Existent", JOptionPane.ERROR_MESSAGE);
                            amics = (LinkedList<Amic>)missatge.getData();
                            av = new AmicView(amics,this);
                            avc = new AmicViewController(av, this);
                            av.amicController(avc);
                            av.setVisible(true);
                        } catch (ClassCastException ignore) {}
                        break;
                    case "cançonsPerReproduir":
                        LinkedList<Song> cançons = new LinkedList<>();
                        cançons = (LinkedList<Song>) missatge.getData();
                        ct.cançonsReproduir(cançons);
                        break;
                    case "cançoPerReproduirTrobada":
                        Cancion cançoTrobada;
                        cançoTrobada = (Cancion) missatge.getData();
                        //ct.reproducirCancion(cançoTrobada);
                        break;
                    case "usuariBorrat":
                        JOptionPane.showMessageDialog(null, "Has eliminat el teu compte amb èxit.", "Compte borrat", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        stopServerComunication();
    }

    public void registerControllerTeclado (ControllerTeclado ct) {
        this.ct = ct;
    }

    public String getCodiAmic() {
        return codiAmic;
    }

    public void setCodiAmic(String codiAmic) {
        this.codiAmic = codiAmic;
    }
}