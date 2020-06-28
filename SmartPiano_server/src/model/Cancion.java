package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Cancion implements Serializable {
    private int id;
    private String nombre;
    private LinkedList<FiguraMusical> figurasMusicales;
    private boolean privada;

    public Cancion(){
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

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }

    //Creacion de la string de la cancion: nombrecancion|privacidad|cancion
    public String toStringToSend () {
        StringBuilder strb = new StringBuilder();
        strb.append(nombre + "|");
        if (privada) strb.append(Privacidad.PRV.toString() + "|");
        else strb.append(Privacidad.PBL.toString() + "|");
        for (FiguraMusical fm: figurasMusicales){
            strb.append(fm.toStringToSend() + "/");
        }
        return strb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private enum Privacidad {
        PRV {
            @Override
            public String toString (){
                return "PRV";
            }
        },
        PBL {
            @Override
            public String toString() {
                return "PBL";
            }
        }
    }
}
