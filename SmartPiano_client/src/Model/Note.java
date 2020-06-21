package Model;

import javax.sound.midi.*;

public class Note extends Thread{
    private String scale;
    private char shortcut;
    private boolean isSustaining;

    public Note(String scale, char shortcut) {
        this.scale = scale;
        this.shortcut = shortcut;
        this.isSustaining = false;
    }

    public String getScale() {
        return scale;
    }

    public char getShortcut() {
        return shortcut;
    }

    public void setShortcut(char shortcut) {
        this.shortcut = shortcut;
    }

    public static void mousePlay (String note) { //quan apreten una tecla amb el ratoli
        try{
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load piano
            int midiNote = midiScaleNoteAssign(note);

            mChannels[0].noteOn(midiNote, 100);//On channel 0, play note number 60 with velocity 100
            //try { Thread.sleep(4000); // wait time in milliseconds to control duration
            // } catch( InterruptedException e ) { }
            mChannels[0].noteOff(midiNote);//turn of the note


        } catch (MidiUnavailableException e) {}
    }

    public void play() {
        try{
            isSustaining = true;
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //Agafem la llista de tots els instruments
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels(); //Preguntar a dubtes si els canals ens servirien per tocar varies tecles a la vegada

            midiSynth.loadInstrument(instr[0]); //El instrument 0 casualment es el piano, hi han 235 instruments differents
            int midiNote = midiScaleNoteAssign(scale);

            mChannels[0].noteOn(midiNote, 100); //fem sonar la tecla
            /*
             * Per crear un so realista del piano, podem crear un thread que pari el temps d'execució. En aquest cas, aquest
             * thread està malament perque si parem el temps de execusio depenent de un periode de temps vol dir que fins que
             * no s'acabi el temps en ms posat en el sleep, no es podra tocar una altre tecla. Descomentar per probar.

             */
            try {
                while (isSustaining) {
                    Thread.sleep(100);
                    if (!isSustaining) {
                        break;
                    }
                }
            } catch(InterruptedException ignore) { }
            mChannels[0].noteOff(midiNote); //Apaguem la nota un cop acabat el temps (s'hauria de fer aixo On Key Release)
        } catch (MidiUnavailableException e) {}
    }

    /*
     *  Mètode per assignar un to a un so. Segons la API, el so 60 correspon al middleDo, que es el primer Do de la segona octava.
     *  Sabent això, a cada to li asignem un numero de la escala.
     */
    private static int midiScaleNoteAssign(String scale) {
        int n=0;

        switch (scale) {
            case "Do0" :
                n=48;
                break;
            case "Do#0" :
                n=49;
                break;
            case "Re0" :
                n=50;
                break;
            case "Re#0" :
                n=51;
                break;
            case "Mi0" :
                n=52;
                break;
            case "Fa0" :
                n=53;
                break;
            case "Fa#0" :
                n=54;
                break;
            case "Sol0" :
                n=55;
                break;
            case "Sol#0" :
                n=56;
                break;
            case "La0" :
                n=57;
                break;
            case "La#0" :
                n=58;
                break;
            case "Si0" :
                n=59;
                break;
            case "Do1" :
                n=60;
                break;
            case "Do#1" :
                n=61;
                break;
            case "Re1" :
                n=62;
                break;
            case "Re#1" :
                n=63;
                break;
            case "Mi1" :
                n=64;
                break;
            case "Fa1" :
                n=65;
                break;
            case "Fa#1" :
                n=66;
                break;
            case "Sol1" :
                n=67;
                break;
            case "Sol#1" :
                n=68;
                break;
            case "La1" :
                n=69;
                break;
            case "La#1" :
                n=70;
                break;
            case "Si1" :
                n=71;
                break;
            default:
                break;
        }

        return n;
    }

    public void setisSustaining(boolean b) {
        this.isSustaining = b;
    }
}
