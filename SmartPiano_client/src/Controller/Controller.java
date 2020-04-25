package Controller;

import javax.swing.JPanel;

import Network.ServerComunication;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

    //relacio vista
    private MainView view;

    //realcio server
    private ServerComunication sComunication;

    public Controller(MainView v){
        this.view = v;
    }

    public Controller(MainView vista,ServerComunication sComunication) {

        this.view = vista;
        this.sComunication = sComunication;
        // iniciem la recepcio dactualitzacions de lestat
        // de la graella
        sComunication.startServerComunication();
    }

}
