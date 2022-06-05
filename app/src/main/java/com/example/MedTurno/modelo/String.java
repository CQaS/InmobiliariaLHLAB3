package com.example.MedTurno.modelo;

import java.io.Serializable;

public class String implements Serializable
{
    private int id;
    private java.lang.String tipo;
    private java.lang.String especialidad;

    public String()
    { }

    public String(int id, java.lang.String tipo, java.lang.String especialidad)
    {
        this.id = id;
        this.tipo = tipo;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getTipo() {
        return tipo;
    }

    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }

    public java.lang.String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(java.lang.String especialidad) {
        this.especialidad = especialidad;
    }
}
