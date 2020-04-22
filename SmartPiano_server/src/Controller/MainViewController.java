package Controller;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController Extends ActionListener {
    private MainView view;


    private MainView v;

    public MainViewController(MainView v) {
        this.v = v;

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(MainView.ACTION)) { //cuando apretamos el boton

            System.out.println("has apretat login");

        } else {
            ErrorView er = new ErrorView(); //salta la finestra de error
            er.setVisible(true);
        }


        if (e.getActionCommand().equals(MainView.ACTION2)) { //cuando apretamos el boton

            System.out.println("has apretat Registre");

        }else{
            ErrorView er = new ErrorView(); //salta la finestra de error
            er.setVisible(true);


        }
    }
}

/* S ha de crear un alte view per el missatge de error amb aixo.
package View;

import javax.swing.*;


public class ErrorView extends JOptionPane {


    public ErrorView(){

        JOptionPane.showMessageDialog(null,"The credentials are not vaild","Warning", WARNING_MESSAGE);

    }



}
 */
