package Controller;
import View.ErrorView;
import View.MainView;
import View.LoginView;
import View.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewControllerCl implements ActionListener {

    private MainView v;
    private LoginView lv;
    private RegisterView rv;


    public MainViewControllerCl(MainView v, LoginView lv, RegisterView rv) {
        this.v = v;
        this.lv = lv;
        this.rv = rv;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(v.ACTION)) { //cuando apretamos el boton

            System.out.println("has apretat login");
            lv.setVisible(true);
            v.setVisible(false);
        }

        if (e.getActionCommand().equals(v.ACTION2)) { //cuando apretamos el boton

            System.out.println("has apretat Registre");
            rv.setVisible(true);
            v.setVisible(false);
        }
        if (e.getActionCommand().equals(rv.CANCEL)) {
            System.out.println("Has tornat al menú principal");
            rv.setVisible(false);
            v.setVisible(true);
        }
    }
}