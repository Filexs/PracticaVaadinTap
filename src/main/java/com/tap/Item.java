package com.tap;

import java.util.ArrayList;

public class Item {

    //Los items tienen que poder: Borrarse, editarse y crearse.

    //Se podrán construir productos a partir de otros, por lo que habrá una lista de items dentro de los items

    //Tienen que tener nombre y precio, el precio puede ir en varias divisas, también se debe mostrar el beneficio del producto


    // CONSTRUCTOR //

    private String nombre;
    private double precioVenta;
    private double costeFabricacion;
    private int numeroItems;
    private ArrayList<Item> listaComponentes;
    private double beneficio;

    public Item(String nombre, double precioVenta, int numeroItems) {

        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.numeroItems = numeroItems;
        this.listaComponentes = new ArrayList<>();
        calcularBeneficio();
        calcularFabricacion();
    }

    //FIN CONSTRUCTOR //

    public void calcularBeneficio(){

        if(this.listaComponentes.size() > 0){

            //Para el cálculo del beneficio se calcula la diferencia entre el precio de venta y de fabricación
            this.beneficio =  (this.precioVenta - this.costeFabricacion)*this.numeroItems;

        }else{

            //Si el item no se fabrica, no puede otorgar beneficios
            this.beneficio =  0;
        }
    }

    public void calcularFabricacion(){

        if(this.listaComponentes.size() > 0){

            double precioBuffer = 0;

            //El precio de fabricación se compone por todos los precios de los componentes del mismo
            for (Item item:this.listaComponentes) {

                precioBuffer += item.precioVenta;
            }

            this.costeFabricacion =  precioBuffer;

        }else{

            //Si el item no se fabrica, no puede otorgar beneficios, por lo que igualames el coste de
            //fabricación al coste de venta.
            this.costeFabricacion =  this.precioVenta;
        }
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

    public double getCosteFabricacion() {
        return costeFabricacion;
    }

    public void setCosteFabricacion(double costeFabricacion) {
        this.costeFabricacion = costeFabricacion;
    }

    public int getNumeroItems() {
        return numeroItems;
    }

    public void setNumeroItems(int numeroItems) {
        this.numeroItems = numeroItems;
    }

    public ArrayList<Item> getListaComponentes() {
        return listaComponentes;
    }

    public void setListaComponentes(ArrayList<Item> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

    public void anadirUnidad(){

        this.numeroItems += 1;
    }

    public void quitarUnidad(){

        if(this.numeroItems > 0) {
            this.numeroItems -= 1;
        }
    }
}
