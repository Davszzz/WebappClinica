package com.example.clinicaa.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.clinicaa.Adapters.CitaAdapter;
import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.Cita;
import com.example.clinicaa.Models.Usuario;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostrarCita extends AppCompatActivity {

    private ListView lstView;
    private List<Cita> lstcitum;
    private CitaAdapter citaAdapter;
    private Button btnregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_cita);

        lstView = (ListView) findViewById(R.id.listarCita);
        btnregresar= (Button) findViewById(R.id.btnregresar2);
        int idoctor = Integer.parseInt(getIntent().getStringExtra("id"));
        cargar(idoctor);


        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doc =  new Intent(MostrarCita.this, DoctorActivity.class);
                startActivity(doc);
            }
        });
    }
    public void cargar(int id)
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Cita>> call = serviceAPI.listcita();
        call.enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
                if(response.isSuccessful())
                {
                    lstcitum = response.body();
                    ArrayList<Cita> list = new ArrayList<>();
                    for(Cita x: lstcitum)
                    {
                        if(x.getIdDoctor() ==id)
                        {
                            list.add(x);
                        }
                    }
                    citaAdapter = new CitaAdapter(MostrarCita.this, list);
                    lstView.setAdapter(citaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {

            }
        });
    }


}