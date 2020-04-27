package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    public static final String ACTION = "Log in";
    public static final String ACTION2 = "Reg";
    private JButton jblogin;
    private JButton jbregistre;
    private JPanel jpInferior;
    private JPanel jpsuperior;


    public MainView() {
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(300, 110); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x


        jpsuperior = new JPanel(); // creamos un jpanel
        jpsuperior.setLayout(new GridLayout(2, 2)); // con el grid de 2 2

        //Creem un panell
        jpInferior = new JPanel();

        //Creem l esquema de diseny
        jpInferior.setLayout(new GridLayout(1, 2));

        jblogin = new JButton("Log in"); // fiquem un boto
        jblogin.setActionCommand(ACTION); // creamos el comando de accion.
        jblogin.setPreferredSize(new Dimension(275, 30)); //amb unes dimensions
        //setVisible(true);
        jpInferior.add(jblogin);


        jbregistre = new JButton("Registrat");
        jbregistre.setActionCommand(ACTION2);
        jbregistre.setPreferredSize(new Dimension(275, 30)); //amb unes dimensions
        //setVisible(true);
        jpInferior.add(jbregistre);

        getContentPane().add(jpsuperior, BorderLayout.NORTH); //coloquem els panels on necesitem
        getContentPane().add(jpInferior, BorderLayout.SOUTH);

    }

    public void registerController(ActionListener actionListener) {
        jblogin.addActionListener(actionListener);
        jbregistre.addActionListener(actionListener);// creamos las realciones con el controlador
    }
}