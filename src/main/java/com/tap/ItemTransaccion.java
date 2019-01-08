package com.tap;

public class ItemTransaccion {


    private String nombre;
    private double precioVenta;
    private String comprado;


    public ItemTransaccion(Item i){

        this.nombre = i.getNombre();
        this.precioVenta = i.getPrecioVenta();
        this.comprado = i.getComprado();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getComprado() {
        return comprado;
    }

    public void setComprado(String comprado) {
        this.comprado = comprado;
    }
}
