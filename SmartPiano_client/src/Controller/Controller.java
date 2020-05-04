package Controller;

import Model.Network.ServerComunication;
import Model.User;
import View.*;


public class Controller {

    //relacio vista
    private MainView view;
    private User usuari;
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
