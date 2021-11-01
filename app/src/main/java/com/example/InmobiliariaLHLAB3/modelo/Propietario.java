package com.example.InmobiliariaLHLAB3.modelo;

public class Propietario
{

    private int id;
    private String nombre;
    private String dni;
    private String direccion;
    private String tel;
    private String email;
    private String clave;
    private String avatar;

    public Propietario()
    {    }

    public Propietario(int id, String nombre, String dni, String direccion, String telefono, String email, String clave, String avatar)
    {
        id = id;
        nombre = nombre;
        dni = dni;
        direccion = direccion;
        telefono = telefono;
        email = email;
        clave = clave;
        avatar = avatar;
    }

    public Propietario(int id, String nombre, String dni, String direccion, String telefono)
    {
        id = id;
        nombre = nombre;
        dni = dni;
        direccion = direccion;
        telefono = telefono;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
