package model;

import java.io.Serializable;

public class FiguraMusical implements Serializable {
    private Nota nota;
    private long tiempoInicio;
    private long tiempoFinal;
    private long tiempo;
    private int escala;

    public FiguraMusical() {
    }

    public FiguraMusical(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public long getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(long tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
        setTiempo();
    }

    private void setTiempo (){
        tiempo = tiempoFinal - tiempoInicio;
        if (tiempo < 100){
            tiempo = 100;
        }
    }

    public long getTiempo (){
        return tiempo;
    }

    public int getEscala() {
        return escala;
    }

    public void setEscala(int escala) {
        this.escala = escala;
    }
    /**
     * Passem la figura musical a format string
     *
     */
    @Override
    public String toString() {
        return "FiguraMusical{" +
                "nota=" + nota +
                ", tiempo=" + tiempo +
                ", escala=" + escala +
                '}';
    }
    /**
     * Creacio de la string de les figures musicals
     *
     */
    public String toStringToSend (){
        StringBuilder strb = new StringBuilder();
        strb.append(nota.toString()).append("&").append(escala).append("&").append(tiempo);
        return strb.toString();
    }
}
