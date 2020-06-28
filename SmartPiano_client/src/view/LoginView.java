package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    public static final String CANCEL = "Cancel";
    public static final String LOGIN = "Log in2";
    private JLabel jllogin;
    private JLabel jlpasword;
    private JTextField jllogin2;
    private JTextField jlpasword2;
    private JButton jbcancel;
    private JButton jblogin;
    private JPanel jpInferior;
    private JPanel jpsuperior;


    /**
     * Constructor de la vista on afegim tots els elements necessaris
     */
    public LoginView() {
        setVisible(false);
        setTitle("Login"); // titol
        setSize(720, 480); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x


        jpsuperior = new JPanel(); // creamos un jpanel
        jpsuperior.setLayout(new GridLayout(2, 2)); // con el grid de 2 2

        //Etiqueta per el username
        jllogin = new JLabel("Username"); // label de username
        //setVisible(true); //fem visible
        jpsuperior.add(jllogin); // la afegim al jpanel

        //caixa de text per el username
        jllogin2 = new JTextField(); // creem una caixa de text
        //setVisible(true); // fem visible
        jpsuperior.add(jllogin2); // afegim al jpanel

        //Etiqueta per la Password
        jlpasword = new JLabel("Password"); // etiqueta pasword
        //setVisible(true);
        jpsuperior.add(jlpasword);


        //caixa de text per el username
        jlpasword2 = new JPasswordField();
        //setVisible(true);
        jpsuperior.add(jlpasword2);


        //Creem un panell
        jpInferior = new JPanel();

        //Creem l esquema de diseny
        jpInferior.setLayout(new GridLayout(1, 2));

        jbcancel= new JButton("Cancel"); // fiquem un boto
        jbcancel.setActionCommand(CANCEL); // creamos el comando de accion.
        jbcancel.setPreferredSize(new Dimension(275, 30)); //amb unes dimensions
        //setVisible(true);
        jpInferior.add(jbcancel);


        jblogin = new JButton("Log in");
        jblogin.setActionCommand(LOGIN);
        jblogin.setPreferredSize(new Dimension(275, 30)); //amb unes dimensions
        //setVisible(true);
        jpInferior.add(jblogin);

        getContentPane().add(jpsuperior, BorderLayout.NORTH); //coloquem els panels on necesitem
        getContentPane().add(jpInferior, BorderLayout.SOUTH);

    }
    /**
     * Assignem el actionlistener que rebrà els accions quan toquem els botons cancel o login
     * @param actionListener
     */
    public void registerControllerL(ActionListener actionListener)
    {
        jbcancel.addActionListener(actionListener);
        jblogin.addActionListener(actionListener);
    }

    public String Getusername() {
        return jllogin2.getText();
    }

    public String Getpasword() {
        return jlpasword2.getText();
    }
}