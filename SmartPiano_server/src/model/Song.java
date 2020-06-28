package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Song implements Serializable {
    private int id;
    private String name;
    private String autor;
    private int numRep;
    private int duration;
    private boolean publico;
    private int times_played;
    private int minutesListened;
    private boolean privacity;

    public Song(int id, String name, String autor, int numRep, int duration, boolean publico, int minutesListened) {
        this.id = id;
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

    public Song() {

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

    public int getTimes_played() {
        return times_played;
    }

    public void setTimes_played(int times_played) {
        this.times_played = times_played;
    }

    public int getMinutesListened() {
        return minutesListened;
    }

    public void setMinutesListened(int minutesListened) {
        this.minutesListened = minutesListened;
    }

    public boolean isPrivacity() {
        return privacity;
    }

    public void setPrivacity(boolean privacity) {
        this.privacity = privacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
