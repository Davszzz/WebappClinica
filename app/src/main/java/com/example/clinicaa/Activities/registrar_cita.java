package com.example.clinicaa.Activities;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.Cita;
import com.example.clinicaa.Models.Dia;
import com.example.clinicaa.Models.Doctor;
import com.example.clinicaa.Models.Especialidad;
import com.example.clinicaa.Models.Horario;
import com.example.clinicaa.Models.Usuario;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrar_cita extends AppCompatActivity {

    private Spinner sEspecialidad, sDoctor, sHorario, sDia;
    private EditText etpaciente, etdoctor;
    private Button btncita, btnregresar;
    private TextView txtid;
    private List<Especialidad> listesp;
    private List<Dia> listdium;
    private List<Horario> listhorario;
    private List<Doctor> listdocxesp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cita);

        sEspecialidad = (Spinner) findViewById(R.id.spinnerEspecialidad);
        sDoctor = (Spinner) findViewById(R.id.spinnerDoctor);
        sHorario = (Spinner) findViewById(R.id.spinnerHorario);
        sDia = (Spinner) findViewById(R.id.spinnerDia);

        etpaciente = (EditText) findViewById(R.id.idPaciente);
        etdoctor = (EditText) findViewById(R.id.etDoctorC);

        btncita = (Button) findViewById(R.id.btnCrearCita);
        btnregresar = (Button) findViewById(R.id.btnRegresar2);

        txtid = (TextView) findViewById(R.id.idUsuario);

        int idusu = Integer.parseInt(getIntent().getStringExtra("prueba"));

        SpinnerEspecialidad();
        SpinnerDia();
        SpinnerHorario();

        sEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerDoctorxEspecialidad(sEspecialidad.getSelectedItemPosition()+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etdoctor.setText(sDoctor.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btncita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar())
                {
                    int c1 = idusu;
                    String c2 = etpaciente.getText().toString();
                    int c3 = (sEspecialidad.getSelectedItemPosition() + 1);
                    String c4 = sEspecialidad.getSelectedItem().toString();
                    int c5 = Integer.parseInt(txtid.getText().toString());
                    String c6 = etdoctor.getText().toString();
                    int c7 = (sHorario.getSelectedItemPosition() + 1);
                    String c8 = sHorario.getSelectedItem().toString();
                    int c9 = (sDia.getSelectedItemPosition()+1);
                    String c10 = sDia.getSelectedItem().toString();
                    float c11 = 65;

                    Cita objcita = new Cita(); //(c1,c2,c5,c6,c3,c4,c7,c8,c9,c10,c11);
                    objcita.setIdUser(c1);
                    objcita.setNomUser(c2);
                    objcita.setIdEspecialidad(c3);
                    objcita.setNomEspecialidad(c4);
                    objcita.setIdDoctor(c5);
                    objcita.setNomDoctor(c6);
                    objcita.setIdHorarios(c7);
                    objcita.setHorario(c8);
                    objcita.setIdDia(c9);
                    objcita.setDia(c10);
                    objcita.setCosto(c11);
                    addCita(objcita);
                    Toast.makeText(registrar_cita.this,"Registro Realizado", Toast.LENGTH_LONG).show();
                    Intent doc =  new Intent(registrar_cita.this, MainActivity.class);
                    startActivity(doc);
                }
                else
                {
                    Toast.makeText(registrar_cita.this,"Registro Invalido", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doc =  new Intent(registrar_cita.this, MainActivity.class);
                startActivity(doc);
            }
        });




    }

    public boolean validar()
    {
        String c1 = etpaciente.getText().toString();
        String c2 = etdoctor.getText().toString();
        String c3 = txtid.getText().toString();

        boolean retorno = true;

        if(c1.isEmpty())
        {
            etpaciente.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c2.isEmpty())
        {
            etdoctor.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c3.isEmpty())
        {
            txtid.setError("Este campo no puede estar vacio");
        }

        return retorno;

    }

    public void SpinnerEspecialidad()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Especialidad>> call = serviceAPI.listEspecialidad();
        call.enqueue(new Callback<List<Especialidad>>() {
            @Override
            public void onResponse(Call<List<Especialidad>> call, Response<List<Especialidad>> response) {
                if(response.isSuccessful())
                {
                    listesp = response.body();

                    String[] items = new String[listesp.size()];
                    for(int i=0; i< listesp.size(); i++)
                    {
                        items[i] = listesp.get(i).getDescripcion();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(registrar_cita.this, android.R.layout.simple_list_item_1, items);
                    sEspecialidad.setAdapter(adapter);

                    //List<Especialidad> lst1 = ListaEsp();
                    //for(Especialidad x: lst1)
                    //{
                        //lstesp.add("ola" + x.getDescripcion());
                    //}ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lst1.toString());
                    //sEspecialidad.setAdapter(adapter);

                    //List<String> listSpinner = Arrays.asList("item1", "item2", "item3");
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    //        android.R.layout.simple_spinner_item, listSpinner);
                }

            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {

            }
        });
    }

    public void SpinnerDia()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Dia>> call = serviceAPI.listdia();
        call.enqueue(new Callback<List<Dia>>() {
            @Override
            public void onResponse(Call<List<Dia>> call, Response<List<Dia>> response) {
                if(response.isSuccessful())
                {
                    listdium = response.body();
                    String[] items = new String[listdium.size()];
                    for(int i=0; i< listdium.size(); i++)
                    {
                        items[i] = listdium.get(i).getDia();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(registrar_cita.this, android.R.layout.simple_list_item_1, items);
                    sDia.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Dia>> call, Throwable t) {

            }
        });
    }

    public void SpinnerHorario()
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Horario>> call = serviceAPI.listhorario();
        call.enqueue(new Callback<List<Horario>>() {
            @Override
            public void onResponse(Call<List<Horario>> call, Response<List<Horario>> response) {
                if(response.isSuccessful())
                {
                    listhorario = response.body();
                    String[] items = new String[listhorario.size()];
                    for(int i=0; i< listhorario.size(); i++)
                    {
                        items[i] = listhorario.get(i).getFechaI();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(registrar_cita.this, android.R.layout.simple_list_item_1, items);
                    sHorario.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Horario>> call, Throwable t) {

            }
        });
    }
    public void SpinnerDoctorxEspecialidad(int id)
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Doctor>> call = serviceAPI.listDoctor();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if(response.isSuccessful())
                {
                    listdocxesp = response.body();
                    List<String> list = new ArrayList<String>();
                    for(int i=0; i< listdocxesp.size(); i++)
                    {
                        if(listdocxesp.get(i).getIdEspecialidad()==id)
                        {
                            list.add(listdocxesp.get(i).getNombreD() + " " + listdocxesp.get(i).getApellidoD());
                            txtid.setText("" + (i+1));
                        }


                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(registrar_cita.this, android.R.layout.simple_list_item_1, list);
                    sDoctor.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });
    }

    public void addCita(Cita obj)
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<Cita> call = serviceAPI.addCita(obj);
        call.enqueue(new Callback<Cita>() {
            @Override
            public void onResponse(Call<Cita> call, Response<Cita> response) {
                Cita obj = response.body();
            }

            @Override
            public void onFailure(Call<Cita> call, Throwable t) {

            }
        });

    }

}