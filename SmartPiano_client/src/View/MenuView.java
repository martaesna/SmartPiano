package View;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuView extends JFrame {

    public static final String ACTION = "Piano lliure";
    public static final String ACTION2 = "Cançons";
    public static final String ACTION4 = "Amics";
    public static final String ACTION5 = "Tanca sessio";
    public static final String ACTION6 = "Borra conta";

    private JPanel jpVista;
    private JPanel jpUser;
    //Creem l esquema de diseny
    private javax.swing.JButton jbpianolliure;
    private javax.swing.JButton jbcancons;
    private javax.swing.JButton jbamics;
    private javax.swing.JButton jbtancasessio;
    private javax.swing.JButton jbborraconta;
    private JLabel menuTitle;
    private JLabel userName;
    private JLabel userEmail;
    private JLabel espai;
    private JLabel background;

    public MenuView() {
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(1024, 635); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);


        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Images/BackgroundPiano.jpg"));
            Image scaled = image.getScaledInstance(1024, 635, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0,1024,635);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        ImageIcon backgroundImage = new ImageIcon("Images/BackgroundPiano.jpg");
        background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setBounds(0,0,1024,635);
        add(background);*/

        Font fontUser = new Font("Helvetica", Font.ITALIC, 20);
        Font titleM = new Font("Helvetica", Font.BOLD, 38);

        //VISTA INFO USER
        jpUser = new JPanel();
        //jpUser.setOpaque(false);
        jpUser.setLayout(new GridLayout(4,1));
        setLayout(null);
        jpUser.setBounds(40,70,470,480);

        menuTitle = new JLabel("Smart Piano MENU");
        menuTitle.setFont(titleM);
        menuTitle.setForeground(Color.BLUE);
        jpUser.add(menuTitle);

        espai = new JLabel(" ");
        jpUser.add(espai);

        userName = new JLabel("Username: <User_Name>");
        userName.setFont(fontUser);
        jpUser.add(userName);

        userEmail = new JLabel("Email: <User_Email>");
        userEmail.setFont(fontUser);
        jpUser.add(userEmail);


        //VISTA DE LES OPCIONS
        jpVista = new JPanel();
        jpVista.setOpaque(false);
        jpVista.setLayout(new GridLayout(5, 1));
        jpVista.setBounds(525,50,450,520);

        Font font = new Font("Helvetica", Font.PLAIN, 20);
        Border border = new LineBorder(Color.BLACK, 3);

        jbpianolliure = new JButton("Piano Lliure"); // fiquem un boto
        jbpianolliure.setFont(font);
        jpVista.setLayout(null);
        jbpianolliure.setBounds(40, 20, 300, 50);
        jbpianolliure.setBorder(border);
        jbpianolliure.setActionCommand(ACTION); // creamos el comando de accion.
        jpVista.add(jbpianolliure);

        jbcancons = new JButton("Cançons");
        jbcancons.setFont(font);
        jbcancons.setBounds(40, 120, 300, 50);
        jbcancons.setBorder(border);
        jbcancons.setActionCommand(ACTION2);
        jpVista.add(jbcancons);

        jbamics = new JButton("Amics");
        jbamics.setFont(font);
        jbamics.setBounds(40, 220, 300, 50);
        jbamics.setBorder(border);
        jbamics.setActionCommand(ACTION4);
        jpVista.add(jbamics);

        jbtancasessio = new JButton("Tanca sessió");
        jbtancasessio.setFont(font);
        jbtancasessio.setBounds(40, 320, 300, 50);
        jbtancasessio.setBorder(border);
        jbtancasessio.setActionCommand(ACTION5);
        jpVista.add(jbtancasessio);

        jbborraconta = new JButton("Borra conta");
        jbborraconta.setFont(font);
        jbborraconta.setBounds(40, 420, 300, 50);
        jbborraconta.setBorder(border);
        jbborraconta.setActionCommand(ACTION6);
        jpVista.add(jbborraconta);

        setContentPane(background);
        getContentPane().add(jpUser);
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