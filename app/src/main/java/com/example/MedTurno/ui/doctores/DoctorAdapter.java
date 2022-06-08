package com.example.MedTurno.ui.doctores;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Doctor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DoctorAdapter extends ArrayAdapter<Doctor>
{

    private  Context context;
    private List<Doctor> doctores;
    private LayoutInflater li;

    public DoctorAdapter(@NonNull Context context, int resource, @NonNull List<Doctor> objects, LayoutInflater layoutInflater)
    {
        super(context, resource, objects);

        context = context;
        doctores = objects;
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
            item = li.inflate(R.layout.doctor_fragment,parent,false);
        }

        Doctor doctores = this.doctores.get(position);


        TextView nombreDoc = item.findViewById(R.id.etNombreDoc);;
        nombreDoc.setText(doctores.getNombre());

        TextView especialidad = item.findViewById(R.id.etEspecialidad);
        especialidad.setText(doctores.getEspecialidad() + "\n" + doctores.getHorarioatencion());

        return  item;
    }
}
