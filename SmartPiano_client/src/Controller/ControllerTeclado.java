/**
 * Controlador del Teclado, permite formato libre, grabar y reproduccir una canción
 */
package Controller;
import Model.Cancion;
import Model.FiguraMusical;
import Model.Modo;
import Model.Nota;
import View.ViewTeclado;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


public class ControllerTeclado implements ActionListener, MouseListener, KeyListener {
    private ViewTeclado viewTeclado;
    private Modo modo;
    private Cancion cancion;
    private Synthesizer midiSynth;


    public ControllerTeclado(ViewTeclado viewTeclado){
        this.viewTeclado = viewTeclado;
        this.viewTeclado.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "libre":
                modo = Modo.LIBRE;
                viewTeclado.unenablePausarGrabacionBoton();
                break;
            case "grabar":
                modo = Modo.GRABAR;
                cancion = new Cancion();
                viewTeclado.enablePausarGrabacionBoton();
                break;
            case "reproducir":
                modo = Modo.REPRODUCCIR;
                //TODO: llamar servidor para pedir las canciones publicas y las privadas del usuario.
                LinkedList<Cancion> canciones = new LinkedList<>();
                Cancion cancion = new Cancion();
                cancion.setNombre("ASDF - JKLÑ");
                cancion.startNewFiguraMusical(0);
                cancion.endNewFiguraMusical(2000L, Nota.DO, 0);
                cancion.startNewFiguraMusical(0);
                cancion.endNewFiguraMusical(2000L, Nota.DOMOD, 0);
                cancion.startNewFiguraMusical(0);
                cancion.endNewFiguraMusical(2000L, Nota.RE, 0);
                canciones.add(cancion);
                String[] nombreCanciones = new String[canciones.size()];
                for (int i = 0; i < canciones.size(); i++){
                    nombreCanciones[i] = canciones.get(i).getNombre();
                }
                for (Cancion c: canciones) {
                    if (viewTeclado.popUpSeleccionarCancion(nombreCanciones).equals(c.getNombre())){
                        this.cancion = c;
                        reproducirCancion();
                        break;
                    }
                }
                modo = Modo.LIBRE;
                break;
        }
    }

    private void reproducirCancion()  {
        LinkedList<FiguraMusical> figurasMusical = cancion.getFigurasMusicales();
        Timer timerPintar = new Timer();
        long tiempoPintar = 0L;

        for (FiguraMusical fm: figurasMusical) {
            System.out.println(fm.toString());

            TimerTask timerTaskpintarRosa = new TimerTask() {
                @Override
                public void run() {
                    viewTeclado.cambiarColorTecla(fm.getNota().toString() + String.valueOf(fm.getEscala()), Color.PINK);
                }
            };

            TimerTask timerTaskpintarNegro = new TimerTask() {
                @Override
                public void run() {
                    if (fm.getNota().toString().charAt(fm.getNota().toString().length()-1) == '#') {
                        viewTeclado.cambiarColorTecla(fm.getNota().toString() + String.valueOf(fm.getEscala()), Color.black);
                    } else {
                        viewTeclado.cambiarColorTecla(fm.getNota().toString() + String.valueOf(fm.getEscala()), Color.white);
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

    private int getEscalaFromMouseEvent(String nombre){
        return Integer.parseInt(nombre.substring(nombre.length() - 1));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getComponent().getName());
        if (e.getComponent().getName().equals("pausargrabacion")) {
            //TODO: hacer lo que se quiera con la canción, guardarla en un archivo o enviarla al servidor
            switch (viewTeclado.popUpGrabacion()) {
                case 0:
                    //TODO: guardar la canción de forma pública
                    //TODO: pasar cancion por el scocket
                    //TODO: servidor guardarla en bbdd como publica
                    cancion.setNombre(viewTeclado.popUpNombreCancion());
                    break;
                case 1:
                    //TODO: guardar la canción de forma privada
                    //TODO: pasar cancion por el scocket
                    //TODO: servidor guardarla en bbdd como privada
                    cancion.setNombre(viewTeclado.popUpNombreCancion());
                    break;
                case 2:
                    //TODO: no hacer nada
                    break;
            }
            //Volver al modo libre
            modo = Modo.LIBRE;
            viewTeclado.unenablePausarGrabacionBoton();
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
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }
}
