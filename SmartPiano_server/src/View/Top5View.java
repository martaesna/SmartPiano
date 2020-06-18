package View;

import javax.swing.*;

public class Top5View extends JFrame{
    private JPanel jpVista;
    public Top5View(){
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(515,550); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        //Creem un panell
        jpVista = new JPanel();
        jpVista.setLayout(null);
        jpVista.setBounds(0,0,500,500);
    }
}
