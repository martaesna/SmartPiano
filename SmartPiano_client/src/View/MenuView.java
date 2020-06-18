package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {

    public static final String ACTION = "Piano lliure";
    public static final String ACTION2 = "Cançons";
    public static final String ACTION3 = "Ajustos";
    public static final String ACTION4 = "Amics";
    public static final String ACTION5 = "Tanca sessio";
    public static final String ACTION6 = "Borra conta";

    private JPanel jpVista;
    private javax.swing.JButton jbpianolliure;
    private javax.swing.JButton jbcancons;
    private javax.swing.JButton jbajustos;
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
        jpVista.setLayout(new GridLayout(7, 1));
        jpVista.setBounds(524,35,450,520);

        jbpianolliure = new javax.swing.JButton("Piano lliure"); // fiquem un boto
        jbpianolliure.setActionCommand(ACTION); // creamos el comando de accion.
        jpVista.add(jbpianolliure);

        jbcancons = new javax.swing.JButton("Cançons");
        jbcancons.setActionCommand(ACTION2);
        jpVista.add(jbcancons);

        jbajustos = new javax.swing.JButton("Ajustos");
        jbajustos.setActionCommand(ACTION3);
        jpVista.add(jbajustos);

        jbamics = new javax.swing.JButton("Amics");
        jbamics.setActionCommand(ACTION4);
        jpVista.add(jbamics);

        jbtancasessio = new javax.swing.JButton("Tanca sessió");
        jbtancasessio.setActionCommand(ACTION5);
        jpVista.add(jbtancasessio);

        jbtancasessio = new javax.swing.JButton("Tanca sessió");
        jbtancasessio.setActionCommand(ACTION5);
        jpVista.add(jbtancasessio);

        jbborraconta = new javax.swing.JButton("Borra conta");
        jbborraconta.setActionCommand(ACTION6);
        jpVista.add(jbborraconta);

        getContentPane().add(jpVista);
    }

    public void registerControllerM(ActionListener actionListener) {
        jbpianolliure.addActionListener(actionListener);
        jbcancons.addActionListener(actionListener);
        jbajustos.addActionListener(actionListener);
        jbamics.addActionListener(actionListener);
        jbtancasessio.addActionListener(actionListener);
        jbborraconta.addActionListener(actionListener);
    }

}