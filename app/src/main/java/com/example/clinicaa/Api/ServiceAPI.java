package com.example.clinicaa.Api;

import com.example.clinicaa.Models.*;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceAPI {
    @GET("usuario")
    public abstract Call<List<Usuario>> listUsuario();
    @POST("usuario/agregar")
    public abstract Call<Usuario> addUsu(@Body Usuario usu);
    @GET("doctor")
    public abstract Call<List<Doctor>> listDoctor();
    @GET("especialidad")
    public abstract Call<List<Especialidad>> listEspecialidad();
    @GET("dia")
    public abstract Call<List<Dia>> listdia();
    @GET("horario")
    public abstract Call<List<Horario>> listhorario();
    @GET("cita")
    public abstract Call<List<Cita>> listcita();
    @POST("cita/agregar")
    public abstract Call<Cita> addCita(@Body Cita cita);


}
