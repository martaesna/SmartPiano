import Model.DB.Network.Servidor;
import Model.DB.SQLOperations;
import Controller.*;
import View.MainView;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainView v = new MainView();

        MainViewController controller = new MainViewController(v);
        SQLOperations sqlOperations = new SQLOperations();

        Servidor server = new Servidor();
        server.startServer();

    }

}