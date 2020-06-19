import Model.Network.Servidor;
import Model.DDBB.SQLOperations;
import Controller.*;
import View.CançonsView;
import View.EvolucioView;
import View.MainView;
import View.Top5View;
import com.mysql.fabric.Server;

import javax.swing.*;

public class Main {

    private Server server;

    public static void main(String[] args) {
        MainView v = new MainView();
        CançonsView gc = new CançonsView();
        EvolucioView me = new EvolucioView();
        Top5View mt = new Top5View();

        MainViewController controller = new MainViewController(v, gc, me, mt);
        SQLOperations sqlOperations = new SQLOperations();

        Servidor server = new Servidor();
        server.startServer();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                v.mainController(controller);

            }
        });
    }
}