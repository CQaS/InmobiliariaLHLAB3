package com.example.InmobiliariaLHLAB3.modelo;

import java.io.Serializable;

public class Inmueble implements Serializable
{
    private int id_Inmu;
    private String direccion_in;
    private int ambientes;
    private String uso;
    private String tipo;
    private double precio;
    private int estado;
    private int disponible;
    private String foto;
    private int id_propietario;
    private Propietario duenio;

    public Inmueble()
    {    }

    public Inmueble(String direccion_in, int ambientes, String tipo, String uso, double precio, int estado, int disponible, String foto)
    {
        direccion_in = direccion_in;
        ambientes = ambientes;
        tipo = tipo;
        uso = uso;
        precio = precio;
        estado=estado;
        disponible = disponible;
        foto = foto;
    }
    public Inmueble(String direccion_in, int ambientes, String tipo, String uso, double precio, int estado,Boolean disponible)
    {
        direccion_in = direccion_in;
        ambientes = ambientes;
        tipo = tipo;
        uso = uso;
        precio = precio;
        estado=estado;
        disponible = disponible;
    }

    public int getId_Inmu() {
        return id_Inmu;
    }

    public void setId_Inmu(int id_Inmu) {
        this.id_Inmu = id_Inmu;
    }

    public String getDireccion_in() {
        return direccion_in;
    }

    public void setDireccion_in(String direccion_in) {
        this.direccion_in = direccion_in;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }
}
