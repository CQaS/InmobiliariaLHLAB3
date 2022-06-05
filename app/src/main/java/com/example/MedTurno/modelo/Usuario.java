package com.example.MedTurno.modelo;

public class Usuario
{
    private int id;
    private java.lang.String nombre;
    private int dni;
    private String fecNac;
    private java.lang.String mail;
    private int telefono;
    private String avatar;
    private java.lang.String password;
    private String pregunta;
    private int rol;
    private String fecAlta;
    private int idprestador;
    private Prestador prestador;
    private int idDireccion;
    private Direccion direccion;

    public Usuario()
    { }

    public Usuario(int id, java.lang.String nombre, int dni, String fecNac, java.lang.String mail, int telefono, String avatar, java.lang.String password, String pregunta, int rol, String fecAlta, int idprestador, Prestador prestador, int idDireccion, Direccion direccion)
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
        this.idprestador = idprestador;
        this.prestador = prestador;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
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

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(String fecAlta) {
        this.fecAlta = fecAlta;
    }

    public int getIdprestador() {
        return idprestador;
    }

    public void setIdprestador(int idprestador) {
        this.idprestador = idprestador;
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
}
