package com.example.clinicaa.Models;

public class Usuario {
    private int idUser;
    private String nombreu;
    private String apellidou;
    private String correou;
    private String contraseñau;
    private String nacimiento;
    private String genero;
    private String telefono;

    public Usuario() {
    }

    public Usuario(int idUser, String nombreu, String apellidou, String correou, String contraseñau, String nacimiento, String genero, String telefono) {
        this.idUser = idUser;
        this.nombreu = nombreu;
        this.apellidou = apellidou;
        this.correou = correou;
        this.contraseñau = contraseñau;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.telefono = telefono;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombreu() {
        return nombreu;
    }

    public void setNombreu(String nombreu) {
        this.nombreu = nombreu;
    }

    public String getApellidou() {
        return apellidou;
    }

    public void setApellidou(String apellidou) {
        this.apellidou = apellidou;
    }

    public String getCorreou() {
        return correou;
    }

    public void setCorreou(String correou) {
        this.correou = correou;
    }

    public String getContraseñau() {
        return contraseñau;
    }

    public void setContraseñau(String contraseñau) {
        this.contraseñau = contraseñau;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
