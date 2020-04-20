package Model.DB;

import Model.DB.SQLOperations;
import Controller.Controller;
import View.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainView v = new MainView();


        Controller controller = new Controller(v);
        SQLOperations sqlOperations = new SQLOperations();

        /*Servidor server = new Servidor();
        server.startServer();*/



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //MainView v = new MainView();

                Controller c = new Controller(v);
                v.registerController(c);
                v.setVisible(true);
            }
        });
    }

}