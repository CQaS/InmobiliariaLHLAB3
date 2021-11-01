package com.example.InmobiliariaLHLAB3.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable
{

    private int id;
    private int dni;
    private String nombre;
    private String mail;
    private String direccion;
    private int tel_inquilino;
    private String lugarTrabajo;
    private String nom_garante;
    private int dni_garante;
    private int tel_garante;

    public Inquilino()
    {    }

    public Inquilino(int dni, String nombre , String mail, String direccion, int tel_inquilino, String lugarTrabajo, String nom_garante, int dni_garante, int tel_garante)
    {
        nombre = nombre;
        dni = dni;
        tel_inquilino = tel_inquilino;
        direccion = direccion;
        mail = mail;
        lugarTrabajo = lugarTrabajo;
        nom_garante = nom_garante;
        dni_garante = dni_garante;
        tel_garante = tel_garante;
    }

    public Inquilino(String direccion) {
        this.direccion=direccion;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTel_inquilino() {
        return tel_inquilino;
    }

    public void setTel_inquilino(int tel_inquilino) {
        this.tel_inquilino = tel_inquilino;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getNom_Garante() {
        return nom_garante;
    }

    public void setNom_Garante(String nom_Garante) {
        this.nom_garante = nom_Garante;
    }

    public int getDni_garante() {
        return dni_garante;
    }

    public void setDni_garante(int dni_garante) {
        this.dni_garante = dni_garante;
    }

    public int getTel_garante() {
        return tel_garante;
    }

    public void setTel_garante(int tel_garante) {
        this.tel_garante = tel_garante;
    }

}
