package View;

import Controller.CançonsViewController;
import Model.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CançonsView extends JFrame{
    private JPanel jpVista;
    private static final String ACTION = "Tornar";
    private javax.swing.JButton tornar;
    private javax.swing.JButton eliminar;
    private JLabel background;
    private JTable taula;
    private String[] titols;

    public CançonsView(ArrayList<Song> songs){
        setTitle("Selecciona una opció"); // titol
        setSize(515, 550); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Images/disco.jpg"));
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
        tornar.setActionCommand("Tornar");
        tornar.setBounds(5,5,120,30);
        jpVista.add(tornar);

        eliminar = new javax.swing.JButton("eliminar");
        eliminar.setFont(new Font("Bauhaus 93", Font.BOLD, 20));
        eliminar.setActionCommand("Eliminar");
        eliminar.setBounds(370,475,120,30);
        jpVista.add(eliminar);


        setContentPane(background);
        getContentPane().add(jpVista);


        taula = new JTable();
        titols = new String[]{"Titol", "Autor"};
        taula =  crearTaula(taula, titols, songs);
        taula.setBounds(50,50,415,415);
        JScrollPane js = new JScrollPane(taula);
        js.setBounds(50,50,415,415);
        js.setVisible(true);
        getContentPane().add(js);
    }

    public static JTable crearTaula(JTable taula, String[] header, ArrayList<Song> rows) {
        DefaultTableModel tablemodel = (DefaultTableModel) taula.getModel();
        tablemodel.setRowCount(0);
        for (String col : header) {
            tablemodel.addColumn(col);
        }
        for (Song row : rows) {
            String[] a = new String[]{row.getName(),row.getAutor()};
            tablemodel.addRow(a);
        }
        taula.setModel(tablemodel);
        return taula;
    }

    public void registerController(CançonsViewController c) {
        tornar.addActionListener(c);
        eliminar.addActionListener(c);
    }

    public ArrayList<String> songSeleccionada() {
        ArrayList<String> canço =  new ArrayList<>();
        String nom = (String) taula.getValueAt(taula.getSelectedRow(), 0);
        String autor = (String) taula.getValueAt(taula.getSelectedRow(), 1);
        System.out.println("nom:" + nom + "    autor" + autor);
        canço.add(nom);
        canço.add(autor);
        return canço;
    }
}
