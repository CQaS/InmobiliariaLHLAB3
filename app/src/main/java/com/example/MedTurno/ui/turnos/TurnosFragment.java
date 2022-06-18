package com.example.MedTurno.ui.turnos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Turnos;

import java.util.ArrayList;

public class TurnosFragment extends Fragment
{

    private ListView lvTurnos;
    private TurnosViewModel vm;
    ArrayAdapter<Turnos> adapter;
    Context context;

    public static TurnosFragment newInstance() {
        return new TurnosFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.turnos_fragment, container, false);
        context= root.getContext();

        inicializar(root);

        return root;
    }

    private void inicializar(View root)
    {

        lvTurnos = root.findViewById(R.id.lvTurnos);

        vm = new ViewModelProvider(this).get(TurnosViewModel.class);

        vm.getTurnos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Turnos>>()
        {
            @Override
            public void onChanged(ArrayList<Turnos> turnos)
            {
                adapter = new TurnoAdapter(context, R.layout.turno_fragment, turnos, getLayoutInflater());
                lvTurnos.setAdapter(adapter);

            }
        });

        vm.getAviso().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String er)
            {
                Toast.makeText(context,"errorT " + er, Toast.LENGTH_LONG).show();
            }
        });

        Bundle argument = getArguments();
        if(argument != null)
        {
            Toast.makeText(context,"argumento != null" + argument, Toast.LENGTH_LONG).show();
            //vm.cancelar(turnosCancelar);
        }

        vm.cargarTurnos();

    }

}