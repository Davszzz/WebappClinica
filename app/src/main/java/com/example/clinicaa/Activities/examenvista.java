package com.example.clinicaa.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.Doctor;
import com.example.clinicaa.Models.Especialidad;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class examenvista extends AppCompatActivity {

    private Spinner spiEspecialidad, spiDoctores;
    private List<Doctor> lstd;
    private List<Especialidad> lste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examenvista);


        spiEspecialidad = (Spinner) findViewById(R.id.spiEspecialidad);
        spiDoctores= (Spinner) findViewById(R.id.spiDoctores);
        Especialidad();

        spiEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerDoctorxEspecialidad(spiEspecialidad.getSelectedItemPosition()+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                    lste = response.body();
                    String[] items = new String[lste.size()];
                    for(int i=0; i< lste.size();i++)
                    {
                        items[i] = lste.get(i).getDescripcion();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(examenvista.this, android.R.layout.simple_list_item_1, items);
                    spiEspecialidad.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {

            }
        });
    }

    public void SpinnerDoctorxEspecialidad(int id) {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Doctor>> call = serviceAPI.listDoctor();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if(response.isSuccessful()) {
                        lstd = response.body();
                        List<String> list = new ArrayList<>();
                        for(int i=0; i<lstd.size(); i++)
                        {
                            if(lstd.get(i).getIdEspecialidad()==id)
                            {
                                list.add(lstd.get(i).getNombreD() + " " + lstd.get(i).getApellidoD());

                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(examenvista.this, android.R.layout.simple_list_item_1, list);
                        spiDoctores.setAdapter(adapter);
                }
            }


            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });

    };

    }
