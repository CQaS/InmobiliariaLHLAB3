package com.example.MedTurno.ui.turnos;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Turnos;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurnoAdapter extends ArrayAdapter<Turnos>
{

    private  Context context;
    private List<Turnos> turnos;
    private LayoutInflater li;

    public TurnoAdapter(@NonNull Context context, int resource, @NonNull List<Turnos> objects, LayoutInflater layoutInflater)
    {
        super(context, resource, objects);

        context = context;
        turnos = objects;
        li = layoutInflater;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View item= convertView;

        if(item == null)
        {
            item = li.inflate(R.layout.turno_fragment,parent,false);
        }

        Turnos turnos = this.turnos.get(position);


        TextInputEditText fechas = item.findViewById(R.id.etFechas);;
        fechas.setText(turnos.getStart().toString());

        TextInputEditText especialidad = item.findViewById(R.id.etEspecialista);
        especialidad.setText(turnos.getDoctor().getNombre());

        return  item;
    }
}
