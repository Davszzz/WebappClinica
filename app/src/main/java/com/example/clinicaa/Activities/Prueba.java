package com.example.clinicaa.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.Cita;
import com.example.clinicaa.Models.Dia;
import com.example.clinicaa.Models.Doctor;
import com.example.clinicaa.Models.Especialidad;
import com.example.clinicaa.Models.Usuario;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Prueba extends AppCompatActivity {

    private TextView ola;
    private ServiceAPI serviceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        ola = (TextView) findViewById(R.id.txtprueba);

        Doctor();
    }

    public void Usuario()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Usuario>> call = serviceAPI.listUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful())
                {
                    List<Usuario> respuesta = response.body();
                    ola.setText("\n\n\n\n");
                    for(Usuario x:respuesta)
                    {
                        ola.append("Id: " + x.getIdUser() + " Nombre: " +x.getNombreu() + " Apellido" + x.getApellidou() + " Correo: " + x.getCorreou() + " Contraseña: " + x.getContraseñau() + " Nacimiento " + x.getNacimiento() + " Genero: " + x.getGenero() + " Telefono " + x.getTelefono() + "\n");
                    }
                }
                else
                {
                    Toast.makeText(null, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(null, "Ocurrio un error", Toast.LENGTH_LONG).show();

            }
        });

    }
    public void Doctor()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Doctor>> call = serviceAPI.listDoctor();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if(response.isSuccessful())
                {
                    List<Doctor> respuesta = response.body();
                    ola.setText("\n\n\n\n");
                    for(Doctor x:respuesta)
                    {
                        ola.append("Id: " + x.getIdDoctor() + " Nombre: " +x.getNombreD() + " Apellido" + x.getApellidoD() + " Correo: " + x.getCorreoD() + " Contraseña: " + x.getContraseñaD() + " Especialidad " + x.getIdEspecialidad() + "\n");
                    }

                }
                else
                {
                    Toast.makeText(null, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Toast.makeText(null, "Ocurrio un error", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void Especialidad()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Especialidad>> call = serviceAPI.listEspecialidad();
        call.enqueue(new Callback<List<Especialidad>>() {
            @Override
            public void onResponse(Call<List<Especialidad>> call, Response<List<Especialidad>> response) {
                if(response.isSuccessful())
                {
                    List<Especialidad> respuesta = response.body();
                    ola.setText("\n\n\n\n");
                    for(Especialidad x:respuesta)
                    {
                        ola.append("Id: " + x.getIdEspecialidad() + " Nombre: " +x.getDescripcion() + "\n");
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {

            }
        });
    }
    public void buscarPorCategoria(int idesp)
    {

        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Doctor>> call = serviceAPI.listDoctor();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if(response.isSuccessful())
                {
                    List<Doctor> respuesta = response.body();
                    ola.setText("\n\n\n\n");
                    for (int i =0; i<respuesta.size(); i++)
                    {
                        if(respuesta.get(i).getIdEspecialidad() == idesp)
                        {
                            ola.append(respuesta.get(i).getNombreD());
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });
    }

    public void Dia()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Dia>> call = serviceAPI.listdia();
        call.enqueue(new Callback<List<Dia>>() {
            @Override
            public void onResponse(Call<List<Dia>> call, Response<List<Dia>> response) {
                if(response.isSuccessful())
                {
                    List<Dia> respuesta = response.body();
                    ola.setText("\n\n\n\n");
                    for(Dia x:respuesta)
                    {
                        ola.append("Id: " + x.getIdDia() + " Nombre: " +x.getDia() + "\n");
                    }


                }
            }

            @Override
            public void onFailure(Call<List<Dia>> call, Throwable t) {

            }
        });


    }


}