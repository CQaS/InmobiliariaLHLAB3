package com.example.MedTurno.modelo;

import java.io.Serializable;

public class Direccion implements Serializable
{
    private int id;
    private String calle;
    private int numero;
    private String ciudad;

    public Direccion()
    { }

    public Direccion(int id, String calle, int numero, String ciudad)
    {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
