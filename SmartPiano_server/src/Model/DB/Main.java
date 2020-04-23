package Model.DB;

import Model.DB.SQLOperations;
import Controller.*;
import View.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainView v = new MainView();


        MainViewController controller = new MainViewController(v);
        SQLOperations sqlOperations = new SQLOperations();

        /*Servidor server = new Servidor();
        server.startServer();*/



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {


                Controller c = new Controller(v);
                v.registerMainViewController(controller);
                v.setVisible(true);
            }
        });
    }

}