package com.example.clinicaa.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.SHA256;
import com.example.clinicaa.Models.Usuario;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etusu;
    private TextInputEditText etcontra;
    private Button btnlogin, btncrear, btnloginD;
    private List<Usuario> listusu;
    private ServiceAPI serviceAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusu = (EditText) findViewById(R.id.eTUsu);
        etcontra = (TextInputEditText) findViewById(R.id.eTContraD);

        btnlogin = (Button) findViewById(R.id.btnLogin);
        btncrear = (Button) findViewById(R.id.btnCrear);
        btnloginD = (Button) findViewById(R.id.btnLoginD);


        btnloginD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doc =  new Intent(MainActivity.this, DoctorActivity.class);
                startActivity(doc);

                //Intent doc =  new Intent(MainActivity.this, Prueba.class);
                //startActivity(doc);
            }
        });

        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doc =  new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(doc);
            }
        });

    }
    public void prueba(View view)
    {
        if(validar()==true)
        {
            SHA256 SHA = new SHA256();
            String c1 = etusu.getText().toString();
            String c2 = SHA.Char25(etcontra.getText().toString());

            ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);

            Call<List<Usuario>> call = serviceAPI.listUsuario();
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    boolean estado = false;
                    if(response.isSuccessful())
                    {
                        listusu = response.body();
                        for(Usuario x: listusu)
                        {
                            if(c1.equalsIgnoreCase(x.getCorreou()) && c2.equalsIgnoreCase(x.getContraseñau()))
                            {
                                estado = true;
                                Intent doc =  new Intent(MainActivity.this, registrar_cita .class);
                                doc.putExtra("prueba", "" + x.getIdUser());
                                startActivity(doc);
                                finish();
                                break;

                            }
                            else
                            {

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {

                }
            });

        }


    }

    public boolean validar()
    {
        String c1 = etusu.getText().toString();
        String c2 = etcontra.getText().toString();

        boolean retorno = true;

        if(c1.isEmpty())
        {
            etusu.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c2.isEmpty())
        {
            etcontra.setError("Este campo no puede estar vacio");
            retorno = false;
        }

        return retorno;

    }

}