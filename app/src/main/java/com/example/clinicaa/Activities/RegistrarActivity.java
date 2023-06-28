package com.example.clinicaa.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaa.Api.ServiceAPI;
import com.example.clinicaa.Models.SHA256;
import com.example.clinicaa.Models.Usuario;
import com.example.clinicaa.R;
import com.example.clinicaa.Util.ConnectionREST;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity {

    private EditText etnnombre, etnapellido, etncorreo, etncontra, etnfecha, etntel;
    private EditText id;
    private Button btncrear1, btnregresari;
    private Spinner spiGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        etnnombre = (EditText) findViewById(R.id.eTNnombre);
        etnapellido = (EditText) findViewById(R.id.eTNapellido);
        etncorreo = (EditText) findViewById(R.id.etNcorreo);
        etncontra = (EditText) findViewById(R.id.etNcontra);
        etnfecha = (EditText) findViewById(R.id.etNfecha);
        etntel = (EditText) findViewById(R.id.etNtelefono);

        id = (EditText) findViewById(R.id.id);
        spiGenero =(Spinner) findViewById(R.id.spinnerGenero);

        btncrear1 = (Button) findViewById(R.id.btncrear1);
        btnregresari = (Button) findViewById(R.id.btnregresari);

        load();

        btnregresari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar = new Intent(RegistrarActivity.this, MainActivity.class);
                startActivity(regresar);
            }
        });

    }

    public void prueba(View view)
    {
        if(validar()==true)
        {
            SHA256 SHA = new SHA256();

            String c1 = etnnombre.getText().toString();
            String c2 = etnapellido.getText().toString();
            String c3 = etncorreo.getText().toString();
            String c4 = SHA.Char25(etncontra.getText().toString());
            String c5 = etnfecha.getText().toString();
            String c6 = spiGenero.getSelectedItem().toString();
            String c7 = etntel.getText().toString();

            Usuario objusu = new Usuario();
            objusu.setNombreu(c1);
            objusu.setApellidou(c2);
            objusu.setCorreou(c3);
            objusu.setContraseñau(c4);
            objusu.setNacimiento(c5);
            objusu.setGenero(c6);
            objusu.setTelefono(c7);
            addUsuario(objusu);
            Intent regresar = new Intent(RegistrarActivity.this, MainActivity.class);
            startActivity(regresar);

        }
        else {
            Toast.makeText(this,"Inserte los datos solicitados", Toast.LENGTH_LONG).show();
        }

    }

    public boolean validar()
    {
        String c1 = etnnombre.getText().toString();
        String c2 = etnapellido.getText().toString();
        String c3 = etncorreo.getText().toString();
        String c4 = etncontra.getText().toString();
        String c5 = etnfecha.getText().toString();
        String c6 = etntel.getText().toString();

        boolean retorno = true;

        if(c1.isEmpty())
        {
            etnnombre.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c2.isEmpty())
        {
            etnapellido.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c3.isEmpty())
        {
            etncorreo.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c4.isEmpty())
        {
            etncontra.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(c5.isEmpty())
        {
            etnfecha.setError("Este campo no puede estar vacio");
            retorno = false;
        }
        if(spiGenero.getSelectedItemPosition() == 0)
        {
            Toast.makeText(this,"Seleccione un género", Toast.LENGTH_LONG).show();
            retorno = false;
        }
        if(c6.isEmpty())
        {
            etntel.setError("Este campo no puede estar vacio");
            retorno = false;
        }

        return retorno;
    }

    public void load() {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<List<Usuario>> call = serviceAPI.listUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    List<Usuario> respuesta = response.body();
                    id.setText("" + (respuesta.size()+1));
                } else {
                    Toast.makeText(null, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(null, "Ocurrio un error", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void addUsuario(Usuario obj)
    {
        ServiceAPI serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        Call<Usuario> call = serviceAPI.addUsu(obj);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful())
                {
                    Usuario pro = response.body();
                    mensaje("Registro grabado satisfactoriamente!");
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
            }
        });
    }
    public void mensaje(String msj)
    {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage(msj);
        alerta.show();
    }
}