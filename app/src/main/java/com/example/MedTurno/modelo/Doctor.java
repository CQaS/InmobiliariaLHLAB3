package com.example.MedTurno.modelo;

import java.io.Serializable;

public class Doctor implements Serializable
{
    private int id;
    private String nombre;
    private String matricula;
    private String horarioatencion;
    private int idEspecialidad;
    private Especialidad especialidad;

    public Doctor()
    { }

    public Doctor(int id, String nombre, String matricula, String horarioatencion, int idEspecialidad, Especialidad especialidad)
    {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.horarioatencion = horarioatencion;
        this.idEspecialidad = idEspecialidad;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getHorarioatencion() {
        return horarioatencion;
    }

    public void setHorarioatencion(String horarioatencion) {
        this.horarioatencion = horarioatencion;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
