package Model.DB;
import View.MainView;
import Controller.MainViewController;
import javax.swing.*;

import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class Main {
    public static void main (String[] args) {
        Servidor server = new Servidor();
        server.startServer();



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView v = new MainView();
                MainViewController c = new MainViewController(v);
                v.registerController(c);
                v.setVisible(true);
            }

    }
}
