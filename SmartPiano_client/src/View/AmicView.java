package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AmicView extends JFrame {

    public static final String ACTION = "Torna";
    public static final String ACTION2 = "Afegueix";
    public static final String ACTION3 = "Buscar";
    public static final String ACTION4 = "Chat";


    private JPanel jpsuperior;
    private JPanel jpinferior;
    private JButton jbexit;
    private JButton jbadd;
    private JButton jbfind;
    private JLabel jlamic1;
    private JLabel jlamic2;
    private JLabel jlamic3;
    private JLabel jlamic4;
    private JLabel jlamic5;
    private JButton jbamic1;
    private JButton jbamic2;
    private JButton jbamic3;
    private JButton jbamic4;
    private JButton jbamic5;






    public AmicView() {

        setVisible(true);
        setTitle("Amics"); // titol
        setSize(500, 800); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);


        //Creem el panell pel boto de back , afegir amics i buscarlos.
        jpsuperior = new JPanel();

        //Creem l esquema
        jpsuperior.setLayout(new GridLayout(1,3));
        jpsuperior.setBounds(10,20,500,30);



        //Creem els botons
        jbexit = new JButton("Tornar"); // fiquem un boto
        jbexit.setActionCommand(ACTION); // creamos el comando de accion.
        jpsuperior.add(jbexit);
        jpsuperior.setLayout(null); //per poder editar la posicio dels botons
        jbexit.setBounds(0,0,100,30); //fiquem les cordenades

        jbadd = new JButton("Afegir"); // fiquem un boto
        jbadd.setActionCommand(ACTION2); // creamos el comando de accion.
        jbadd.setBounds(275,0,100,30);
        jpsuperior.add(jbadd);

        jbfind = new JButton("Buscar"); // fiquem un boto
        jbfind.setActionCommand(ACTION3); // creamos el comando de accion.
        jbfind.setBounds(380,0,100,30);
        jpsuperior.add(jbfind);


        //Creem en un altre panell
        jpinferior = new JPanel();
        jpinferior.setLayout(new GridLayout(5,2));
        jpinferior.setBounds(10,50,500,750);

        //Creem les etiquetes per els amics
        jlamic1 = new JLabel("Amic1");
        jlamic2 = new JLabel("Amic2");
        jlamic3 = new JLabel("Amic3");
        jlamic4 = new JLabel("Amic4");
        jlamic5 = new JLabel("Amic5");
        jpinferior.add(jlamic1);
        jpinferior.add(jlamic2);
        jpinferior.add(jlamic3);
        jpinferior.add(jlamic4);
        jpinferior.add(jlamic5);


        getContentPane().add(jpsuperior);
        getContentPane().add(jpinferior);
    }



}
