package Controller;
import View.ErrorView;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener   {
    private MainView view;


    private MainView v;

    public MainViewController(MainView v) {
        this.v = v;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(v.ACTION)) {
            System.out.println("vols gestionar cansçons");
        }
        if (e.getActionCommand().equals(v.ACTION2)) {
            System.out.println("Vols mostrar el Top 5");
        }
        if (e.getActionCommand().equals(v.ACTION3)) {
            System.out.println("Vols mostrar evolucio");
        }

    }
}

