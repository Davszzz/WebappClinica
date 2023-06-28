package com.example.clinicaa.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.Doctor;
import com.example.clinicaa.Models.SHA256;
import com.example.clinicaa.Models.Usuario;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorActivity extends AppCompatActivity {
    private EditText etdoctor;
    private TextInputEditText etcontradoc;
    private Button btnlogind1, btnregresar;
    private List<Doctor> lstdoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        etdoctor = (EditText) findViewById(R.id.etDoctor);
        etcontradoc = (TextInputEditText) findViewById(R.id.eTContraD);

        btnlogind1 = (Button) findViewById(R.id.btnLoginD1);
        btnregresar = (Button) findViewById(R.id.btnRegresar);

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar =  new Intent(DoctorActivity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
        btnlogind1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar())
                {
                    SHA256 SHA = new SHA256();
                    String c1 = etdoctor.getText().toString();
                    String c2 = SHA.Char25(etcontradoc.getText().toString());

                    ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

                    Call<List<Doctor>> call = serviceAPI.listDoctor();
                    call.enqueue(new Callback<List<Doctor>>() {
                        @Override
                        public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                            boolean estado = false;
                            if (response.isSuccessful())
                            {
                                lstdoctor = response.body();
                                for(Doctor x: lstdoctor)
                                {
                                    if(c1.equalsIgnoreCase(x.getCorreoD()) && c2.equalsIgnoreCase(x.getContraseñaD()))
                                    {
                                        estado = true;
                                        Intent doc = new Intent(DoctorActivity.this, MostrarCita.class);
                                        doc.putExtra("id", "" + x.getIdDoctor());
                                        startActivity(doc);
                                        finish();
                                        break;
                                    }
                                    else
                                    {
                                        Toast.makeText(DoctorActivity.this,"Inicio de sesion invalido", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Doctor>> call, Throwable t) {

                        }
                    });


                }


            }
        });
    }

    public boolean validar()
    {
        String c1 = etdoctor.getText().toString();
        String c2 = etcontradoc.getText().toString();

        boolean retorno = true;

        if(c1.isEmpty())
        {
            etdoctor.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c2.isEmpty())
        {
            etcontradoc.setError("Este campo no puede estar vacio");
            retorno = false;
        }

        return retorno;

    }
}