package Controller;
import Model.GeneradorCodi;
import Model.Missatge;
import View.*;
import Model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit;

import Model.Network.ServerComunication;

public class MainViewControllerCl implements ActionListener {

    public static final String ERROR_EMAIL = "Format del correu incorrecte";
    public static final String ERROR_PASSWOORD = "Contrasenya incorrecte. Ha d'incloure mín, majús, val. númer i tenir longitud mínima de 8 caràcters";
    public static final String ERROR_CONFIRM = "La constrasenya no coincideix";

    private MainView v;
    private LoginView lv;
    private RegisterView rv;
    private ServerComunication sc;
    private String accio;
    private Boolean validEmail;
    private Boolean validPassword;
    private GeneradorCodi generadorCodi;

    public MainViewControllerCl(MainView v, LoginView lv, RegisterView rv, ServerComunication sc) {
        this.v = v;
        this.lv = lv;
        this.rv = rv;
        this.sc = sc;
        generadorCodi = new GeneradorCodi();
    }

    public static boolean isValidEmail(String email)
    {
        String endPattern = "@gmail.com";
        String endPattern2 = "@students.salle.url.edu";

        if(null == email){
            return false;
        }
        if(!email.endsWith(endPattern) && !email.endsWith(endPattern2)){
            return false;
        } else if (email.endsWith(endPattern)) {
            String[] strArr = email.split(endPattern);
            if(!strArr[0].isEmpty() && strArr[0] != null) {
                return true;
            }
        } else {
            String[] strArr2 = email.split(endPattern2);
            if(!strArr2[0].isEmpty() && strArr2[0] != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        if(password.matches(pattern)) {
            return true;
        }
        return false;
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
            accio = "registre";
            validEmail = true;
            validPassword = true;

            String email = rv.Getmail();
            String password = rv.Getpassword();

            validEmail = isValidEmail(email);
            if (!validEmail) {
                JOptionPane.showMessageDialog(null, ERROR_EMAIL, "Error Email", JOptionPane.ERROR_MESSAGE);
            } else {
                validPassword = isValidPassword(password);
                if(!validPassword) {
                    JOptionPane.showMessageDialog(null, ERROR_PASSWOORD, "Error Password", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (rv.Getpassword().equals(rv.Getconfirma())) {
                        User usuari = new User(rv.Getusername(), rv.Getmail(), rv.Getpassword());
                        usuari.setCodi(generadorCodi.RandomString());
                        Missatge missatge = new Missatge(accio, usuari);
                        sc.enviaMissatge((Missatge)missatge);
                        rv.setVisible(false);
                        lv.setVisible(true);
                        System.out.println("hola que tal");

                        //Guardem user a la BDD

                        /*usuari.setName(rv.Getusername());
                        usuari.setMail(rv.Getmail());
                        usuari.setPassword(rv.Getpassword());
                        */
                    } else {
                        JOptionPane.showMessageDialog(null, ERROR_CONFIRM, "Error Registre", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (e.getActionCommand().equals(lv.LOGIN)) {
            System.out.println("Vols fer log in");

            accio = "login";
            User usuari = new User(lv.Getusername(), "null", lv.Getpasword());
            Missatge missatge = new Missatge(accio, usuari);
            sc.enviaMissatge((Object)missatge);
        }
    }
}

