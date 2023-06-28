package com.example.clinicaa.Models;

public class Doctor {
    private int idDoctor;
    private String nombreD;
    private String apellidoD;
    private int idEspecialidad;
    private String correoD;
    private String contraseñaD;

    public Doctor() {
    }

    public Doctor(int idDoctor, String nombreD, String apellidoD, int idEspecialidad, String correoD, String contraseñaD) {
        this.idDoctor = idDoctor;
        this.nombreD = nombreD;
        this.apellidoD = apellidoD;
        this.idEspecialidad = idEspecialidad;
        this.correoD = correoD;
        this.contraseñaD = contraseñaD;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public String getApellidoD() {
        return apellidoD;
    }

    public void setApellidoD(String apellidoD) {
        this.apellidoD = apellidoD;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getCorreoD() {
        return correoD;
    }

    public void setCorreoD(String correoD) {
        this.correoD = correoD;
    }

    public String getContraseñaD() {
        return contraseñaD;
    }

    public void setContraseñaD(String contraseñaD) {
        this.contraseñaD = contraseñaD;
    }
}
