package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Cancion {
    private String nombre;

    private LinkedList<FiguraMusical> figurasMusicales;

    public Cancion (){
        figurasMusicales = new LinkedList<>();
    }

    private void addFiguraMusical (FiguraMusical figuraMusical) {
        figurasMusicales.add(figuraMusical);
    }

    public void startNewFiguraMusical (long time){
        figurasMusicales.add(new FiguraMusical(time));
    }

    public void endNewFiguraMusical (long time, Nota nota, int escala){
        figurasMusicales.getLast().setTiempoFinal(time);
        figurasMusicales.getLast().setNota(nota);
        figurasMusicales.getLast().setEscala(escala);
        System.out.println(figurasMusicales.getLast().toString());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<FiguraMusical> getFigurasMusicales() {
        return figurasMusicales;
    }
}
