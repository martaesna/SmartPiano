package Controller;
import View.ErrorView;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private MainView view;
    private MainView v;

    public MainViewController(MainView v) {
        this.v = v;
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
    }
}

