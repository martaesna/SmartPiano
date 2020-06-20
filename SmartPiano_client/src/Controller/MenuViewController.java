package Controller;

import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuViewController implements ActionListener{

    private MenuView mv;
    private AmicView amicsv;


    public MenuViewController (MenuView mv,AmicView amicsv) {
        this.mv = mv;
        this.amicsv = amicsv;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(mv.ACTION)) {
            System.out.println("Piano lliure");
            mv.setVisible(false);
            //pianov.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION2)) {
            System.out.println("Cançons");
            mv.setVisible(false);
            //canconcsv.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION4)) {
            System.out.println("Amics");
            mv.setVisible(false);
            amicsv.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION5)) {
            System.out.println("Tanca sessió");
            mv.setVisible(false);
            //sessiov.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION6)) {
            System.out.println("Borra conta");
            mv.setVisible(false);
            //contav.setVisible(true);
        }
    }
}