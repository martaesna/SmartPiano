
import Controller.Controller;
import View.LoginView;
import View.MainView;
import Controller.MainViewControllerCl;
import View.RegisterView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
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