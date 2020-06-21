package Controller;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private MainView v;

    public MainViewController(MainView v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(v.ACTION)) { //cuando apretamos el boton
            CançonsView gc = new CançonsView();
            CançonsViewController c = new CançonsViewController(gc);
            gc.registerController(c);
            System.out.println("has apretat gestiona cançons");
            gc.setVisible(true);
            v.setVisible(false);
        }
        if (e.getActionCommand().equals(v.ACTION2)) { //cuando apretamos el boton
            Top5View mt = new Top5View();
            Top5ViewController c2 = new Top5ViewController(mt);
            mt.registerController(c2);
            System.out.println("has apretat mostra top 5");
            mt.setVisible(true);
            v.setVisible(false);
        }
        if (e.getActionCommand().equals(v.ACTION3)) { //cuando apretamos el boton
            EvolucioView me = new EvolucioView();
            EvolucioViewController c3 = new EvolucioViewController(me);
            me.registerController(c3);
            System.out.println("has apretat mostra evolucio");
            me.setVisible(true);
            v.setVisible(false);
        }
    }
}

