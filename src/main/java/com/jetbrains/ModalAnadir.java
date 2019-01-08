package com.jetbrains;

import com.vaadin.ui.*;

import java.util.ArrayList;


public class ModalAnadir extends Window {


    Window subWindow = new Window("Edición del item");
    VerticalLayout subContent = new VerticalLayout();
    final TextField nombre = new TextField("Nombre");
    final TextField cantidad = new TextField("Cantidad");
    final TextField precioVenta = new TextField("Precio de Venta");
    final Button botonAceptar = new Button("Aceptar");

    //Listas para comprobación
    private ArrayList<CheckBox> listaCheckbox = new ArrayList<>();
    private ArrayList<Item> listaItems = new ArrayList<>();




    //Para poder llamar a una sub-vantana, se deberá llamar desde un Item para poder editarlo
    public ModalAnadir(TablaInventario tablaInventario){

        //Listener del botón
        botonAceptar.addClickListener(e -> guardarDatos(tablaInventario));

        //Agregamos el contenido de la ventana
        subContent.addComponents(nombre, cantidad, precioVenta, botonAceptar);

        //Centramos el modal en la pantalla
        center();

        //Hacemos que la ventaan sea modal para evitar la interacción del usuario con el resto de elementos
        setModal(true);

        //Agregamos el layout al modal
        setContent(subContent);


    }


    private void guardarDatos(TablaInventario t){

        Item item = new Item(nombre.getValue(), Double.parseDouble(precioVenta.getValue()), Integer.parseInt(cantidad.getValue()));

        t.anadirElemento(item);

        //Refrescamos la tablaInventario
        t.refreshTabla();

        close();
    }

}
