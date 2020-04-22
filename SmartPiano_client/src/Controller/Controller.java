package Controller;

import javax.swing.JPanel;

import Network.ServerComunication;
import View.MainView;


public class Controller {

    //relacio vista
    private MainView view;

    //realcio server
    private ServerComunication sComunication;

    public Controller(MainView vista,ServerComunication sComunication) {

        this.view = vista;
        this.sComunication = sComunication;
        // iniciem la recepcio dactualitzacions de lestat
        // de la graella
        sComunication.startServerComunication();
    }
}
