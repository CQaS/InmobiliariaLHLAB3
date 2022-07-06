package com.example.MedTurno.modelo;

import java.io.Serializable;

public class Doctor implements Serializable
{
    private int id;
    private java.lang.String nombre;
    private java.lang.String matricula;
    private java.lang.String horarioatencion;
    private int idEspecialidad;
    private Especialidad especialidad;
    private java.lang.String tipo;

    public Doctor()
    { }

    public Doctor(int id, java.lang.String nombre, java.lang.String matricula, java.lang.String horarioatencion, int idEspecialidad, Especialidad especialidad, java.lang.String tipo)
    {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.horarioatencion = horarioatencion;
        this.idEspecialidad = idEspecialidad;
        this.especialidad = especialidad;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getNombre() {
        return nombre;
    }

    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }

    public java.lang.String getMatricula() {
        return matricula;
    }

    public void setMatricula(java.lang.String matricula) {
        this.matricula = matricula;
    }

    public java.lang.String getHorarioatencion() {
        return horarioatencion;
    }

    public void setHorarioatencion(java.lang.String horarioatencion) {
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

    public java.lang.String getTipo()
    {
        return tipo;
    }

    public void setTipo(java.lang.String tipo)
    {
        this.tipo = tipo;
    }

    @Override
    public java.lang.String toString() {
        return "Prof. " + nombre;
    }
}
