/*package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PianoController implements ActionListener{
    private PianoView v;
    private KeyboardController kc;

    public PianoController(PianoView v, KeyboardController kc) {
        this.v = v;
        this.kc = kc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ChangeShortcut")){
            kc.changeShortcut();
        } else {
            Note.mousePlay(e.getActionCommand());
        }
    }

}
*/