package Controller;

import Model.DDBB.SQLOperations;
import View.CançonsView;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CançonsViewController implements ActionListener {
    private CançonsView v;

    public CançonsViewController(CançonsView v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Tornar")) {
            v.setVisible(false);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainView v = new MainView();
                    MainViewController controller = new MainViewController(v);
                    v.mainController(controller);
                    v.setVisible(true);
                }
            });
        }
        if (e.getActionCommand().equals("Eliminar")) {
            System.out.println("has apretat eliminar");
            try {
                ArrayList<String> canço;
                canço = v.songSeleccionada();
                SQLOperations.eliminaCanço(canço.get(0), canço.get(1));
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("no s'ha seleccionat cap canço");
            }
        }
    }
}
