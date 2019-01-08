package com.tap;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class Main extends UI {

    TablaTransaccion tablaTransaccion = new TablaTransaccion();
    TablaInventario tablaInventario = new TablaInventario(tablaTransaccion);
    Button botonAnadir = new Button("Añadir item");


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();


        final Label nombreTablaInventario = new Label("Tabla de Inventario");
        final Label nombreTablaTransacciones = new Label("Tabla de Transacciones");

        botonAnadir.addClickListener(e -> anadirItem());

        
        layout.addComponents(nombreTablaInventario, tablaInventario,  botonAnadir, nombreTablaTransacciones, tablaTransaccion);
        
        setContent(layout);


    }

    private void anadirItem(){


        ModalAnadir modal = new ModalAnadir(tablaInventario);

        addWindow(modal);

    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
