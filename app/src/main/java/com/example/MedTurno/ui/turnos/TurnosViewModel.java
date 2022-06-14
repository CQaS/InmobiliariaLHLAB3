package com.example.MedTurno.ui.turnos;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MedTurno.modelo.Turnos;
import com.example.MedTurno.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnosViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<String> aviso;
    private ArrayAdapter<Turnos> adapter;
    private MutableLiveData<ArrayList<Turnos>> turnosMutable;

    public TurnosViewModel(@NonNull Application application)
    {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Turnos>> getTurnos()
    {
        if(turnosMutable == null)
        {
            turnosMutable = new MutableLiveData<>();
        }
        return turnosMutable;
    }

    public LiveData<String> getAviso()
    {
        if(aviso == null)
        {
            aviso = new MutableLiveData<>();
        }
        return aviso;
    }


    public void cargarTurnos()
    {
        Call<ArrayList<Turnos>> turnoslist = ApiClient.getMyApiInterface().ListaTurnos(ApiClient.obtenerToken(context));

        turnoslist.enqueue(new Callback<ArrayList<Turnos>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Turnos>> call, Response<ArrayList<Turnos>> response)
            {
                if (response.isSuccessful())
                {
                    turnosMutable.postValue(response.body());
                }
                else
                {
                    aviso.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Turnos>> call, Throwable t)
            {
                aviso.setValue(t.toString());

            }
        });
    }

}