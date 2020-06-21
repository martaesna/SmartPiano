package Model;

import javax.sound.midi.*;
import java.util.Scanner;

public class Sound {
    private Note[] notes;

    public Sound() {
        notes = new Note[24];
        notes[0] = new Note("Do0", '<');
        notes[1] = new Note("Do#0", 'a');
        notes[2] = new Note("Re0", 'z');
        notes[3] = new Note("Re#0", 's');
        notes[4] = new Note("Mi0", 'x');
        notes[5] = new Note("Fa0", 'c');
        notes[6] = new Note("Fa#0", 'f');
        notes[7] = new Note("Sol0", 'v');
        notes[8] = new Note("Sol#0", 'g');
        notes[9] = new Note("La0", 'b');
        notes[10] = new Note("La#0", 'h');
        notes[11] = new Note("Si0", 'n');
        notes[12] = new Note("Do1", 'm');
        notes[13] = new Note("Do#1", 'k');
        notes[14] = new Note("Re1", ',');
        notes[15] = new Note("Re#1", 'l');
        notes[16] = new Note("Mi1", '.');
        notes[17] = new Note("Fa1", '-');
        notes[18] = new Note("Fa#1", '4');
        notes[19] = new Note("Sol1", '1');
        notes[20] = new Note("Sol#1", '5');
        notes[21] = new Note("La1", '2');
        notes[22] = new Note("La#1", '6');
        notes[23] = new Note("Si1", '3');
    }

    public Note[] getNotes() {
        return notes;
    }


    public void setCustomNote(Note n) {
        for (int i = 0; i < notes.length; i++) {
            if (n.getScale().equals(notes[i].getScale())) {
                notes[i].setShortcut(n.getShortcut());
            }
        }
    }


    public void playNotebyShortcut (char shortcut, Note[] n) {
        for (int i = 0; i < n.length; i++) {
            if (n[i].getShortcut() == shortcut) {
                n[i].play();
            }
        }
    }

    public void stopNotebyShortcut(char shortcut, Note[] n) {
        for (int i = 0; i < n.length; i++) {
            if (n[i].getShortcut() == shortcut) {
                n[i].setisSustaining(false);
            }
        }
    }

    public Note changeNote() {
        System.out.println("-----------------------------");
        System.out.println("Cambiar la tecla d'un so");
        System.out.println("-----------------------------\n");
        System.out.println("Assignacions actuals:");

        for (int i = 0; i < notes.length; i++) {
            System.out.println("To: "+notes[i].getScale()+"   Tecla: "+notes[i].getShortcut());
        }
        System.out.println("\nQuin to vols modificar?");
        Scanner scanner = new Scanner(System.in);
        String to = scanner.next();
        System.out.println("Cambiant el to "+to+". Quina tecla li vols assignar? ");
        String tecla = scanner.next();
        System.out.println("Assignant tecla "+tecla+" al to "+to+"...");

        for (int j = 0; j < notes.length; j++) {
            if (notes[j].getScale().equals(to)) {
                return new Note(to, tecla.charAt(0));
            }
        }
        return new Note("error", 'a'); //no se que retornar (suposem que l'usuari no entrara coses rares)
    }
}