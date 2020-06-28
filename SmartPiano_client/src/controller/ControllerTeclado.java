/**
 * Controlador del Teclado, permite formato libre, grabar y reproduccir una canción
 */
package controller;
import model.*;
import model.network.ServerComunication;
import view.*;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import static model.network.ServerComunication.enviaMissatge;


public class ControllerTeclado implements ActionListener, MouseListener, KeyListener {
    private ServerComunication sc;
    private PianoView pv;
    private Modo modo;
    private Song song;
    private Cancion cancion;
    private Synthesizer midiSynth;
    private HashMap<Integer, String> keyBinding;
    private HashMap<Nota, Integer> sustainingKeys;

    public ControllerTeclado(PianoView pv, ServerComunication sc){
        this.pv = pv;
        this.sc = sc;
        this.pv.addKeyListener(this);
        keyBinding = new HashMap<>();
        keyBinding = initKeyBinding();
        sustainingKeys = new HashMap<>();
    }

    private HashMap<Integer, String> initKeyBinding() {
        HashMap<Integer, String> hash = new HashMap<>();
        hash.put(81,"Do0");
        hash.put(87,"Do#0");
        hash.put(69,"Re0");
        hash.put(82,"Re#0");
        hash.put(84,"Mi0");
        hash.put(89,"Fa0");
        hash.put(85,"Fa#0");
        hash.put(73,"Sol0");
        hash.put(79,"Sol#0");
        hash.put(80,"La0");
        hash.put(128,"La#0");
        hash.put(521,"Si0");
        hash.put(65,"Do1");
        hash.put(83,"Do#1");
        hash.put(68,"Re1");
        hash.put(70,"Re#1");
        hash.put(71,"Mi1");
        hash.put(72,"Fa1");
        hash.put(74,"Fa#1");
        hash.put(75,"Sol1");
        hash.put(76,"Sol#1");
        hash.put(45,"La1");
        hash.put(129,"La#1");
        hash.put(10,"Si1");
        return hash;
    }
    /**
     * Registra les accions que succeeixen dins de AmicViewController
     * @param e Event que succeeix al interactuar amb algun element
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "libre":
                modo = Modo.LIBRE;
                pv.unenablePausarGrabacionBoton();
                break;
            case "grabar":
                modo = Modo.GRABAR;
                cancion = new Cancion();
                pv.enablePausarGrabacionBoton();
                break;
            case "reproducir":
                modo = Modo.REPRODUCCIR;
                Missatge missatge = new Missatge("cançonsReproduir", cancion);
                enviaMissatge(missatge);
                break;
            case "salir":
                pv.setVisible(false);
                MenuView mv = new MenuView();
                System.out.println("peta aqui :(");
                LoginView lv = new LoginView();
                RegisterView rv = new RegisterView();
                MainView mnv = new MainView();
                ServerComunication sc = new ServerComunication(mv,lv,rv,mnv);
                MenuViewController mvc = new MenuViewController(mv, sc);
                mv.registerControllerM(mvc);
                mv.setVisible(true);
                break;
        }
    }
    public void cançonsReproduir (LinkedList<Song> canciones) {
        String[] nombreCanciones = new String[canciones.size()];
        for (int i = 0; i < canciones.size(); i++) {
            nombreCanciones[i] = canciones.get(i).getName() + " - " + canciones.get(i).getAutor();
        }
        for (Song c: canciones) {
            System.out.println("valecrack");
            try {
                if (pv.popUpSeleccionarCancion(nombreCanciones).equals(c.getName() + " - " + c.getAutor())){
                    System.out.println("Stitch<3");
                    this.song = c;
                    Missatge missatge = new Missatge("buscaCançoJson", c);
                    enviaMissatge(missatge);
                    break;
                }
            } catch (java.lang.NullPointerException ignore) { }
        }
        modo = Modo.LIBRE;
    }

    public void reproducirCancion(Cancion cançoTrobada)  {
        LinkedList<FiguraMusical> figurasMusical = cançoTrobada.getFigurasMusicales();
        Timer timerPintar = new Timer();
        long tiempoPintar = 0L;

        for (FiguraMusical fm: figurasMusical) {
            System.out.println(fm.toString());

            TimerTask timerTaskpintarRosa = new TimerTask() {
                @Override
                public void run() {
                    pv.cambiarColorTecla(fm.getNota().toString() + String.valueOf(fm.getEscala()), Color.PINK);
                }
            };

            TimerTask timerTaskpintarNegro = new TimerTask() {
                @Override
                public void run() {
                    if (fm.getNota().toString().charAt(fm.getNota().toString().length()-1) == '#') {
                        pv.cambiarColorTecla(fm.getNota().toString() + String.valueOf(fm.getEscala()), Color.black);
                    } else {
                        pv.cambiarColorTecla(fm.getNota().toString() + String.valueOf(fm.getEscala()), Color.white);
                    }
                }
            };

            timerPintar.schedule(timerTaskpintarRosa, tiempoPintar);
            tiempoPintar += fm.getTiempo();
            timerPintar.schedule(timerTaskpintarNegro, tiempoPintar);
        }
    }

    private Nota getNotaFromMouseEvent(String nombre){
        String notaString = nombre.substring(0, nombre.length() - 1);
        Nota notaNota = null;
        for (Nota n : Nota.values()) {
            if (n.toString().equals(notaString)) notaNota = n;
        }
        return notaNota;
    }

    private Nota getNotaFromKeyEvent(int keyCode) {
        String nota = keyBinding.get(keyCode);
        String notaString = nota.substring(0, nota.length() - 1);
        Nota notaNota = null;
        for(Nota n : Nota.values()){
            //System.out.println(n.toString());
            if(n.toString().equals(notaString)) notaNota = n;
        }
        return notaNota;
    }

    private int getEscalaFromMouseEvent(String nombre){
        return Integer.parseInt(nombre.substring(nombre.length() - 1));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent().getName());
        if (e.getComponent().getName().equals("pausargrabacion")) {
            switch (pv.popUpGrabacion()) {
                case 0:
                    cancion.setNombre(pv.popUpNombreCancion());
                    cancion.setPrivada(false);
                    Missatge missatge = new Missatge("afegeixCançoPublica", cancion);
                    enviaMissatge(missatge);
                    System.out.println("Guardar la canción de manera publica.");
                    break;
                case 1:
                    cancion.setNombre(pv.popUpNombreCancion());
                    cancion.setPrivada(true);
                    missatge = new Missatge("afegeixCançoPrivada", cancion);
                    enviaMissatge(missatge);
                    System.out.println("Guardar la canción de manera privada.");
                    break;
                case 2:
                    //No se hace nada, por que no se debe guardar la canción.
                    System.out.println("No guardar la canción.");
                    break;
            }
            //Volver al modo libre
            modo = Modo.LIBRE;
            pv.unenablePausarGrabacionBoton();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!e.getComponent().getName().equals("pausargrabacion")) {
            System.out.println(e.getComponent().getName());
            Nota nota = getNotaFromMouseEvent(e.getComponent().getName());
            int escala = getEscalaFromMouseEvent(e.getComponent().getName());
            if (modo == Modo.GRABAR) {
                cancion.startNewFiguraMusical(System.currentTimeMillis());
            }
            try {
                midiSynth = MidiSystem.getSynthesizer();
                midiSynth.open();
                midiSynth.loadInstrument(midiSynth.getDefaultSoundbank().getInstruments()[0]); // Instrumento 0 = piano
                midiSynth.getChannels()[0].noteOn(nota.getValueNote(escala), 100);
            } catch (MidiUnavailableException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!e.getComponent().getName().equals("pausargrabacion")) {
            Nota nota = getNotaFromMouseEvent(e.getComponent().getName());
            int escala = getEscalaFromMouseEvent(e.getComponent().getName());
            if (modo == Modo.GRABAR) {
                cancion.endNewFiguraMusical(System.currentTimeMillis(), nota, escala);
            }
            midiSynth.getChannels()[0].noteOff(nota.getValueNote(escala));
            midiSynth.close();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("code: " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(sustainingKeys.get(getNotaFromKeyEvent(e.getKeyCode())) == null) {
            System.out.println(e.getKeyCode());
            if (!e.getComponent().getName().equals("pausargrabacion")) {
                System.out.println(e.getComponent().getName());
                Nota nota = getNotaFromKeyEvent(e.getKeyCode());
                sustainingKeys.put(nota, 0);
                int escala = getEscalaFromMouseEvent(e.getComponent().getName());
                if (modo == Modo.GRABAR) {
                    cancion.startNewFiguraMusical(System.currentTimeMillis());
                }
                try {
                    midiSynth = MidiSystem.getSynthesizer();
                    midiSynth.open();
                    midiSynth.loadInstrument(midiSynth.getDefaultSoundbank().getInstruments()[0]); // Instrumento 0 = piano
                    midiSynth.getChannels()[0].noteOn(nota.getValueNote(escala), 100);
                } catch (MidiUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (!e.getComponent().getName().equals("pausargrabacion")) {
            Nota nota = getNotaFromKeyEvent(e.getKeyCode());
            sustainingKeys.remove(nota);
            int escala = getEscalaFromMouseEvent(e.getComponent().getName());
            if (modo == Modo.GRABAR) {
                cancion.endNewFiguraMusical(System.currentTimeMillis(), nota, escala);
            }
            midiSynth.getChannels()[0].noteOff(nota.getValueNote(escala));
            midiSynth.close();
        }
    }
}
