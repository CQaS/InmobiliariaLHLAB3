package com.example.InmobiliariaLHLAB3.modelo;

import java.io.Serializable;

public class Pago implements Serializable
{
    private int id;
    private int num_pago;
    private String fecha;
    private Double importe;
    private int contratoId;
    private Contrato contrato;


    public Pago()
    {    }

    public Pago(int num_pago, String fecha, Double importe, int contratoId)
    {
        num_pago = num_pago;
        fecha = fecha;
        importe = importe;
        contratoId = contratoId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_pago() {
        return num_pago;
    }

    public void setNum_pago(int num_pago) {
        this.num_pago = num_pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
