package com.example.InmobiliariaLHLAB3.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.InmobiliariaLHLAB3.modelo.Inmueble;
import com.example.InmobiliariaLHLAB3.request.ApiClient;

import java.io.Console;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmueblesViewModel extends AndroidViewModel
{
    private Context context;
    private ArrayAdapter<Inmueble> adapter;
    private MutableLiveData<String> error;
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;

    public InmueblesViewModel(@NonNull Application application)
    {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles()
    {

        if(inmueblesMutable== null)
        {
            inmueblesMutable= new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    public LiveData<String> getError()
    {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void cargarInmuebles()
    {

        Call<ArrayList<Inmueble>> inmuebles = ApiClient.getMyApiInterface().ListaInmueble(ApiClient.obtenerToken(context));

        inmuebles.enqueue(new Callback<ArrayList<Inmueble>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response)
            {
               if(response.isSuccessful())
               {

                   inmueblesMutable.postValue(response.body());
               }
               else
               {
                   error.setValue("No existen inmuebles");
               }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t)
            {
                error.setValue("Ocurrio un error!");
            }
        });
    }}