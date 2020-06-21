package View;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainView extends JFrame {

    public static final String ACTION = "Gestiona cançons";
    public static final String ACTION2 = "Mostrar top 5";
    public static final String ACTION3 = "Mostrar evolucio";
    public static final String ACTION4 = "Tornar";
    private JPanel jpVista;
    private JLabel background;
    private javax.swing.JButton jbgestionacançons;
    private javax.swing.JButton jbmostratop5;
    private javax.swing.JButton jbmostraevolucio;



    public MainView() {
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(515, 550); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);


        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Images/disco.jpg"));
            Image scaled = image.getScaledInstance(515, 550, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0,515,550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Creem un panell
        jpVista = new JPanel();
        jpVista.setOpaque(false);
        jpVista.setLayout(null);
        jpVista.setBounds(0,0,500,500);


        jbgestionacançons = new javax.swing.JButton("gestiona cançons"); // fiquem un boto
        jbgestionacançons.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        jbgestionacançons.setActionCommand(ACTION); // creamos el comando de accion.
        jbgestionacançons.setBounds(125,50,250,100);
        jpVista.add(jbgestionacançons);


        jbmostratop5 = new javax.swing.JButton("mostra top 5");
        jbmostratop5.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        jbmostratop5.setActionCommand(ACTION2);
        jbmostratop5.setBounds(125,200,250,100);
        jpVista.add(jbmostratop5);

        jbmostraevolucio = new javax.swing.JButton("mostra evolucio");
        jbmostraevolucio.setFont(new Font("Bauhaus 93", Font.BOLD, 24));
        jbmostraevolucio.setActionCommand(ACTION3);
        jbmostraevolucio.setBounds(125,350,250,100);
        jpVista.add(jbmostraevolucio);

        setContentPane(background);
        getContentPane().add(jpVista);
    }
    public void mainController(ActionListener actionListener) {
        jbgestionacançons.addActionListener(actionListener);// creamos las realciones con el controlador
        jbmostratop5.addActionListener(actionListener);// creamos las realciones con el controlador
        jbmostraevolucio.addActionListener(actionListener);// creamos las realciones con el controlador
    }

}