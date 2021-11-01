package com.example.InmobiliariaLHLAB3.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.InmobiliariaLHLAB3.R;
import com.google.android.material.textfield.TextInputEditText;

import com.example.InmobiliariaLHLAB3.modelo.Inmueble;

public class InmuebleFragment extends Fragment
{
    private InmuebleViewModel vm;
    private  TextInputEditText etDireccion;
    private  TextInputEditText etAmbientes;
    private  TextInputEditText etTipo;
    private  TextInputEditText etUso;
    private  TextInputEditText etPrecio;
    private  CheckBox cbestado;
    private  ImageView imagen;
    private Button editar;
    private Inmueble inmuebleActual;
    Context context;

    public InmuebleFragment(TextInputEditText etDireccion, TextInputEditText etAmbientes, TextInputEditText etTipo, TextInputEditText etUso, TextInputEditText etPrecio, CheckBox cbEstado, ImageView imagen,Button editar)
    {
        etDireccion = etDireccion;
        etAmbientes = etAmbientes;
        etTipo = etTipo;
        etUso = etUso;
        etPrecio = etPrecio;
        cbestado = cbEstado;
        imagen=imagen;
        editar=editar;
    }

    public InmuebleFragment()
    {    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_inmueble, container, false);
        context = root.getContext();

        inicializar(root, savedInstanceState);
        return root;
    }

    private void inicializar(View view, Bundle bundle)
    {
        etDireccion=view.findViewById(R.id.etDomicilio);
        etAmbientes=view.findViewById(R.id.etAmbientes);
        etTipo=view.findViewById(R.id.etTipo);
        etUso=view.findViewById(R.id.etUso);
        etPrecio=view.findViewById(R.id.etPrecio);
        cbestado=view.findViewById(R.id.cbEstado);
        editar=view.findViewById(R.id.btEditar2);
        final ImageView imagen = (ImageView) view.findViewById(R.id.imagen);

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);

        vm.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>()
        {
            @Override
            public void onChanged(Inmueble inmueble)
            {
                etDireccion.setText(inmueble.getDireccion_in());
                etAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
                etTipo.setText(inmueble.getTipo());
                etUso.setText(inmueble.getUso());
                etPrecio.setText(String.valueOf(inmueble.getPrecio()));
                cbestado.setChecked(inmueble.getDisponible()==1?true:false);

                //setea la imagen....
                Glide.with(context)
                        .load("http://192.168.1.104:5000" + inmueble.getFoto())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imagen);

                inmuebleActual = inmueble;
            }
        });

        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String er)
            {
                Toast.makeText(context,er,Toast.LENGTH_LONG).show();
            }
        });

        vm.cargarInmueble(getArguments());

        editar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                vm.editarInmueble(inmuebleActual, cbestado.isChecked());
            }
        });
    }
}