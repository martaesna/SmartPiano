package View;

import javax.swing.*;
public class ErrorView extends JOptionPane {

    //passar missatge d'error i fer un switch segons l'error per mostrar una cosa o altra
    public ErrorView(){
        JOptionPane.showMessageDialog(null,"El usuari o la contrasenya no son correctes","Warning", WARNING_MESSAGE);
    }

}