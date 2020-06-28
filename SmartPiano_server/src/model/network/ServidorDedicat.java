package model.network;

import controller.MainViewController;
import model.*;
import model.ddbb.SQLoperations;
import model.json.JsonCançons;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

import static model.ddbb.SQLoperations.*;
import static model.json.JsonCançons.jsonSongs;


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
                            missatgeResposta = new Missatge(accioResposta, usuari.getCodi());
                            enviaMissatge((Object) missatgeResposta);
                            break;
                        case "login":
                            usuari = (User)missatge.getData();
                            if (!loginUsuariCorrecte(usuari.getName(), usuari.getPassword())) {
                                accioResposta = "errorLogin";
                                System.out.println(accioResposta);

                            } else {
                                accioResposta = "loginCorrecte";
                                if (usuari.getName().contains("@")) {
                                    nomUsuari = SQLoperations.trobaNickname(usuari.getName());
                                } else {
                                    nomUsuari = usuari.getName();
                                }
                                System.out.println(accioResposta);

                            }
                            missatgeResposta = new Missatge(accioResposta, usuari);
                            enviaMissatge((Object)missatgeResposta);
                            break;

                        case "borraCompte":
                            //Borrar la conta de la base de dades
                            usuari = (User)missatge.getData();
                            borraUsuari(nomUsuari);
                            accioResposta = "usuariBorrat";
                            System.out.println(accioResposta);
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "song":
                            song = (Song)missatge.getData();
                            SQLoperations.guardaCanço(song);
                            break;
                        case "amicsUsuari":
                            LinkedList<Amic> amics = new LinkedList<>();
                            amics = SQLoperations.amicsUsuari(nomUsuari);
                            accioResposta = "amicsTrobats";
                            missatgeResposta = new Missatge(accioResposta, amics);
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "eliminaAmic":
                            String amic = (String)missatge.getData();
                            SQLoperations.eliminaAmic(amic, nomUsuari);
                            accioResposta = "amicEliminat";
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "afegeixAmic":
                            String codiAmic = (String)missatge.getData();
                            String nomAmic = SQLoperations.repNomAmic(codiAmic);
                            if (!SQLoperations.amicExisteix(nomAmic, nomUsuari)) {
                                SQLoperations.afegeixAmic(nomAmic, nomUsuari);
                                accioResposta = "amicAfegit";
                                missatge.setData(SQLoperations.amicsUsuari(nomUsuari));
                            } else {
                                accioResposta = "amicJaExisteix";
                                missatge.setData(SQLoperations.amicsUsuari(nomUsuari));
                            }
                            missatgeResposta = new Missatge(accioResposta, missatge.getData());
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "cançonsReproduir":
                            LinkedList<Song> cançons;
                            cançons = SQLoperations.demanaCançonsReproduir(nomUsuari);
                            missatgeResposta = new Missatge("cançonsPerReproduir", cançons);
                            enviaMissatge((Object)missatgeResposta);
                            break;
                        case "afegeixCançoPublica":
                            Cancion canço = (Cancion) missatge.getData();
                            int id = SQLoperations.afegeixCanço(canço, nomUsuari);
                            jsonSongs(canço, id);
                            break;
                        case "afegeixCançoPrivada":
                            canço = (Cancion) missatge.getData();
                            id = SQLoperations.afegeixCanço(canço, nomUsuari);
                            jsonSongs(canço, id);
                            break;
                        case "buscaCançoJson":
                            Song buscaCanço;
                            buscaCanço = (Song) missatge.getData();
                            System.out.println("hola k tal");
                            Cancion cançoTrobada = JsonCançons.buscaCançoJson(buscaCanço);
                            missatgeResposta = new Missatge("cançoPerReproduirTrobada", cançoTrobada);
                            enviaMissatge((Object)missatgeResposta);
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
