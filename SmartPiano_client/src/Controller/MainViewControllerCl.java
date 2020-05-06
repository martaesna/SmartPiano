package Controller;
import View.ErrorView;
import View.MainView;
import View.LoginView;
import View.RegisterView;
import Model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Model.Network.ServerComunication;

public class MainViewControllerCl implements ActionListener {

    private MainView v;
    private LoginView lv;
    private RegisterView rv;
    private ServerComunication sc;

    public MainViewControllerCl(MainView v, LoginView lv, RegisterView rv, ServerComunication sc) {
        this.v = v;
        this.lv = lv;
        this.rv = rv;
        this.sc = sc;
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
        if (e.getActionCommand().equals(rv.CANCEL2)) {
            System.out.println("Has tornat al menú principal");
            rv.setVisible(false);
            v.setVisible(true);
        }
        if (e.getActionCommand().equals(lv.CANCEL)) {
            System.out.println("Has tornat al menú principal");
            lv.setVisible(false);
            v.setVisible(true);
        }
        if (e.getActionCommand().equals(rv.REG)) {
            System.out.println("Vols registrarte");
            if (rv.Getpassword().equals(rv.Getconfirma())) {
                User usuari = new User(rv.Getusername(), rv.Getmail(), rv.Getpassword());

                sc.enviaRegistre(usuari);

                /*usuari.setName(rv.Getusername());
                usuari.setMail(rv.Getmail());
                usuari.setPassword(rv.Getpassword());
                */

            }

            // Recorrem el json comparan
            //if()sdaadasdasd

        }
        if (e.getActionCommand().equals(lv.LOGIN)) {
            System.out.println("Vols fer log in");

        }
    }
}