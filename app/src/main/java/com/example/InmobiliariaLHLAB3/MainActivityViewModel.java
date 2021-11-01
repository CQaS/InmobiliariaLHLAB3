package com.example.InmobiliariaLHLAB3;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.InmobiliariaLHLAB3.modelo.Propietario;
import com.example.InmobiliariaLHLAB3.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel
{
    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> error;
    private Context context;


    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutable() {
        if(propietarioMutable== null)
        {
            propietarioMutable= new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void setHeaderPropietario()
    {

        Call<Propietario> propietario = ApiClient.getMyApiInterface().MiPerfil(ApiClient.obtenerToken(context));

        propietario.enqueue(new Callback<Propietario>()
        {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response)
            {
                if(response.isSuccessful())
                {
                    propietarioMutable.setValue(response.body());
                }
                else
                {
                    error.setValue("Perfil no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t)
            {
                error.setValue("ERROR -> "+ t.getMessage());
            }
        });

    }

}
