package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    public static final String CANCEL = "Cancel";
    public static final String ACTION2 = "Reg";
    private JLabel jllogin;
    private JLabel jlmail;
    private JLabel jlpasword;
    private JLabel jlconfirma;
    private JTextField jllogin2;
    private JTextField jlmail2;
    private JTextField jlpasword2;
    private JTextField jlconfirma2;
    private JButton jblogin;
    private JButton jbregistre;
    private JPanel jpInferior;
    private JPanel jpsuperior;


    public RegisterView() {
        setVisible(false);
        setTitle("Register"); // titol
        setSize(500, 150); // tamaño de la caja
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

        jblogin = new JButton("Cancel"); // fiquem un boto
        jblogin.setActionCommand(CANCEL); // creamos el comando de accion.
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

    public void registerControllerR(ActionListener actionListener) {
        jbregistre.addActionListener(actionListener);// creamos las realciones con el controlador
    }

    public String Getusername() {
        return jllogin2.getText();
    }

    public int Getpasword() throws NumberFormatException {
        return Integer.valueOf(jlpasword2.getText());
    }
}