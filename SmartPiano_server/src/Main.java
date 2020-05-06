import Model.DB.DDBB.ConnectorBBDD;
import Model.DB.Network.Servidor;
import Model.DB.SQLOperations;
import Controller.*;
import View.MainView;
import Model.DB.DDBB.ParseJSON;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainView v = new MainView();

        MainViewController controller = new MainViewController(v);
        SQLOperations sqlOperations = new SQLOperations();
        ConnectorBBDD conn = new ConnectorBBDD();
        conn = Model.DB.DDBB.ParseJSON.jsonToClass();

        Servidor server = new Servidor();
        server.startServer();

    }

}