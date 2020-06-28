package view;

import model.Nota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class PianoView extends JFrame {
    //MENU
    private JMenuBar jMenuBar;
    private JMenu jMenuModo;
    private JMenuItem jMenuItemEstiloLibre;
    private JMenuItem jMenuItemGrabar;
    private JMenuItem jMenuItemTocarCancion;
    private JMenu jMenuOpciones;
    private JMenuItem jMenuItemCambiarBotones;
    private JMenuItem jMenuItemSalir;
    //CENTRO - GENERAL
    private JPanel centro;
    private LinkedList<JButton> teclas;
    private JPanel miscelanea;
    private JButton pausarGrabacion;
    private JButton pausarReproducion;
    /**
     * Constructor de la vista on afegim tots els elements necessaris
     */

    public PianoView() {
        //Configuración básica de la pantalla
        setTitle("Piano");
        setSize(1000, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //MENU - SELECCIONAR MODO DE TOCAR EL TECLADO
        jMenuBar = new JMenuBar();

        jMenuModo = new JMenu("Modo");

        jMenuItemEstiloLibre = new JMenuItem("Estilo Libre");
        jMenuItemEstiloLibre.setActionCommand("libre");
        jMenuModo.add(jMenuItemEstiloLibre);

        jMenuItemGrabar = new JMenuItem("Grabar");
        jMenuItemGrabar.setActionCommand("grabar");
        jMenuModo.add(jMenuItemGrabar);

        jMenuItemTocarCancion = new JMenuItem("Reproducir Canción");
        jMenuItemTocarCancion.setActionCommand("reproducir");
        jMenuModo.add(jMenuItemTocarCancion);

        jMenuBar.add(jMenuModo);


        jMenuOpciones = new JMenu("Opciones");

        jMenuItemCambiarBotones = new JMenuItem("Cambiar Botones");
        jMenuItemCambiarBotones.setActionCommand("cambiarbotones");
        jMenuOpciones.add(jMenuItemCambiarBotones);

        jMenuItemSalir = new JMenuItem("Salir");
        jMenuItemSalir.setActionCommand("salir");
        jMenuOpciones.add(jMenuItemSalir);

        jMenuBar.add(jMenuOpciones);

        setJMenuBar(jMenuBar);

        //CENTRO - Teclado + Botones de pausa
        centro = new JPanel();

        //Botes de pausa
        miscelanea = new JPanel();
        miscelanea.setName("miscelanea");
        miscelanea.setBackground(Color.blue);
        miscelanea.setPreferredSize(new Dimension(1000, 100));

        pausarGrabacion = new JButton("Pausar Grabación");
        pausarGrabacion.setName("pausargrabacion");
        unenablePausarGrabacionBoton();
        miscelanea.add(pausarGrabacion);



        centro.add(miscelanea);

        //Teclado
        JLayeredPane teclado = new JLayeredPane();
        teclado.setPreferredSize(new Dimension(1000, 300));
        teclado.setLayout(new GridLayout(1, 24));
        teclas = new LinkedList<JButton>();

        //Definimos las teclas
        for (int i = 0; i < 2; i++) {
            for (Nota nota : Nota.values()) {
                JButton tecla = new JButton(nota.toString());
                tecla.setName(nota.toString() + i);
                tecla.setActionCommand(nota.toString() + i);
                if (nota.toString().charAt(nota.toString().length() - 1) == '#') {
                    tecla.setBackground(Color.black);
                } else {
                    tecla.setBackground(Color.white);
                }
                teclado.add(tecla);
                teclas.add(tecla);
            }
        }
        centro.add(teclado);

        getContentPane().add(centro, BorderLayout.CENTER);
    }

    public void enablePausarGrabacionBoton() {
        pausarGrabacion.setEnabled(true);
        pausarGrabacion.setVisible(true);
    }

    public void unenablePausarGrabacionBoton() {
        pausarGrabacion.setEnabled(false);
        pausarGrabacion.setVisible(false);
    }

    public int popUpGrabacion () {
        Object[] opciones = {"Si, la quiero pública",
                "Si, la quiero privada",
                "No la quiero guardar"};
        return JOptionPane.showOptionDialog(
                this,
                "¿Quieres guardar la cancion?",
                "Guardado de cancion",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[2]
        );
    }

    public String popUpNombreCancion() {
        return (String)JOptionPane.showInputDialog(
                this,
                "Nombre de la cancion");
    }

    public String popUpSeleccionarCancion(String[] nombreCanciones){
        return (String)JOptionPane.showInputDialog (
                this,
                "Selecciona la canción a tocar",
                "Selección de cancion",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombreCanciones,
                0
        );
    }

    public void cambiarColorTecla (String nombreTecla, Color color) {
        for (JButton tecla: teclas){
            if (tecla.getName().equals(nombreTecla)){
                tecla.setBackground(color);
                break;
            }
        }
    }
    /**
     * Assignem el actionlistener que rebrà els accions quan toquem els botons del piano
     * @param actionListener
     */

    public void registerController (ActionListener actionListener, MouseListener mouseListener, KeyListener keyListener){
        jMenuItemEstiloLibre.addActionListener(actionListener);
        jMenuItemGrabar.addActionListener(actionListener);
        jMenuItemTocarCancion.addActionListener(actionListener);
        jMenuItemCambiarBotones.addActionListener(actionListener);
        jMenuItemSalir.addActionListener(actionListener);

        for (JButton tecla : teclas){
            tecla.addMouseListener(mouseListener);
            tecla.addKeyListener(keyListener);
        }
        pausarGrabacion.addMouseListener(mouseListener);

    }


}
