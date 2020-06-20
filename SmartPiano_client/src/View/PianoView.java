package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PianoView extends JFrame {
    Container jpMain;
    JLayeredPane jlpTeclat;
    JButton[] jbTecla = new JButton[14]; //Posem les 14 tecles en un array
    JButton[] jbTeclaN = new JButton[10]; //Posem tambe les tecles negres
    JButton jbChange;

    private String[] notes = {"Do","Re","Mi","Fa","Sol","La","Si"};

    public PianoView() {
        setTitle("Piano");
        setSize(850, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jpMain = this.getContentPane();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));
        jpMain.add(Box.createRigidArea(new Dimension(0,20)));

        jbChange = new JButton("Cambiar teclas");
        jbChange.setBounds(0, 0, 200, 30);
        jbChange.setActionCommand("ChangeShortcut");
        jpMain.add(jbChange);

        jlpTeclat = new JLayeredPane(); //JPanel amb profundidtat per sobreposar les tecles negres sobre les blanques

        int x = 20; //Coloquem els botons
        int k = 0;
        for(int i = 0; i < 2; i++){
            for(int j=0; j < notes.length; j++){
                jbTecla[k] = new JButton(notes[j]); //Li donem nom a la tecla
                jbTecla[k].setName(notes[j] + i);   //li asignem  una "id" a cada tecla per poder escoltar els clicks
                jbTecla[k].setActionCommand(notes[j] + i);
                jbTecla[k].setBounds(x,0,56,260);
                jbTecla[k].setBackground(Color.WHITE);
                jlpTeclat.add(jbTecla[k], 1); //el 1 es la profunditat, (a mes numero, mes asobre esta)
                jlpTeclat.add(Box.createRigidArea(new Dimension(1, 0))); //espai de separació entre teclas
                x += 57;
                k++; //recorre array de butons q seran 14
            }
        }
        x = 55;
        k = 0;
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 7; j++) {
                if (j != 2 && j !=6) {
                    jbTeclaN[k] = new JButton(notes[j]+"#");
                    jbTeclaN[k].setName(notes[j]+"#"+i);
                    jbTeclaN[k].setActionCommand(notes[j]+"#"+i);
                    jbTeclaN[k].setBounds(x, 0, 42, 150);
                    jbTeclaN[k].setBackground(Color.BLACK);
                    jbTeclaN[k].setForeground(Color.WHITE);
                    jlpTeclat.add(jbTeclaN[k], 2);
                    k++;
                }
                x += 57;
            }
        }

        jpMain.add(jlpTeclat);
        jpMain.add(Box.createRigidArea(new Dimension(0,20)));

        //------METODE 1-------
        /*Action rightAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                jbTecla[0].doClick();
            }
        };
        InputMap inputMap = jlpTeclat.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = jlpTeclat.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        actionMap.put("rightAction", rightAction);*/


    }

    public void registerController(ActionListener actionListener) {
        for(int i = 0; i < jbTecla.length; i++) {
            jbTecla[i].addActionListener(actionListener);
        }
        for (int i = 0; i < jbTeclaN.length; i++) {
            jbTeclaN[i].addActionListener(actionListener);
        }
        jbChange.addActionListener(actionListener);
    }

    public void registerKeyboard (KeyListener keyListener) {
        for(int i = 0; i < jbTecla.length; i++) {
            jbTecla[i].addKeyListener(keyListener);
        }
        for (int i = 0; i < jbTeclaN.length; i++) {
            jbTeclaN[i].addKeyListener(keyListener);
        }
    }
}
