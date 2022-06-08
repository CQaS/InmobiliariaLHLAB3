package com.example.MedTurno.ui.turnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Doctor;

import java.util.ArrayList;
import java.util.Calendar;

public class TurnoNuevoFragment extends AppCompatActivity
{
    private TextView etFecha, etHora, select;
    private Button btSolicitar;
    private EditText etRazon;
    private int dia, mes, anio, hora, min;
    private TurnoNuevoViewModel tn;
    private Context context;
    private Spinner spProfesionales;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_nuevo_fragment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inicializar();
    }

    private void inicializar()
    {
        etFecha = findViewById(R.id.etFecha);
        etHora = findViewById(R.id.etHora);
        etRazon = findViewById(R.id.etRazon);
        btSolicitar = findViewById(R.id.btSolicitar);
        spProfesionales = findViewById(R.id.spProfesionales);
        select = findViewById(R.id.profesionalSelect);

        tn = new ViewModelProvider(this).get(TurnoNuevoViewModel.class);

        //Turno OK.........
        tn.getTurnoMutable().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String mensaje)
            {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                dialogo.setTitle("Atención");
                dialogo.setMessage(mensaje);

                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    { }
                });
                dialogo.show();
            }
        });

        tn.getDoctores().observe(this, new Observer<ArrayList<Doctor>>()
        {
            @Override
            public void onChanged(ArrayList<Doctor> profesionales)
            {
                //carga el Spinner...
                ArrayAdapter<Doctor> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, profesionales);
                spProfesionales.setAdapter(arrayAdapter);

                //item seleccionado...
                spProfesionales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(position > 0)
                        {
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setTextSize(18);

                            int idProf = ((Doctor)parent.getSelectedItem()).getId();
                            String nombreProf = ((Doctor)parent.getSelectedItem()).getNombre();

                            Toast.makeText(TurnoNuevoFragment.this,idProf + " " + nombreProf, Toast.LENGTH_LONG).show();
                            select.setText(idProf);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
            }
        });

        tn.getError().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String mensaje)
            {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                dialogo.setTitle("Atención");
                dialogo.setMessage(mensaje);

                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    { }
                });
                dialogo.show();
            }
        });

        tn.llenarSpinner();

        etFecha.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TurnoNuevoFragment.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        etFecha.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, dia, mes, anio);
                datePickerDialog.show();
            }
        });

        etHora.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar calendar = Calendar.getInstance();
                hora = calendar.get(Calendar.HOUR);
                min = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(TurnoNuevoFragment.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        etHora.setText(hourOfDay + " - " + minute);
                    }
                }, hora, min, false);
                timePickerDialog.show();
            }
        });

        btSolicitar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tn.validarTurno(etHora.getText().toString(), etFecha.getText().toString(), etRazon.getText().toString(), Integer.parseInt(select.getText().toString()));
            }
        });
    }
}