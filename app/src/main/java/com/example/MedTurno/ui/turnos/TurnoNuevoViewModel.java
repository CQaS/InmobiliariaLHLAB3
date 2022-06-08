package com.example.MedTurno.ui.turnos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

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

public class TurnoNuevoViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<String> turnoMutable;
    private MutableLiveData<String> error;
    private MutableLiveData<ArrayList<Doctor>> doctoresMutable;

    public TurnoNuevoViewModel(@NonNull Application application)
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
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(razon);

        if (hora != null && fecha != null && razon.length() > 0 && matcher.matches())
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
                        error.setValue("Algo Fallo!");
                    }
                }

                @Override
                public void onFailure(Call<Turnos> call, Throwable t)
                {
                    error.setValue("Algo fallo!!");
                }
            });
        }
        else
        {
            error.setValue("Datos incorrectos!!");
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
                    error.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Doctor>> call, Throwable t)
            {
                error.setValue(t.toString());

            }
        });
    }
}