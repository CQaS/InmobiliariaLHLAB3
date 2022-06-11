package com.example.MedTurno.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MedTurno.modelo.Usuario;
import com.example.MedTurno.request.ApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel
{

    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<String> error;
    private Context context;

    public PerfilViewModel(@NonNull Application application)
    {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<Usuario> getUsuarioMutable() {
        if(usuarioMutable== null)
        {
            usuarioMutable= new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void recuperarUsuario()
    {
        Log.d("nullnull", "recuperar");

        Call<Usuario> usuario = ApiClient.getMyApiInterface().MiPerfil(ApiClient.obtenerToken(context));

        usuario.enqueue(new Callback<Usuario>()
        {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if(response.isSuccessful())
                {
                    usuarioMutable.postValue(response.body());
                }
                else
                {
                    error.setValue("Perfil no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t)
            {
                error.setValue("ERROR -> "+ t.getMessage());
            }
        });

    }

    public void editarPerfil(Usuario usuarioA_editar, String nombre, String telefono, String pass1, String pass2)
    {
        String letras = "^[A-Za-z\\s]{4,20}$";
        Pattern pattern2 = Pattern.compile(letras);
        Matcher mNombre = pattern2.matcher(nombre);

        String numeros = "^[0-9]{10,15}$";
        Pattern pattern3 = Pattern.compile(numeros);
        Matcher mTelefono = pattern3.matcher(telefono);

        String regex = "^[A-Za-z0-9]{8,40}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher password = pattern.matcher(pass1);
        Matcher rePassword = pattern.matcher(pass2);

        if(mNombre.matches() && mTelefono.matches())
        {
            usuarioA_editar.setNombre(nombre);
            usuarioA_editar.setTelefono(Integer.parseInt(telefono));
        }

        if (password.matches() && rePassword.matches() && pass1.equals(pass2))
        {
            usuarioA_editar.setPassword(pass1);

            Call<Usuario> usuario = ApiClient.getMyApiInterface().EditarPerfil(usuarioA_editar, ApiClient.obtenerToken(context));

            usuario.enqueue(new Callback<Usuario>()
            {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response)
                {
                    if(response.isSuccessful())
                    {
                        if(response.body() != null)
                        {
                            usuarioMutable.setValue(response.body());
                            error.setValue("OK: Perfil Actualizado");
                        }
                        else
                        {
                            error.setValue("No se pudo actualizar");
                        }
                    }
                    else
                    {
                        error.setValue("No se pudo modificar el perfil");
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t)
                {
                    error.setValue("ERROR -> "+ t.getMessage());
                }
            });

        }
        else
        {
            error.setValue("Datos incorrectos!!");
        }
    }
}