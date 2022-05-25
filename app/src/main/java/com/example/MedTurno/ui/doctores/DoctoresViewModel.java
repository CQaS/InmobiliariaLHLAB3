package com.example.MedTurno.ui.doctores;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MedTurno.modelo.Doctor;
import com.example.MedTurno.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctoresViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<String> error;
    private ArrayAdapter<Doctor> adapter;
    private MutableLiveData<ArrayList<Doctor>> doctoresMutable;

    public DoctoresViewModel(@NonNull Application application)
    {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Doctor>> getDoctores()
    {
        if(doctoresMutable == null)
        {
            doctoresMutable = new MutableLiveData<>();
        }
        return doctoresMutable;
    }

    public LiveData<String> getError()
    {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }


    public void cargarDoctores()
    {
        Call<ArrayList<Doctor>> doctores = ApiClient.getMyApiInterface().ListaDoctor(ApiClient.obtenerToken(context));

        doctores.enqueue(new Callback<ArrayList<Doctor>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Doctor>> call, Response<ArrayList<Doctor>> response)
            {
                if (response.isSuccessful())
                 {
                    doctoresMutable.postValue(response.body());
                }
                else
                {
                    error.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Doctor>> call, Throwable t)
            {
                error.setValue("No existen contratos");

            }
        });
    }

}