package com.example.MedTurno.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.MedTurno.MainActivity;
import com.example.MedTurno.MainActivityViewModel;
import com.example.MedTurno.R;

public class LoginActivity extends AppCompatActivity implements SensorEventListener
{
 private EditText user;
 private EditText pass;
 private Button login;
 private SensorManager senSensorManager;
 private Sensor senAccelerometer;
 private LoginActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) ;

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1004);

        inicializar();
    }

    public void inicializar()
    {
        user = findViewById(R.id.etUsuario);
        pass = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);


        vm = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        vm.getError().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                Toast.makeText(getApplicationContext(), "ALERT:" + s, Toast.LENGTH_LONG).show();
            }
        });

        //Login OK!!.........
        vm.getLoginMutable().observe(this, new Observer<Boolean>()
        {
            @Override
            public void onChanged(Boolean b)
            {
                user.setText("");
                pass.setText("");
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                vm.autenticar(user.getText().toString(), pass.getText().toString());
            }
        });

        vm.getLLamar().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String string)
            {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: " + string));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        vm.setSensorLlamar(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

}