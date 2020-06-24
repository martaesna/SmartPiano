package Model;

import java.io.Serializable;
import java.util.LinkedList;

public class Song implements Serializable {
    private LinkedList<Note> notes;
    private String name;

    private String autor;
    private int numRep;
    private int duration;
    private boolean publico;
    private int minutesListened;

    public Song(String name, String autor, int numRep, int duration, boolean publico, int minutesListened) {
        this.name = name;
        this.autor = autor;
        this.numRep = numRep;
        this.duration = duration;
        this.publico = publico;
        this.minutesListened = minutesListened;
    }

    public Song(String name, String author, int numRep) {
        this.name = name;
        this.autor = author;
        this.numRep = numRep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumRep() {
        return numRep;
    }

    public void setNumRep(int numRep) {
        this.numRep = numRep;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public int getMinutesListened() {
        return minutesListened;
    }

    public void setMinutesListened(int minutesListened) {
        this.minutesListened = minutesListened;
    }
}
