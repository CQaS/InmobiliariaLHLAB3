package com.example.MedTurno.ui.turnos;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.MedTurno.R;
import com.example.MedTurno.modelo.Doctor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TurnoNuevosFragment extends Fragment
{
    private TextView etFecha, etHora;
    private Button btSolicitar, btMisturnos;
    private EditText etRazon;
    private ImageView logoNT;
    private LinearLayout lyHora, lyDia, lyRazon, lyPro, lyBtns;
    private int dia, mes, anio, hora, min, idProf = 0;
    private String nombreProf;
    private TurnoNuevosViewModel tn;
    private Context context;
    private Spinner spProfesionales;

    private TurnoNuevosViewModel mViewModel;

    public static TurnoNuevosFragment newInstance() {
        return new TurnoNuevosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.turno_nuevos_fragment, container, false);
        context = root.getContext();

        inicializar(root);

        return root;
    }

    private void inicializar(View root)
    {
        logoNT = root.findViewById(R.id.logoNT);
        lyDia = root.findViewById(R.id.lyDia);
        lyHora = root.findViewById(R.id.lyHora);
        lyRazon = root.findViewById(R.id.lyRazon);
        lyPro = root.findViewById(R.id.lyProfecional);
        lyBtns = root.findViewById(R.id.lyBotones);
        etFecha = root.findViewById(R.id.etFecha);
        etHora = root.findViewById(R.id.etHora);
        etRazon = root.findViewById(R.id.etRazon);
        btSolicitar = root.findViewById(R.id.btSolicitar);
        btMisturnos = root.findViewById(R.id.btnMisturnos);
        spProfesionales = root.findViewById(R.id.spProfesionales);

        tn = new ViewModelProvider(this).get(TurnoNuevosViewModel.class);

        //Turno OK.........
        tn.getTurnoMutable().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String mensaje)
            {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                dialogo.setTitle("MedTurno informa:");
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

        tn.getDoctores().observe(getViewLifecycleOwner(), new Observer<ArrayList<Doctor>>()
        {
            @Override
            public void onChanged(ArrayList<Doctor> profesionales)
            {
                //carga el Spinner...
                ArrayAdapter<Doctor> arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, profesionales);
                spProfesionales.setAdapter(arrayAdapter);

                //item seleccionado...
                spProfesionales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(position > 0)
                        {

                            idProf = ((Doctor)parent.getSelectedItem()).getId();
                            nombreProf = ((Doctor)parent.getSelectedItem()).getNombre();

                            Toast.makeText(context,idProf + " " + nombreProf, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
            }
        });

        tn.getAviso().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String mensaje)
            {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                dialogo.setTitle("MedTurno informa:");
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
                //final Calendar calendar = Calendar.getInstance();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        etFecha.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, dia, mes, anio);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
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

                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        etHora.setText(hourOfDay + ":" + minute);
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
                tn.validarTurno(etHora.getText().toString(), etFecha.getText().toString(), etRazon.getText().toString(), idProf);
            }
        });

        btMisturnos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //logoNT.setVisibility(View.GONE);  OCULTA EL FORM y MUSTRA LA LSITA
                /*lyDia.setVisibility(View.GONE);
                lyHora.setVisibility(View.GONE);
                lyRazon.setVisibility(View.GONE);
                lyPro.setVisibility(View.GONE);
                lyBtns.setVisibility(View.GONE);
                 INICIA EL Fragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.listar_misturnos, TurnosFragment.newInstance());
                transaction.commit();*/
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.turnosFragment);
            }
        });

        Bundle argument = getArguments();
        if(argument != null)
        {
            int id = getArguments().getInt("idCancelar");
            Log.d("cancelar1", Integer.toString(id));
            tn.cancelar(id);
        }
    }
}