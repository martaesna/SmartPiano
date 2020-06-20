package Controller;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private MainView view;
    private MainView v;
    private CançonsView gc;
    private EvolucioView me;
    private Top5View mt;

    public MainViewController(MainView v, CançonsView gc, EvolucioView me, Top5View mt) {
        this.v = v;
        this.gc = gc;
        this.me = me;
        this.mt = mt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(v.ACTION)) { //cuando apretamos el boton
            System.out.println("has apretat gestiona cançons");
            gc.setVisible(true);
            v.setVisible(false);
        }
        if (e.getActionCommand().equals(v.ACTION2)) { //cuando apretamos el boton
            System.out.println("has apretat mostra top 5");
            mt.setVisible(true);
            v.setVisible(false);
        }
        if (e.getActionCommand().equals(v.ACTION3)) { //cuando apretamos el boton
            System.out.println("has apretat mostra evolucio");
            me.setVisible(true);
            v.setVisible(false);
        }
        if (e.getActionCommand().equals(v.ACTION4)) {
            System.out.println("has apretat tornar");
            gc.setVisible(false);
            mt.setVisible(false);
            me.setVisible(false);
            v.setVisible(true);
        }
    }
}

