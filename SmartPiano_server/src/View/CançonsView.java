package View;

import javax.swing.*;
import java.awt.*;

public class CançonsView extends JFrame{
    private JPanel jpVista;
    public static final String ACTION = "Tornar";
    private javax.swing.JButton tornar;

    public CançonsView(){
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(515, 550); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);
        //Creem un panell
        jpVista = new JPanel();
        jpVista.setLayout(null);
        jpVista.setBounds(0,0,500,500);

        tornar = new javax.swing.JButton("tornar");
        tornar.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        tornar.setActionCommand(ACTION);
        tornar.setBounds(5,5,120,30);
        jpVista.add(tornar);

        getContentPane().add(jpVista);

    }
}
