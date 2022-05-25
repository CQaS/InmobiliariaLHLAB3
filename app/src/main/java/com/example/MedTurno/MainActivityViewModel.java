package com.example.MedTurno;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MedTurno.modelo.Usuario;
import com.example.MedTurno.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel
{
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<String> error;
    private Context context;


    public MainActivityViewModel(@NonNull Application application)
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

    public void setHeaderUsuario()
    {

        Call<Usuario> usuario = ApiClient.getMyApiInterface().MiPerfil(ApiClient.obtenerToken(context));

        usuario.enqueue(new Callback<Usuario>()
        {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response)
            {
                if(response.isSuccessful())
                {
                    usuarioMutable.setValue(response.body());
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

}
