package com.tap;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

public class TablaTransaccion extends VerticalLayout {




    private Grid<ItemTransaccion> grid = new Grid<>();

    private ArrayList<ItemTransaccion> listaItems = new ArrayList<>();

    private ModalEditar modal;

    public TablaTransaccion() {

        //Adición de todos los componentes de la tablaInventario
        grid.setItems(listaItems);
        grid.addColumn(ItemTransaccion::getNombre).setCaption("Nombre");
        grid.addColumn(ItemTransaccion::getComprado).setCaption("Tipo Transacción");
        grid.addColumn(ItemTransaccion::getPrecioVenta).setCaption("Gastos/Beneficios");

        //Estilos de la tablaInventario
        grid.setWidth("50%");

        addComponent(grid);
    }


    public void anadirElemento(ItemTransaccion i){
        this.listaItems.add(i);
    }

    public void anadirTransaccionCompra(ItemTransaccion i){

        i.setComprado("Compra");
        anadirElemento(i);
        refreshTabla();
    }

    public void anadirTransaccionVenta(ItemTransaccion i){

        i.setComprado("Venta");
        i.setPrecioVenta(-i.getPrecioVenta());
        anadirElemento(i);
        refreshTabla();
    }

    public void refreshTabla(){

        grid.setItems(listaItems);
        grid.getDataProvider().refreshAll();

    }
}
