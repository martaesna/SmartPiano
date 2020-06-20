package Controller;

import Model.Sound;
import View.PianoView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {
    private PianoView v;
    private Sound s;

    public KeyboardController(PianoView v) {
        this.v = v;
        this.s = new Sound();
    }

    @Override
    public void keyTyped(KeyEvent e) { //quan escrius una tecla (buit)

    }

    @Override
    public void keyPressed(KeyEvent e) {
        s.playNotebyShortcut(e.getKeyChar(), s.getNotes()); //al apretar una tecla

        // d'alguna manera s'han de crear Threads per allargar el so de les tecles i desactivarlos abaix en keyReleased (preguntar a dubtes)
    }

    @Override
    public void keyReleased(KeyEvent e) { //al deixar anar la tecla

    }

    public void changeShortcut() {
        s.setCustomNote(s.changeNote());
    }
}
