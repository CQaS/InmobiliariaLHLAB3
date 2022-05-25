package com.example.MedTurno.modelo;

import java.io.Serializable;

public class Turnos implements Serializable
{
    private int id;
    private String fechaSolicitud;
    private String start;
    private String end;
    private String color;
    private String descripcion;
    private String textColor;
    private String title;
    private int idPrestador;
    private Prestador prestador;
    private int idDoctor;
    private Doctor doctor;
    private int idUsuario;
    private Usuario usuario;

    public Turnos()
    { }

    public Turnos(int id, String fechaSolicitud, String start, String end, String color, String descripcion, String textColor, String title, int idPrestador, Prestador prestador, int idDoctor, Doctor doctor, int idUsuario, Usuario usuario)
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

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
