package Controller;

import Model.DB.ConectorDB;
        import View.MainView;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.ResultSet;
        import java.sql.SQLException;

public class Controller {
    private boolean connected;
    private ConectorDB conn;

    public Controller(MainView mainView) {
        this.connected = false;
    }
}

