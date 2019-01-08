package com.jetbrains;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;


import com.vaadin.ui.CheckBox;
import java.util.ArrayList;


public class ModalEditar extends Window {


    Window subWindow = new Window("Edición del item");
    VerticalLayout subContent = new VerticalLayout();
    final TextField nombre = new TextField("Nombre");
    final TextField cantidad = new TextField("Cantidad");
    final TextField precioVenta = new TextField("Precio de Venta");
    final Button botonAceptar = new Button("Aceptar");
    final Label label = new Label("Componentes de Fabricación", ContentMode.PREFORMATTED);

    //Listas para comprobación
    private ArrayList<CheckBox> listaCheckbox = new ArrayList<>();
    private ArrayList<Item> listaItems = new ArrayList<>();




    //Para poder llamar a una sub-vantana, se deberá llamar desde un Item para poder editarlo
    public ModalEditar(Item i, Tabla tabla){

        //Les añadimos los antiguos valores del objeto al modal
        nombre.setValue(i.getNombre());
        cantidad.setValue(i.getNumeroItems()+"");
        precioVenta.setValue(i.getPrecioVenta()+"");
        botonAceptar.addClickListener(e -> guardarDatos(i, tabla));


        //Agregamos el contenido de la ventana
        subContent.addComponents(nombre, cantidad, precioVenta, label);

        //Agregamos los checkboxes
        crearSeleccion(i, tabla);

        subContent.addComponent(botonAceptar);

        //Centramos el modal en la pantalla
        center();

        //Hacemos que la ventaan sea modal para evitar la interacción del usuario con el resto de elementos
        setModal(true);

        //Agregamos el layout al modal
        setContent(subContent);


    }

    //Genera los checkbox
    private void crearSeleccion(Item i, Tabla tabla){

        CheckBox checkbox;

        //Creamos la lista de checkbox
        for (Item item:tabla.getListaItems()) {

            //Si el item tiene unidades disponibles y es diferente del item a elaborar, puede utilizarse como "ingrediente"
            if(item != i && item.getNumeroItems() != 0){
                //Por cada elemento existente
                 checkbox = new CheckBox(item.getNombre());

                if(i.getListaComponentes().contains(item)){
                    checkbox.setValue(true);
                }

                listaCheckbox.add(checkbox);
                listaItems.add(i);
                subContent.addComponent(checkbox);
            }
        }
    }


    private void guardarDatos(Item i, Tabla t){

        ArrayList<Item> listaBuffer = new ArrayList<>();

        //Añadimos los componentes de fabricación del elemento
        for (CheckBox cb:listaCheckbox) {

            //Si está marcado como verdadero, añadimos los elementos
            if(cb.getValue()){

                listaBuffer.add(buscarItem(cb.getCaption(), t));
            }

        }

        i.setListaComponentes(listaBuffer);

        //Pasamos los datos de todos los campos del formulario
        i.setNombre(nombre.getValue());
        i.setNumeroItems(Integer.parseInt(cantidad.getValue()));
        i.setPrecioVenta(Double.parseDouble(precioVenta.getValue()));
        i.calcularBeneficio();
        i.calcularFabricacion();



        //Refrescamos la tabla
        t.refreshTabla();

        close();
    }

    private Item buscarItem(String nombre, Tabla t){

        for (Item item:t.getListaItems()) {

            if (item.getNombre() == nombre){

                return item;
            }

        }
        return null;
    }
}
