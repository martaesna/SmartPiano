import Model.Json.JsonReader;
import Model.Network.Servidor;
import Model.SQLOperations;
import Controller.*;
import View.MainView;
import com.mysql.fabric.Server;

public class Main {

    private Server server;

    public static void main(String[] args) {
        MainView v = new MainView();

        MainViewController controller = new MainViewController(v);
        SQLOperations sqlOperations = new SQLOperations();

        Servidor server = new Servidor();
        server.startServer();
    }
}