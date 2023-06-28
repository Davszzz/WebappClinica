package com.example.clinicaa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clinicaa.Models.Cita;
import com.example.clinicaa.R;

import java.util.ArrayList;

public class CitaAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<Cita>lstcitum;

    public CitaAdapter(Context context, ArrayList<Cita> lstcitum) {
        this.context = context;
        this.lstcitum = lstcitum;
    }

    @Override
    public int getCount() { return lstcitum.size();}

    @Override
    public Object getItem(int i) { return lstcitum.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Cita item = (Cita) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.item_cita, null);
        TextView codusu = (TextView) view.findViewById(R.id.idUser);

        TextView nomusu = (TextView) view.findViewById(R.id.nomUser);
        TextView nomdoc = (TextView) view.findViewById(R.id.nomDoctor);
        TextView idesp = (TextView) view.findViewById(R.id.idEspecialidad);
        TextView nomesp = (TextView) view.findViewById(R.id.nomEspecialidad);
        TextView idhor = (TextView) view.findViewById(R.id.idHorario);
        TextView nomhor = (TextView) view.findViewById(R.id.horario);
        TextView iddia = (TextView) view.findViewById(R.id.idDia);
        TextView nomdia = (TextView) view.findViewById(R.id.Dia);
        TextView costo = (TextView) view.findViewById(R.id.costo);

        codusu.setText("" + item.getIdCita());
        nomusu.setText("Paciente: " + item.getNomUser());
        nomdoc.setText("Doctor: " + item.getNomDoctor());
        nomesp.setText("Especialidad: " + item.getNomEspecialidad());
        nomhor.setText("En el horario: " + item.getHorario());
        nomdia.setText("Acercarse en el dia: " + item.getDia());
        costo.setText("Se debera cobrar: " + item.getCosto());
        return view;
    }
}
