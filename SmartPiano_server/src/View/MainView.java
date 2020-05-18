package View;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    public static final String ACTION = "Gestiona cançons";
    public static final String ACTION2 = "Mostrar top 5";
    public static final String ACTION3 = "Mostrar evolucio";
    private JPanel jpVista;
    private javax.swing.JButton jbgestionacançons;
    private javax.swing.JButton jbmostratop5;
    private javax.swing.JButton jbmostraevolucio;



    public MainView() {
        setVisible(true);
        setTitle("Selecciona una opció"); // titol
        setSize(500, 535); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        //Creem un panell
        jpVista = new JPanel();

        //Creem l esquema de diseny
        jpVista.setLayout(null);
        jpVista.setBounds(0,0,500,535);

        jbgestionacançons = new javax.swing.JButton("Gestiona cançons"); // fiquem un boto
        jbgestionacançons.setActionCommand(ACTION); // creamos el comando de accion.
        jbgestionacançons.setBounds(125,50,250,100);
        jpVista.add(jbgestionacançons);


        jbmostratop5 = new javax.swing.JButton("Mostra top 5");
        jbmostratop5.setActionCommand(ACTION2);
        jbmostratop5.setBounds(125,200,250,100);
        jpVista.add(jbmostratop5);

        jbmostraevolucio = new javax.swing.JButton("Mostra evolucio");
        jbmostraevolucio.setActionCommand(ACTION3);
        jbmostraevolucio.setBounds(125,350,250,100);
        jpVista.add(jbmostraevolucio);

        getContentPane().add(jpVista);
    }
    public void mainController(ActionListener actionListener) {
        jbgestionacançons.addActionListener(actionListener);// creamos las realciones con el controlador
        jbmostratop5.addActionListener(actionListener);// creamos las realciones con el controlador
        jbmostraevolucio.addActionListener(actionListener);// creamos las realciones con el controlador
    }

}