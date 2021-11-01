package com.example.InmobiliariaLHLAB3.ui.perfil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.InmobiliariaLHLAB3.R;
import com.google.android.material.textfield.TextInputEditText;

import com.example.InmobiliariaLHLAB3.modelo.Propietario;

public class PerfilFragment extends Fragment
{

    private PerfilViewModel vm;
    private TextInputEditText etDni, etNombre, etDireccion, etTelefono;
    private ImageView imageAvatar;
   // private TextInputEditText etEmail;
   // private TextInputEditText etPass;
    private Button btGuardar,btEditar;
    private Propietario propietarioActual;
    Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        context = root.getContext();

        inicializar(root);

        return root;
    }

    private  void  inicializar(View view)
    {
        final ImageView imageAvatar = (ImageView) view.findViewById(R.id.imageAvatar);
        etDni=view.findViewById(R.id.etDni);
        etNombre=view.findViewById(R.id.etNombre);
        etDireccion=view.findViewById(R.id.etDireccion);
        etTelefono=view.findViewById(R.id.etTelefono);
        btEditar=view.findViewById(R.id.btEditar);
        btGuardar =view.findViewById(R.id.btAceptar);

        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        vm.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>()
        {
            @Override
            public void onChanged(Propietario propietario)
            {
                //setea el avatar....
                Glide.with(context)
                        .load("http://192.168.1.104:5000" + propietario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageAvatar);

                etNombre.setText(propietario.getNombre());
                etDni.setText(propietario.getDni());
                etDireccion.setText(propietario.getDireccion());
                etTelefono.setText(propietario.getTel());

                propietarioActual = propietario;

            }
        });
        vm.recuperarPropietario();

        btEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etNombre.setEnabled(true);
                etDni.setEnabled(true);
                etDireccion.setEnabled(true);
                etTelefono.setEnabled(true);
                btEditar.setVisibility(View.GONE);
                btGuardar.setVisibility(View.VISIBLE);

            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Propietario prop = new Propietario(propietarioActual.getId(), etNombre.getText().toString(), etDni.getText().toString(), etDireccion.getText().toString(), etTelefono.getText().toString(), propietarioActual.getEmail(), propietarioActual.getClave());

                propietarioActual.setNombre(etNombre.getText().toString());
                propietarioActual.setDni(etDni.getText().toString());
                propietarioActual.setDireccion(etDireccion.getText().toString());
                propietarioActual.setTel(etTelefono.getText().toString());

                vm.editarPerfil(propietarioActual);

                etNombre.setEnabled(false);
                etDni.setEnabled(false);
                etDireccion.setEnabled(false);
                etTelefono.setEnabled(false);
                btEditar.setVisibility(View.VISIBLE);
                btGuardar.setVisibility(View.INVISIBLE);
            }
        });

        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String er)
            {
                Toast.makeText(getContext(), er, Toast.LENGTH_LONG).show();
            }
        });
    }
}