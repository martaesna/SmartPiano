package controller;

import model.Missatge;
import model.network.ServerComunication;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.network.ServerComunication.enviaMissatge;

public class MenuViewController implements ActionListener{

    private ServerComunication sc;
    private MenuView mv;
    private LoginView lv = new LoginView();
    private RegisterView rv = new RegisterView();
    private MainView mnv = new MainView();
    private PianoView pv = new PianoView();
    private ControllerTeclado controllerTeclado = new ControllerTeclado(pv, sc);


    public MenuViewController (MenuView mv, ServerComunication sc) {
        this.mv = mv;
        this.sc = sc;
        sc.registerControllerTeclado(controllerTeclado);
    }


    /////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(mv.ACTION)) {
            System.out.println("Piano lliure");
            mv.setVisible(false);
            pv.setVisible(true);
            pv.registerController(controllerTeclado, controllerTeclado, controllerTeclado);

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
            MainView mainView = new MainView();
            MainViewControllerCl mvc = new MainViewControllerCl(mainView, lv, rv, sc);
            mainView.registerController(mvc);
            lv.registerControllerL(mvc);
            rv.registerControllerR(mvc);
            mainView.setVisible(true);
        }
        if (e.getActionCommand().equals(mv.ACTION6)) {
            System.out.println("Borra compte");
            Missatge missatge = new Missatge("borraCompte", null);
            enviaMissatge(missatge);
            mv.setVisible(false);
            MainView mainView = new MainView();
            MainViewControllerCl mvc = new MainViewControllerCl(mainView, lv, rv, sc);
            mainView.registerController(mvc);
            lv.registerControllerL(mvc);
            rv.registerControllerR(mvc);
            mainView.setVisible(true);
        }
    }
}
