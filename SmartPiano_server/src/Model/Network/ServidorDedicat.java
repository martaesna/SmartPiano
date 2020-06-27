package Model.Network;

import Controller.MainViewController;
import Model.Amic;
import Model.DDBB.SQLOperations;
import Model.Missatge;
import Model.Song;
import Model.User;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import static Model.DDBB.SQLOperations.*;


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
    private String nomUsuari;
    private User usuari;
    private Song song;


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
            //while is running(bucle infinit)
            while (isRunning) {
                System.out.println("estem al run");
                //dos = new DataOutputStream(sClient.getOutputStream());
                try {
                    Object object = ois.readObject();
                    Missatge missatge;
                    missatge = (Missatge) object;
                    String accio = missatge.getAccio();
                    System.out.println(accio);
                    String accioResposta;
                    Missatge missatgeResposta;

                    switch (accio) {
                        case "registre":
                            usuari = (User)missatge.getData(); // rep la informacio del client
                            if (existeixUsuari(usuari.getName())) { //mirem si existeix el nom
                                accioResposta = "errorRegistre";
                                System.out.println(accioResposta); //enviem la resposta

                            } else {
                                registreUsuari(usuari.getName(), usuari.getMail(), usuari.getPassword(), usuari.getCodi()); //registrem el usuari
                                accioResposta = "registreCorrecte";
                                System.out.println(accioResposta); //resposta de correcte

                            }
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object) missatgeResposta);
                            break;
                        case "login":
                            //retornar info al client
                            usuari = (User)missatge.getData();
                            if (!loginUsuariCorrecte(usuari.getName(), usuari.getPassword())) {
                                accioResposta = "errorLogin";
                                System.out.println(accioResposta);

                            } else {
                                accioResposta = "loginCorrecte";
                                if (usuari.getName().contains("@")) {
                                    nomUsuari = SQLOperations.trobaNickname(usuari.getName());
                                } else {
                                    nomUsuari = usuari.getName();
                                }
                                System.out.println(accioResposta);

                            }
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;

                        case "delete":
                            //Borrar la conta de la base de dades
                            usuari = (User)missatge.getData();
                            borraUsuari(nomUsuari);
                            //BorrarUsuari(usuari.getName());
                            accioResposta = "usuariBorrat";
                            System.out.println(accioResposta);
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "song":
                            song = (Song)missatge.getData();
                            SQLOperations.guardaCanço(song);
                            break;
                        case "amicsUsuari":
                            LinkedList<Amic> amics = new LinkedList<>();
                            amics = SQLOperations.amicsUsuari(nomUsuari);
                            accioResposta = "amicsTrobats";
                            missatgeResposta = new Missatge(accioResposta, amics);
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "eliminaAmic":
                            String amic = (String)missatge.getData();
                            SQLOperations.eliminaAmic(amic, nomUsuari);
                            accioResposta = "amicEliminat";
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "afegeixAmic":
                            String codiAmic = (String)missatge.getData();
                            String nomAmic = SQLOperations.repNomAmic(codiAmic);
                            if (!SQLOperations.amicExisteix(nomAmic, nomUsuari)) {
                                SQLOperations.afegeixAmic(nomAmic, nomUsuari);
                                accioResposta = "amicAfegit";
                                missatge.setData(SQLOperations.amicsUsuari(nomUsuari));
                            } else {
                                accioResposta = "amicJaExisteix";
                                missatge.setData(SQLOperations.amicsUsuari(nomUsuari));
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
