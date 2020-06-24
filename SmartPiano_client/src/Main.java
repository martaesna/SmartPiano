

import Model.Json.*;
import View.*;
import Controller.MainViewControllerCl;
import Controller.MenuViewController;
import Model.Network.ServerComunication;
import View.RegisterView;
import Controller.*;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;



import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    private static ReadJson rj;
    private static Data data;
    private Controller c;
    private ServerComunication sc;


    public static void main(String[] args) {
        //Reading the JSON file
        ReadJson Rj = new ReadJson();
        Rj.llegeixJSON();

        MainView v = new MainView();
        LoginView lv = new LoginView();
        RegisterView rv = new RegisterView();
        MenuView mv = new MenuView();
        AmicView amicsv = new AmicView();
        ViewTeclado viewTeclado = new ViewTeclado();


        ServerComunication sc = new ServerComunication(mv, lv, rv,v);

        Controller c = new Controller(v,sc);
        System.out.println("estem abans de la vista");
        MainViewControllerCl controllerMain = new MainViewControllerCl(v, lv, rv, sc);
        MenuViewController controllerMenu = new MenuViewController(mv,amicsv,v);
        ControllerTeclado controllerTeclado = new ControllerTeclado(viewTeclado);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                v.registerController(controllerMain);
                lv.registerControllerL(controllerMain);
                rv.registerControllerR(controllerMain);

                mv.registerControllerM(controllerMenu);
                viewTeclado.registerController(controllerTeclado, controllerTeclado);
                v.setVisible(true);
            }
        });
    }
}