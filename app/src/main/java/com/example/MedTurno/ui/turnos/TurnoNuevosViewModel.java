package com.example.MedTurno.ui.turnos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MedTurno.modelo.Doctor;
import com.example.MedTurno.modelo.Turnos;
import com.example.MedTurno.request.ApiClient;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnoNuevosViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<String> turnoMutable;
    private MutableLiveData<String> aviso;
    private MutableLiveData<ArrayList<Doctor>> doctoresMutable;

    public TurnoNuevosViewModel(@NonNull Application application)
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

    public LiveData<String> getAviso()
    {
        if(aviso == null)
        {
            aviso = new MutableLiveData<>();
        }
        return aviso;
    }

    public MutableLiveData<String> getTurnoMutable()
    {

        if(turnoMutable == null)
        {
            turnoMutable = new MutableLiveData<>();
        }
        return turnoMutable;
    }

    public void validarTurno(String hora, String fecha, String razon, int idProfesional)
    {
        String regex = "^[A-Za-z0-9\\s]{5,40}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(razon);

        if (matcher.matches() && idProfesional > 0 )
        {
            Turnos turno = new Turnos();
            turno.setStart(fecha + " " + hora);
            turno.setDescripcion(razon);
            turno.setIdDoctor(idProfesional);

            Call<Turnos> dato = ApiClient.getMyApiInterface().NuevoTurno(turno, ApiClient.obtenerToken(context));

            dato.enqueue(new Callback<Turnos>()
            {
                @Override
                public void onResponse(Call<Turnos> call, Response<Turnos> response)
                {
                    if (response.isSuccessful())
                    {
                        turnoMutable.setValue("Turno solicitado con Exito!");
                    }
                    else
                    {
                        aviso.setValue("Algo Fallo!");
                    }
                }

                @Override
                public void onFailure(Call<Turnos> call, Throwable t)
                {
                    aviso.setValue("Algo fallo!!");
                }
            });
        }
        else
        {
            aviso.setValue("Datos incorrectos!!");
        }
    }

    public void llenarSpinner()
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
                    aviso.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Doctor>> call, Throwable t)
            {
                aviso.setValue(t.toString());

            }
        });
    }

    public void cancelar(int id)
    {
        Log.d("cancelar2", Integer.toString(id));

        /*Call<String> cancelar = ApiClient.getMyApiInterface().CancelarTurno(id, ApiClient.obtenerToken(context));

        cancelar.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (response.isSuccessful())
                {
                    aviso.setValue("Su turno fue cancelado");
                }
                else
                {
                    aviso.setValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                aviso.setValue("No hubo respuesta");
            }
        });*/


    }
}