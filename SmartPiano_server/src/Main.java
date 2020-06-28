import model.network.Servidor;
import model.ddbb.SQLoperations;
import controller.*;
import view.MainView;
import com.mysql.fabric.Server;

import javax.swing.*;

public class Main {

    private Server server;

    public static void main(String[] args) {
        MainView v = new MainView();
        MainViewController controller = new MainViewController(v);
        SQLoperations sqlOperations = new SQLoperations();

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