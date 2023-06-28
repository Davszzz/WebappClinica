package com.example.clinicaa.Models;

public class Dia {
    private int idDia;
    private String dia;

    public Dia() {
    }

    public Dia(int idDia, String dia) {
        this.idDia = idDia;
        this.dia = dia;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
