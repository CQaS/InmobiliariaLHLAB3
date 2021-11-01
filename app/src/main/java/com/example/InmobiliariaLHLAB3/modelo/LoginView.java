package com.example.InmobiliariaLHLAB3.modelo;

public class LoginView
{

    public String usuario;
    public String clave;

    public LoginView()
    {    }

    public LoginView(String usuario, String clave)
    {
        usuario = usuario;
        clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
