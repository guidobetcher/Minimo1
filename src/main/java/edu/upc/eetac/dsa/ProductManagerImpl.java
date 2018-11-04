package edu.upc.eetac.dsa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    //We implement the façade using a Singleton pattern
    private static ProductManager instance;

    protected List<Productos> productos;
    protected LinkedList<Pedido> pedidos;
    protected HashMap<String, Usuarios> usuarios;

    private ProductManagerImpl(){
        this.productos = new List<Productos>();
        this.pedidos = new LinkedList<>();
        this.usuarios = new HashMap<>();
    }

    public static ProductManager getInstance(){
        if(instance==null) instance = new ProductManagerImpl();
        return instance;
    }

}
