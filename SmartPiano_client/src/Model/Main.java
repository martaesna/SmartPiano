package Model;

import Controller.Controller;
import Model.Json.ReadJson;
import Model.Json.Data;
import View.LoginView;
import View.MainView;
import Controller.MainViewControllerCl;
import View.RegisterView;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    private static ReadJson rj;
    private static Data data;


    public static void main(String[] args) {


        //Reading the JSON file
        ReadJson Rj = new ReadJson();
        Rj.llegeixJSON();
        System.out.println(Rj.getDades().getListeningPort());

        MainView v = new MainView();
        LoginView lv = new LoginView();
        RegisterView rv = new RegisterView ();

        MainViewControllerCl controller = new MainViewControllerCl(v, lv, rv);


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Controller c = new Controller(v);
                v.registerController(controller);
                lv.registerControllerL(controller);
                rv.registerControllerR(controller);

                v.setVisible(true);
            }
        });
    }
}