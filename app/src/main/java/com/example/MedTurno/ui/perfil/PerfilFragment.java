package com.example.MedTurno.ui.perfil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Usuario;
import com.example.MedTurno.request.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

public class PerfilFragment extends Fragment
{

    private PerfilViewModel vm;
    private TextInputEditText etNombre, etTelefono, etPassword, etRePassword;
    private TextView etPaciente;
    //private ImageView imageAvatar;
    private Button btGuardar,btEditar;
    private Usuario usuarioActual;
    Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        context = root.getContext();

        inicializar(root);

        return root;
    }

    private  void  inicializar(View view)
    {
        final ImageView imageAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        etPaciente = view.findViewById(R.id.etPaciente);
        etNombre=view.findViewById(R.id.etNombre);
        etTelefono=view.findViewById(R.id.etTelefono);
        etPassword=view.findViewById(R.id.etPassword);
        etRePassword=view.findViewById(R.id.etRePassword);
        btEditar=view.findViewById(R.id.btEditar);
        btGuardar =view.findViewById(R.id.btAceptar);

        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        vm.getUsuarioMutable().observe(getViewLifecycleOwner(), new Observer<Usuario>()
        {
            @Override
            public void onChanged(Usuario usuario)
            {
                //setea el avatar....
                Glide.with(context)
                        .load(ApiClient.getPath() + usuario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageAvatar);

                etPaciente.setText(usuario.getNombre());
                etNombre.setText(usuario.getNombre());
                etTelefono.setText(usuario.getTelefono());

                usuarioActual = usuario;

            }
        });

        vm.recuperarUsuario();

        btEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etNombre.setEnabled(true);
                etPassword.setEnabled(true);
                etRePassword.setEnabled(true);
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
                usuarioActual.setNombre(etNombre.getText().toString());
                usuarioActual.setTelefono(Integer.parseInt(etTelefono.getText().toString()));

                vm.editarPerfil(usuarioActual, etPassword.getText().toString(), etRePassword.getText().toString());

                etNombre.setEnabled(false);
                etTelefono.setEnabled(false);
                etPassword.setEnabled(false);
                etRePassword.setEnabled(false);
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