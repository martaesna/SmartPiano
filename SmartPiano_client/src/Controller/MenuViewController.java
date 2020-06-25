package Controller;

import View.*;
import com.sun.tools.javac.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuViewController implements ActionListener{

    private MenuView mv;
    private AmicView amicsv;
    private MainView mnv;
    ViewTeclado pv = new ViewTeclado();
    ControllerTeclado pianoController = new ControllerTeclado(pv);

    public MenuViewController (MenuView mv,AmicView amicsv, MainView mnv) {
        this.mv = mv;
        this.amicsv = amicsv;
        this.mnv = mnv;
    }


    /////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(mv.ACTION)) {
            System.out.println("Piano lliure");
            mv.setVisible(false);
            pv.setVisible(true);
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
            mnv.setVisible(true);

            //sessiov.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION6)) {
            System.out.println("Borra conta");
            mv.setVisible(false);



            //contav.setVisible(true);
        }
        if (e.getActionCommand().equals(amicsv.ACTION)) {
            System.out.println("Tornar");
            amicsv.setVisible(false);
            mv.setVisible(true);
        }
    }
}
