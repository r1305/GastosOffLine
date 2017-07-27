package com.example.julian.gastos;

/**
 * Created by Julian on 26/07/2017.
 */

public class Gasto {
    private int id;
    private String desc;
    private String monto;

    public Gasto() {
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
