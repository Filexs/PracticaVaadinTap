package com.tap;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;

public class TablaInventario extends VerticalLayout {




    private Grid<Item> grid = new Grid<>();

    private ArrayList<Item> listaComponentes = new ArrayList<Item>();

    private ArrayList<Item> listaItems = inicializarLista();

    private ModalEditar modal;

    TablaTransaccion tablaTransaccion;

    public TablaInventario(TablaTransaccion tt) {

        tablaTransaccion = tt;

        //Adición de todos los componentes de la tablaInventario
        grid.setItems(listaItems);
        grid.addColumn(Item::getNombre).setCaption("Nombre");
        grid.addColumn(Item::getNumeroItems).setCaption("Cantidad");
        grid.addComponentColumn(this::buildAddButton);
        grid.addComponentColumn(this::buildDelButton);
        grid.addColumn(Item::getPrecioVenta).setCaption("Precio de Venta");
        grid.addColumn(Item::getCosteFabricacion).setCaption("Coste de Fabricación P/U");
        grid.addColumn(Item::getBeneficio).setCaption("Beneficio Total");
        grid.addComponentColumn(this::buildDeleteButton).setCaption("Borrar");
        grid.addComponentColumn(this::buildEditButton).setCaption("Editar");

        //Estilos de la tablaInventario
        grid.setWidth("100%");



        addComponent(grid);
    }


    private ArrayList<Item> inicializarLista(){

        ArrayList<Item> listaItems = new ArrayList<>();

        listaItems.add(new Item("pluma", 10, 3));
        listaItems.add(new Item("piedra", 10, 1));
        listaItems.add(new Item("patata", 10, 5));

        return listaItems;

    }

    private Button buildAddButton(Item i){

        Button button = new Button(VaadinIcons.PLUS_CIRCLE_O);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> anadirUnidad(i));
        return button;
    }

    private Button buildDelButton(Item i){

        Button button = new Button(VaadinIcons.MINUS_CIRCLE_O);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> quitarUnidad(i));
        return button;
    }

    private Button buildDeleteButton(Item i) {
        Button button = new Button(VaadinIcons.CLOSE);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> deleteItem(i));
        return button;
    }

    private Button buildEditButton(Item i){

        Button button = new Button(VaadinIcons.EDIT);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> editarItem(i));
        return button;
    }


    public void anadirElemento(Item i){

        this.listaItems.add(i);


    }

    private void deleteItem(Item i) {

        listaItems.remove(i);
        refreshTabla();
    }

    private void anadirUnidad(Item i){

        ItemTransaccion it = new ItemTransaccion(i);

        tablaTransaccion.anadirTransaccionCompra(it);
        i.anadirUnidad();
        refreshTabla();
    }

    private void quitarUnidad(Item i){

        if(i.getNumeroItems() > 0) {
            ItemTransaccion it = new ItemTransaccion(i);

            tablaTransaccion.anadirTransaccionVenta(it);

            i.quitarUnidad();
            refreshTabla();
        }
    }

    private void editarItem(Item i){

        modal = new ModalEditar(i, this);

        getUI().addWindow(modal);
    }

    public void refreshTabla(){

        grid.setItems(listaItems);
        grid.getDataProvider().refreshAll();
    }

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public void setListaItems(ArrayList<Item> listaItems) {
        this.listaItems = listaItems;
    }
}
