package com.example.clinicaa.Models;

public class Horario {
    private int idHorarios;
    private String fechaI;
    private String fechaF;

    public Horario() {
    }

    public Horario(int idHorarios, String fechaI, String fechaF) {
        this.idHorarios = idHorarios;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
    }

    public int getIdHorarios() {
        return idHorarios;
    }

    public void setIdHorarios(int idHorarios) {
        this.idHorarios = idHorarios;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
}
