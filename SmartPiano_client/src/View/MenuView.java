package View;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class MenuView extends JFrame {

    public static final String ACTION = "Piano lliure";
    public static final String ACTION2 = "Cançons";
    public static final String ACTION4 = "Amics";
    public static final String ACTION5 = "Tanca sessio";
    public static final String ACTION6 = "Borra conta";

    private JPanel jpVista;
    private javax.swing.JButton jbpianolliure;
    private javax.swing.JButton jbcancons;
    private javax.swing.JButton jbamics;
    private javax.swing.JButton jbtancasessio;
    private javax.swing.JButton jbborraconta;


    public MenuView() {
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(1024, 635); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        //Creem un panell
        jpVista = new JPanel();
        //Creem l esquema de diseny
        jpVista.setLayout(new GridLayout(5, 1));
        jpVista.setBounds(525,35,450,520);

        Font font = new Font("Helvetica", Font.PLAIN, 20);


        jbpianolliure = new JButton("Piano Lliure"); // fiquem un boto
        jbpianolliure.setFont(font);
        jpVista.setLayout(null);
        jbpianolliure.setBounds(25, 20, 400, 80);

        jbpianolliure.setActionCommand(ACTION); // creamos el comando de accion.
        jpVista.add(jbpianolliure);

        jbcancons = new JButton("Cançons");
        jbcancons.setFont(font);
        jbcancons.setBounds(25, 120, 400, 80);
        jbcancons.setActionCommand(ACTION2);
        jpVista.add(jbcancons);

        jbamics = new JButton("Amics");
        jbamics.setFont(font);
        jbamics.setBounds(25, 220, 400, 80);
        jbamics.setActionCommand(ACTION4);
        jpVista.add(jbamics);

        jbtancasessio = new JButton("Tanca sessió");
        jbtancasessio.setFont(font);
        jbtancasessio.setBounds(25, 320, 400, 80);
        jbtancasessio.setActionCommand(ACTION5);
        jpVista.add(jbtancasessio);

        jbborraconta = new JButton("Borra conta");
        jbborraconta.setFont(font);
        jbborraconta.setBounds(25, 420, 400, 80);
        jbborraconta.setActionCommand(ACTION6);
        jpVista.add(jbborraconta);

        getContentPane().add(jpVista);
    }

    public void registerControllerM(ActionListener actionListener) {
        jbpianolliure.addActionListener(actionListener);
        jbcancons.addActionListener(actionListener);
        jbamics.addActionListener(actionListener);
        jbtancasessio.addActionListener(actionListener);
        jbborraconta.addActionListener(actionListener);
    }

}