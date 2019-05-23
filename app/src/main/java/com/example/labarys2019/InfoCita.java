package com.example.labarys2019;

import java.io.Serializable;

public class InfoCita implements Serializable {

    private String cita;
    private String autor;
    private int acumulado;
    private int puntuaciones;

    public InfoCita(){

    }

    public String getCita() {
        return cita;
    }

    public String getAutor() {
        return autor;
    }

    public int getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(int acumulado) {
        this.acumulado = acumulado;
    }

    public int getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(int puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "InfoCita{" +
                "cita='" + cita + '\'' +
                ", autor='" + autor + '\'' +
                ", puntuacion='" + (acumulado/puntuaciones) + '\'' +
                '}';
    }
}
