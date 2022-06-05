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

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<String> error;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
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

    public void editarPerfil(Usuario u, String pass1, String pass2)
    {
        String regex = "^[A-Za-z0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass1);
        Matcher matcher1 = pattern.matcher(pass2);

        if (pass1 != null && pass2 != null && pass1.length() > 0 && pass2.length() > 0 && matcher.matches() && matcher1.matches() && pass1 == pass2)
        {
            u.setPassword(pass1);

            Call<Usuario> usuario = ApiClient.getMyApiInterface().EditarPerfil(u, ApiClient.obtenerToken(context));

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