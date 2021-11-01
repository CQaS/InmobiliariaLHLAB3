package com.example.InmobiliariaLHLAB3.modelo;

import java.io.Serializable;

public class Contrato implements Serializable
{
    private int id;
    private String fe_ini;
    private String fe_fin;
    private  int id_inquilino;
    private Inquilino inquilino;
    private  int id_inmueble;
    private Inmueble inmueble;


    public Contrato(String fechaIngreso, String fechaSalida, Inmueble innmueble, Inquilino inquilino)
    {
        fe_ini = fechaIngreso;
        fe_fin = fechaSalida;
        inmueble = innmueble;
        inquilino = inquilino;
    }

    public Contrato()
    {    }

    public Contrato(String fechaIngreso, String fechaSalida, Inquilino inquilino)
    {
        fe_ini = fechaIngreso;
        fe_fin = fechaSalida;
        inquilino=inquilino;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFe_ini() {
        return fe_ini;
    }

    public void setFe_ini(String fe_ini) {
        this.fe_ini = fe_ini;
    }

    public String getFe_fin() {
        return fe_fin;
    }

    public void setFe_fin(String fe_fin) {
        this.fe_fin = fe_fin;
    }

    public int getId_inquilino() {
        return id_inquilino;
    }

    public void setId_inquilino(int id_inquilino) {
        this.id_inquilino = id_inquilino;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public int getId_inmueble() {
        return id_inmueble;
    }

    public void setId_inmueble(int id_inmueble) {
        this.id_inmueble = id_inmueble;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
