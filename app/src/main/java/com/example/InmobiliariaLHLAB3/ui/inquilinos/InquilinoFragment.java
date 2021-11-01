package com.example.InmobiliariaLHLAB3.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.InmobiliariaLHLAB3.R;
import com.google.android.material.textfield.TextInputEditText;

import com.example.InmobiliariaLHLAB3.modelo.Inquilino;

public class InquilinoFragment extends Fragment
{

    private InquilinoViewModel vm;
    private  TextInputEditText etDni;
    private  TextInputEditText etNombre;
    private  TextInputEditText etDireccion;
    private  TextInputEditText etTelefono;
    private  TextInputEditText etEmail;
    private  TextInputEditText etLugarTrabajo;
    private  TextInputEditText etNombreGarante;
    private  TextInputEditText etDniGarante;
    private  TextInputEditText etTelefonoGarante;

    public InquilinoFragment()
    {    }

    public InquilinoFragment(int contentLayoutId, TextInputEditText etDni, TextInputEditText etNombre, TextInputEditText etDireccion, TextInputEditText etTelefono, TextInputEditText etEmail, TextInputEditText etNombreGarante, TextInputEditText etDniGarante, TextInputEditText etTelefonoGarante)
    {
        super(contentLayoutId);
        etDni = etDni;
        etNombre = etNombre;
        etDireccion = etDireccion;
        etTelefono = etTelefono;
        etEmail = etEmail;
        etNombreGarante = etNombreGarante;
        etDniGarante = etDniGarante;
        etTelefonoGarante = etTelefonoGarante;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_inquilino, container, false);
        inicializar(root, savedInstanceState);
        return root;
    }

    public void inicializar(View view, Bundle bundle)
    {
        etDni= view.findViewById(R.id.etDni2);
        etNombre= view.findViewById(R.id.etNombre);
        etDireccion =view.findViewById(R.id.etDireccion);
        etTelefono=view.findViewById(R.id.etTelefono);
        etEmail= view.findViewById(R.id.etEmail);
        etLugarTrabajo=view.findViewById(R.id.etLugarTrabajo);
        etDniGarante=view.findViewById(R.id.etDniGarante);
        etNombreGarante= view.findViewById(R.id.etNombreGarante);
        etTelefonoGarante=view.findViewById(R.id.etTelefonoGarante);


        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);

        vm.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>()
        {
            @Override
            public void onChanged(Inquilino inquilino)
            {

                etNombre.setText(inquilino.getNombre());
                etDni.setText(String.valueOf(inquilino.getDni()));
                etTelefono.setText(String.valueOf(inquilino.getTel_inquilino()));
                etDireccion.setText(inquilino.getDireccion());
                etEmail.setText(inquilino.getMail());
                etLugarTrabajo.setText(inquilino.getLugarTrabajo());
                etNombreGarante.setText(inquilino.getNom_Garante());
                etDniGarante.setText(String.valueOf(inquilino.getDni_garante()));
                etTelefonoGarante.setText(String.valueOf(inquilino.getTel_garante()));
            }
        });

        vm.cargarInquilino(getArguments());
    }


}
