package com.example.MedTurno.modelo;

import java.io.Serializable;

public class Turnos implements Serializable
{
    private int id;
    private java.lang.String fechaSolicitud;
    private java.lang.String start;
    private java.lang.String end;
    private java.lang.String color;
    private java.lang.String descripcion;
    private java.lang.String textColor;
    private java.lang.String title;
    private int idPrestador;
    private Prestador prestador;
    private int idDoctor;
    private Doctor doctor;
    private int idUsuario;
    private Usuario usuario;

    public Turnos()
    { }

    public Turnos(int id, java.lang.String fechaSolicitud, java.lang.String start, java.lang.String end, java.lang.String color, java.lang.String descripcion, java.lang.String textColor, java.lang.String title, int idPrestador, Prestador prestador, int idDoctor, Doctor doctor, int idUsuario, Usuario usuario)
    {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.start = start;
        this.end = end;
        this.color = color;
        this.descripcion = descripcion;
        this.textColor = textColor;
        this.title = title;
        this.idPrestador = idPrestador;
        this.prestador = prestador;
        this.idDoctor = idDoctor;
        this.doctor = doctor;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(java.lang.String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public java.lang.String getStart() {
        return start;
    }

    public void setStart(java.lang.String start) {
        this.start = start;
    }

    public java.lang.String getEnd() {
        return end;
    }

    public void setEnd(java.lang.String end) {
        this.end = end;
    }

    public java.lang.String getColor() {
        return color;
    }

    public void setColor(java.lang.String color) {
        this.color = color;
    }

    public java.lang.String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }

    public java.lang.String getTextColor() {
        return textColor;
    }

    public void setTextColor(java.lang.String textColor) {
        this.textColor = textColor;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
