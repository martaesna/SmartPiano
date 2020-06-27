

import Model.Json.*;
import View.*;
import Controller.MainViewControllerCl;
import Controller.MenuViewController;
import Model.Network.ServerComunication;
import View.RegisterView;
import Controller.*;


import javax.swing.*;

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
        v.setVisible(true);
        LoginView lv = new LoginView();
        RegisterView rv = new RegisterView();
        MenuView mv = new MenuView();
        PianoView pianoView = new PianoView();


        ServerComunication sc = new ServerComunication(mv, lv, rv,v);

        Controller c = new Controller(v,sc);
        System.out.println("estem abans de la vista");
        MainViewControllerCl controllerMain = new MainViewControllerCl(v, lv, rv, sc);
        MenuViewController controllerMenu = new MenuViewController(mv);
        ControllerTeclado controllerTeclado = new ControllerTeclado(pianoView);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                v.registerController(controllerMain);
                lv.registerControllerL(controllerMain);
                rv.registerControllerR(controllerMain);

                mv.registerControllerM(controllerMenu);
                pianoView.registerController(controllerTeclado, controllerTeclado, controllerTeclado);
                v.setVisible(true);
            }
        });
    }
}