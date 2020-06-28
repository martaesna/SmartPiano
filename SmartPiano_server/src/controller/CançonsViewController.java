package controller;

import model.ddbb.SQLoperations;
import view.CançonsView;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CançonsViewController implements ActionListener {
    private CançonsView v;

    public CançonsViewController(CançonsView v) {
        this.v = v;
    }
    /**
     * Registra les accions que succeeixen dins de AmicViewController
     * @param e Event que succeeix al interactuar amb algun element
     */

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
                SQLoperations.eliminaCanço(canço.get(0), canço.get(1));
                v.refresh();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("no s'ha seleccionat cap canço");
            }
        }
    }
}
