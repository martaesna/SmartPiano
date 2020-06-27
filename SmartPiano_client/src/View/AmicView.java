package View;

import Controller.AmicViewController;
import Model.Amic;
import Model.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class AmicView extends JFrame {

    private final LinkedList<Amic> amics;
    private String[] titols;
    private static JTable taula;
    private JPanel jpVista;
    private JButton eliminar;
    private JButton tornar;
    private JButton afegir;
    private JButton buscar;
    private JButton chat;

    public AmicView(LinkedList<Amic> amics) {
        this.amics = amics;
        setVisible(false);
        setTitle("Amics"); // titol
        setSize(700, 635); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);


        jpVista = new JPanel();
        jpVista.setOpaque(false);
        jpVista.setLayout(null);
        jpVista.setBounds(0,0,700,635);



        //Creem els botons
        tornar = new javax.swing.JButton("Tornar"); // fiquem un boto
        tornar.setActionCommand("Tornar"); // creamos el comando de accion.
        jpVista.add(tornar);
        tornar.setBounds(0,15,100,30); //fiquem les cordenades

        afegir = new JButton("Afegir"); // fiquem un boto
        afegir.setActionCommand("Afegir"); // creamos el comando de accion.
        afegir.setBounds(255,15,100,30);
        jpVista.add(afegir);

        buscar = new javax.swing.JButton("Buscar"); // fiquem un boto
        buscar.setActionCommand("Buscar"); // creamos el comando de accion.
        buscar.setBounds(360,15,100,30);
        jpVista.add(buscar);


        eliminar = new javax.swing.JButton("Eliminar");
        eliminar.setActionCommand("Eliminar");
        eliminar.setBounds(465, 15, 100, 30);
        jpVista.add(eliminar);

        chat =  new javax.swing.JButton("Chat");
        chat.setActionCommand("Chat");
        chat.setBounds(570,15,100,30);
        jpVista.add(chat);

        getContentPane().add(jpVista);

        taula = new JTable();
        titols = new String[]{"Nº", "Nickname"};
        taula =  crearTaula(taula, titols, amics);
        taula.setBounds(50,75,650,500);
        JScrollPane js = new JScrollPane(taula);
        js.setBounds(50,75,600,500);
        js.setVisible(true);

        getContentPane().add(js);
    }
    public static JTable crearTaula(JTable taula, String[] header, LinkedList<Amic> amics) {
        DefaultTableModel tablemodel = (DefaultTableModel) taula.getModel();
        tablemodel.setRowCount(0);
        for (String col : header) {
            tablemodel.addColumn(col);
        }
        int posicio = 1;
        for (Amic row : amics) {
            String[] a = new String[]{String.valueOf(posicio), row.getNickName()};
            tablemodel.addRow(a);
            posicio++;
        }
        taula.setModel(tablemodel);
        return taula;
    }

    public static String amicSeleccionat() {
        String amic = (String) taula.getValueAt(taula.getSelectedRow(), 1);
        return amic;
    }

    public void amicController(AmicViewController c) {
        tornar.addActionListener(c);
        afegir.addActionListener(c);
        buscar.addActionListener(c);
        eliminar.addActionListener(c);
        chat.addActionListener(c);
    }

    public void refresh(String amic) {
        IntStream.range(0, amics.size()).filter(i -> amics.get(i).getNickName().equals(amic)).forEach(amics::remove);
        AmicView av = new AmicView(amics);
        AmicViewController avc = new AmicViewController(av);
        av.amicController(avc);
        this.setVisible(false);
        av.setVisible(true);
    }

}
