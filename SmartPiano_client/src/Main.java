
import Controller.Controller;
import View.MainView;
import Controller.MainViewControllerCl;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        MainView v = new MainView();
        MainViewControllerCl c = new MainViewControllerCl(v);





        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Controller c = new Controller(v);
                v.registerController2(c);
                v.setVisible(true);
            }
        });
    }

}