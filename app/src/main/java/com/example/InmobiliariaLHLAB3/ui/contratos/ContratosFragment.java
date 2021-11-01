package com.example.InmobiliariaLHLAB3.ui.contratos;

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

import com.example.InmobiliariaLHLAB3.R;

import java.util.ArrayList;

import com.example.InmobiliariaLHLAB3.modelo.Contrato;

public class ContratosFragment extends Fragment
{

    private ListView lvContratos;
    private ContratosViewModel vm;
    ArrayAdapter<Contrato> adapter;
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root= inflater.inflate(R.layout.contratos_fragment, container, false);
        context= root.getContext();

        inicializar(root);

        return root;
    }

    private void inicializar(View root)
    {

        lvContratos = root.findViewById(R.id.lvContratos);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratosViewModel.class);

        vm.getContratos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>()
        {
            @Override
            public void onChanged(ArrayList<Contrato> contratoes)
            {
                adapter= new ContratoAdapter(context,R.layout.contrato_fragment,contratoes,getLayoutInflater());
                lvContratos.setAdapter(adapter);

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

        vm.cargarContratos();
    }

}