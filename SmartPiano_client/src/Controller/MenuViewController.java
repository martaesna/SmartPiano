package Controller;

import Model.Missatge;
import Model.Network.ServerComunication;
import View.*;
import com.sun.tools.javac.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Model.Network.ServerComunication.enviaMissatge;

public class MenuViewController implements ActionListener{

    private ServerComunication sc;
    private MenuView mv;
    private MainView mnv = new MainView();
    private PianoView pv = new PianoView();
    private ControllerTeclado controllerTeclado = new ControllerTeclado(pv);


    public MenuViewController (MenuView mv) {
        this.mv = mv;
    }


    /////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(mv.ACTION)) {
            System.out.println("Piano lliure");
            mv.setVisible(false);
            pv.setVisible(true);
            pv.registerController(controllerTeclado, controllerTeclado, controllerTeclado);

        }
        if (e.getActionCommand().equals(mv.ACTION2)) {
            System.out.println("Cançons");
            mv.setVisible(false);
            //canconcsv.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION4)) {
            System.out.println("Amics");
            Missatge missatge = new Missatge("amicsUsuari", null);
            enviaMissatge(missatge);
            mv.setVisible(false);
        }
        if (e.getActionCommand().equals(mv.ACTION5)) {
            System.out.println("Tanca sessió");
            mv.setVisible(false);
            mnv.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION6)) {
            System.out.println("Borra compte");
            Missatge missatge = new Missatge("borra compte", null);
            enviaMissatge(missatge);
            mv.setVisible(false);
            System.exit(0);
        }
     /*   if (e.getActionCommand().equals(amicsv.ACTION)) {
            System.out.println("Tornar");
            amicsv.setVisible(false);
            mv.setVisible(true);
        }*/
    }
}
