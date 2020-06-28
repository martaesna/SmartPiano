package controller;

import model.Missatge;
import view.AmicView;
import view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.network.ServerComunication.enviaMissatge;

public class AmicViewController implements ActionListener {
    private AmicView v;

    public AmicViewController(AmicView v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Eliminar")) {
            System.out.println("has apretat eliminar");
            try {
                String amic = AmicView.amicSeleccionat();
                Missatge missatge = new Missatge("eliminaAmic", amic);
                enviaMissatge(missatge);
                v.refresh(amic);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("no s'ha seleccionat cap amic");
            }
        }
        if (e.getActionCommand().equals("Tornar")) {
            System.out.println("has apretat tornar");
            v.setVisible(false);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MenuView mv = new MenuView();
                    MenuViewController mvc = new MenuViewController(mv);
                    mv.registerControllerM(mvc);
                    mv.setVisible(true);
                }
            });
        }
        if (e.getActionCommand().equals("Chat")) {
            System.out.println("has apretat chat");
        }
        if (e.getActionCommand().equals("Afegir")) {
            String codiAmic = v.getCodiAmic();
            if (codiAmic.length() == 9) {
                Missatge missatge = new Missatge("afegeixAmic", codiAmic);
                enviaMissatge(missatge);
                System.out.println("has apretat Afegir");
                v.setVisible(false);
            } else {
                //missatge error codi erroni
            }
        }
    }
}
