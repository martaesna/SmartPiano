/*package Controller;

import Model.FiguraMusical;
import View.PianoView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyboardController implements KeyListener {
    private PianoView v;
    private FiguraMusical s;

    public KeyboardController(PianoView v) {
        this.v = v;
        this.s = new FiguraMusical();
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
        s.stopNotebyShortcut(e.getKeyChar(), s.getNotes());
    }

    public void changeShortcut() {
        s.setCustomNote(s.changeNote());
    }
}*/
