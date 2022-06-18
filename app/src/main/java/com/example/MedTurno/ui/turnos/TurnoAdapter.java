package com.example.MedTurno.ui.turnos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Turnos;
import com.example.MedTurno.modelo.Usuario;
import com.example.MedTurno.request.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnoAdapter extends ArrayAdapter<Turnos>
{
    private TurnosViewModel vm;

    private  Context context;
    private List<Turnos> turnos;
    private LayoutInflater li;
    private TextInputEditText fechas, especialidad;
    private Button btnCancelar;
    private String idT;

    public TurnoAdapter(@NonNull Context context, int resource, @NonNull List<Turnos> objects, LayoutInflater layoutInflater)
    {
        super(context, resource, objects);

        this.context = context;
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

        fechas = item.findViewById(R.id.etFechas);;
        fechas.setText(turnos.getStart().toString());

        especialidad = item.findViewById(R.id.etEspecialista);
        especialidad.setText(turnos.getDoctor().getNombre());

        btnCancelar = item.findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("cancelar", Integer.toString(turnos.getId()));
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.medturno_info)
                        .setIcon(R.drawable.info)
                        .setMessage(R.string.seguro)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Bundle TurnoCalcelar = new Bundle();
                                TurnoCalcelar.putSerializable("turnoCancelar", turnos);

                                Navigation.findNavController((Activity) context, R.id.nav_host_fragment)
                                        .navigate(R.id.turnosFragment, TurnoCalcelar);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        })
                        .show();
            }

        });

        return  item;
    }
}
