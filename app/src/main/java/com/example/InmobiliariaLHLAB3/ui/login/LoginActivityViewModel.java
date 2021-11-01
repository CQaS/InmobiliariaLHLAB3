package com.example.InmobiliariaLHLAB3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.InmobiliariaLHLAB3.request.ApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel
{
    private Context context;
    private MutableLiveData<String> error;
    private MutableLiveData<Boolean> loginMutable;
    private MutableLiveData<String> llamar;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;

    public LoginActivityViewModel(@NonNull Application application)
    {
        super(application);
        context=application.getApplicationContext();

    }

    public LiveData<String> getLLamar()
    {
        if(llamar == null)
            llamar = new MutableLiveData<>();
        return llamar;
    }

    public LiveData<String> getError()
    {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public MutableLiveData<Boolean> getLoginMutable()
    {

        if(loginMutable == null)
        {
            loginMutable = new MutableLiveData<>();
        }
        return loginMutable;
    }

    public void autenticar(String user, String pass)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user);

        if (user != null && pass != null && pass.length() > 0 && user.length() > 0 && matcher.matches())
        {

            //retorna datos al llamar el Login de Inteface en ApiClient..

            Call<String> dato = ApiClient.getMyApiInterface().Login(user,pass);

            dato.enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    if(response.isSuccessful())
                    {
                        SharedPreferences sp = context.getSharedPreferences("datos.dat",0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("Token","Bearer "+ response.body());
                        editor.commit();

                        loginMutable.setValue(true);

                    }
                    else
                    {
                        error.setValue("Datos incorrectos!!");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    Log.d("Token","Salida Incorrecta"+ t.getMessage());
                }
            });

        }
        else
        {
            error.setValue("Datos incorrectos!!");
        }

    }

    public void setSensorLlamar(SensorEvent sensorEvent)
    {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long c = System.currentTimeMillis();

            if ((c - lastUpdate) > 100)
            {
                long diffTime = (c - lastUpdate);
                lastUpdate = c;

                float movimiento = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (movimiento > SHAKE_THRESHOLD)
                {
                    //set Mutable...
                    llamar.setValue("*611");
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
}
