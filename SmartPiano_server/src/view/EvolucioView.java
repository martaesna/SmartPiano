package view;

import controller.EvolucioViewController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class EvolucioView extends JFrame {
    private JPanel jpVista;
    private static final String ACTION = "Tornar";
    private javax.swing.JButton tornar;
    private JLabel background;
    private JTable taula;
    private String[] titols;

    public EvolucioView(ArrayList<String> evolucio){
        setTitle("Selecciona una opció"); // titol
        setSize(515, 550); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Model/images/disco.jpg"));
            Image scaled = image.getScaledInstance(515, 550, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0,515,550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Creem un panell
        jpVista = new JPanel();
        jpVista.setLayout(null);
        jpVista.setOpaque(false);
        jpVista.setBounds(0,0,500,500);

        tornar = new javax.swing.JButton("tornar");
        tornar.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        tornar.setActionCommand(ACTION);
        tornar.setBounds(5,5,120,30);
        jpVista.add(tornar);

        setContentPane(background);
        getContentPane().add(jpVista);

        taula = new JTable();
        titols = new String[]{"Reproduccions totals", "Minuts totals"};
        taula =  crearTaula(taula, titols, evolucio);
        taula.setBounds(50,50,415,40);
        JScrollPane js = new JScrollPane(taula);
        js.setBounds(50,50,415,40);
        js.setVisible(true);
        getContentPane().add(js);
    }

    public static JTable crearTaula(JTable taula, String[] header, ArrayList<String> rows) {
        DefaultTableModel tablemodel = (DefaultTableModel) taula.getModel();
        tablemodel.setRowCount(0);
        for (String col : header) {
            tablemodel.addColumn(col);
        }
        tablemodel.addRow(new String[]{rows.get(0), rows.get(1)});

        taula.setModel(tablemodel);
        return taula;
    }


    public void registerController(EvolucioViewController c) {
        tornar.addActionListener(c);
    }
}
