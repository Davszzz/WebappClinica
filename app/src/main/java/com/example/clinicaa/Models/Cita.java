package com.example.clinicaa.Models;

public class Cita {
    private int idCita;
    private int idUser;
    private String nomUser;
    private int idDoctor;
    private String nomDoctor;
    private int idEspecialidad;
    private String nomEspecialidad;
    private int idHorarios;
    private String horario;
    private int idDia;
    private String dia;
    private Float costo;

    public Cita() {
    }

    public Cita(int idCita, int idUser, String nomUser, int idDoctor, String nomDoctor, int idEspecialidad, String nomEspecialidad, int idHorarios, String horario, int idDia, String dia, Float costo) {
        this.idCita = idCita;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.idDoctor = idDoctor;
        this.nomDoctor = nomDoctor;
        this.idEspecialidad = idEspecialidad;
        this.nomEspecialidad = nomEspecialidad;
        this.idHorarios = idHorarios;
        this.horario = horario;
        this.idDia = idDia;
        this.dia = dia;
        this.costo = costo;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNomDoctor() {
        return nomDoctor;
    }

    public void setNomDoctor(String nomDoctor) {
        this.nomDoctor = nomDoctor;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNomEspecialidad() {
        return nomEspecialidad;
    }

    public void setNomEspecialidad(String nomEspecialidad) {
        this.nomEspecialidad = nomEspecialidad;
    }

    public int getIdHorarios() {
        return idHorarios;
    }

    public void setIdHorarios(int idHorarios) {
        this.idHorarios = idHorarios;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
}
