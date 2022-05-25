package com.example.MedTurno.ui.doctores;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Doctor;

import java.util.ArrayList;

public class DoctoresFragment extends Fragment
{

    private ListView lvDoctores;
    private DoctoresViewModel vm;
    ArrayAdapter<Doctor> adapter;
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.doctores_fragment, container, false);
        context= root.getContext();

        inicializar(root);

        return root;
    }

    private void inicializar(View root)
    {

        lvDoctores = root.findViewById(R.id.lvDoctores);

        vm = new ViewModelProvider(this).get(DoctoresViewModel.class);

        vm.getDoctores().observe(getViewLifecycleOwner(), new Observer<ArrayList<Doctor>>()
        {
            @Override
            public void onChanged(ArrayList<Doctor> doctors)
            {
                adapter= new DoctorAdapter(context, R.layout.doctor_fragment, doctors, getLayoutInflater());
                lvDoctores.setAdapter(adapter);

            }
        });

        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String er)
            {
                Toast.makeText(context,er, Toast.LENGTH_LONG).show();
            }
        });

        vm.cargarDoctores();
    }

}