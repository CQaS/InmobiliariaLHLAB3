package com.example.MedTurno.modelo;

public class Usuario
{
    private int id;
    private java.lang.String nombre;
    private int dni;
    private java.lang.String fecNac;
    private java.lang.String mail;
    private int telefono;
    private java.lang.String avatar;
    private java.lang.String password;
    private java.lang.String pregunta;
    private int rol;
    private java.lang.String fecAlta;
    private int idPrestador;
    private Prestador prestador;
    private int idDireccion;
    private Direccion direccion;
    private int estado;

    public Usuario()
    { }

    public Usuario(int id, java.lang.String nombre, int dni, java.lang.String fecNac, java.lang.String mail, int telefono, java.lang.String avatar, java.lang.String password, java.lang.String pregunta, int rol, java.lang.String fecAlta, int idPrestador, Prestador prestador, int idDireccion, Direccion direccion, int estado)
    {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.fecNac = fecNac;
        this.mail = mail;
        this.telefono = telefono;
        this.avatar = avatar;
        this.password = password;
        this.pregunta = pregunta;
        this.rol = rol;
        this.fecAlta = fecAlta;
        this.idPrestador = idPrestador;
        this.prestador = prestador;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.estado = estado;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public java.lang.String getFecNac() {
        return fecNac;
    }

    public void setFecNac(java.lang.String fecNac) {
        this.fecNac = fecNac;
    }

    public java.lang.String getMail() {
        return mail;
    }

    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public java.lang.String getAvatar() {
        return avatar;
    }

    public void setAvatar(java.lang.String avatar) {
        this.avatar = avatar;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public java.lang.String getPregunta() {
        return pregunta;
    }

    public void setPregunta(java.lang.String pregunta) {
        this.pregunta = pregunta;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public java.lang.String getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(java.lang.String fecAlta) {
        this.fecAlta = fecAlta;
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

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado(int estado)
    {
        this.estado = estado;
    }
}
