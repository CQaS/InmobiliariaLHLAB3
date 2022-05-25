package com.example.MedTurno.modelo;

import java.io.Serializable;

public class Especialidad implements Serializable
{
    private int id;
    private String tipo;
    private String especialidad;

    public Especialidad()
    { }

    public Especialidad(int id, String tipo, String especialidad)
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
