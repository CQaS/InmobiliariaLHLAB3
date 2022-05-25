package com.example.MedTurno.ui.doctores;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.MedTurno.modelo.Doctor;

public class DoctorViewModel extends AndroidViewModel
{

    private MutableLiveData<Doctor> doctor;

    public DoctorViewModel(@NonNull Application application)
    {
        super(application);
    }

    public LiveData<Doctor> getDoctor()
    {
        if(doctor == null)
        {
            doctor = new MutableLiveData<>();
        }

        return doctor;
    }

    public void cargarContrato(Bundle arguments)
    {
        Doctor contrato = (Doctor) arguments.getSerializable("contrato");
        this.doctor.setValue(contrato);
    }
}