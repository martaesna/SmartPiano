package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    public static final String CANCEL2 = "Cancel2";
    public static final String REG = "Reg2";
    private JLabel jllogin;
    private JLabel jlmail;
    private JLabel jlpasword;
    private JLabel jlconfirma;
    private JTextField jllogin2;
    private JTextField jlmail2;
    private JTextField jlpasword2;
    private JTextField jlconfirma2;
    private JButton jbcancel;
    private JButton jbregistre;
    private JPanel jpInferior;
    private JPanel jpsuperior;

    /**
     * Constructor de la vista on afegim tots els elements necessaris
     */

    public RegisterView() {
        setVisible(false);
        setTitle("Register"); // titol
        setSize(720, 480); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x

        jpsuperior = new JPanel(); // creamos un jpanel
        jpsuperior.setLayout(new GridLayout(4, 1)); // con el grid de 2 2

        //Etiqueta per el username
        jllogin = new JLabel("Username"); // label de username
        //setVisible(true); //fem visible
        jpsuperior.add(jllogin); // la afegim al jpanel

        //caixa de text per el username
        jllogin2 = new JTextField(); // creem una caixa de text
        //setVisible(true); // fem visible
        jpsuperior.add(jllogin2); // afegim al

        //Etiqueta per el mail
        jlmail = new JLabel("Mail"); // label de username
        //setVisible(true); //fem visible
        jpsuperior.add(jlmail); // la afegim al jpanel

        //caixa de text per el mail
        jlmail2 = new JTextField(); // creem una caixa de text
        //setVisible(true); // fem visible
        jpsuperior.add(jlmail2); // afegim al jpanel

        //Etiqueta per la Password
        jlpasword = new JLabel("Password"); // etiqueta pasword
        //setVisible(true);
        jpsuperior.add(jlpasword);

        //caixa de text per el Password
        jlpasword2 = new JPasswordField();
        //setVisible(true);
        jpsuperior.add(jlpasword2);

        //Etiqueta per la Confirmacio Password
        jlconfirma = new JLabel("Confirma Password"); // etiqueta pasword
        //setVisible(true);
        jpsuperior.add(jlconfirma);


        //caixa de text per la Confirmacio Password
        jlconfirma2 = new JPasswordField();
        //setVisible(true);
        jpsuperior.add(jlconfirma2);


        //Creem un panell
        jpInferior = new JPanel();

        //Creem l esquema de diseny
        jpInferior.setLayout(new GridLayout(1, 2));

        jbcancel = new JButton("Cancel"); // fiquem un boto
        jbcancel.setActionCommand(CANCEL2); // creamos el comando de accion.
        jbcancel.setPreferredSize(new Dimension(275, 30)); //amb unes dimensions
        //setVisible(true);
        jpInferior.add(jbcancel);


        jbregistre = new JButton("Registrat");
        jbregistre.setActionCommand(REG);
        jbregistre.setPreferredSize(new Dimension(275, 30)); //amb unes dimensions
        //setVisible(true);
        jpInferior.add(jbregistre);

        getContentPane().add(jpsuperior, BorderLayout.NORTH); //coloquem els panels on necesitem
        getContentPane().add(jpInferior, BorderLayout.SOUTH);
    }

    /**
     * Assignem el actionlistener que rebrà els accions quan toquem els botons cancel o registre
     * @param actionListener
     */
    public void registerControllerR(ActionListener actionListener) {
        jbcancel.addActionListener(actionListener);// creamos las realciones con el controlador
        jbregistre.addActionListener(actionListener);// creamos las realciones con el controlador
    }

    public String Getusername() {
        return jllogin2.getText();
    }

    public String Getpassword() {
        return jlpasword2.getText();
    }

    public String Getmail(){
        return jlmail2.getText();
    }

    public String Getconfirma () {return jlconfirma2.getText();}

}