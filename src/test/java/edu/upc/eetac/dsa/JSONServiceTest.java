package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class JSONServiceTest {
    //Logger
    final static Logger log = Logger.getLogger(JSONServiceTest.class.getName());

    //We have to create and object of type ProductManager, for then use its methods
    private ProductManager pm;

    private Usuario u;

    private LinkedList<Pedido> pedidos;

    private List<Producto> productos;

    private HashMap<String, Usuario> usuarios;

    //Before the tests we have to add Users and some orders
    @Before
    public void setUp(){
        //Because ProductManagerImpl implements ProductManager, we can create on top of pm, new ProductManagerImpl
        this.pm = ProductManagerImpl.getInstance();
        pedidos = u.pedidos();
        productos.add(new Producto("Manzana", 20, 3));
        productos.add(new Producto("Cerveza", 5, 2));
        productos.add(new Producto("Pastel", 40, 1));
        //pedidos.add(new Pedido.LProducto()Pedido(productos));
        //usuarios.put("Juan", new Usuario("Juan", pedidos));
    }

    //When the test ends, it's properly to erase the contents added in @Before
    @After
    public void tearDown(){
        this.pm = null;
    }

    //We verify if we place an order properly
    @Test
    public void placeAnOrder(String user, Pedido p) {
        pedidos = u.pedidos();
        productos.add(new Producto("Leche", 2, 2));
        productos.add(new Producto("Cerveza", 5, 2));
        productos.add(new Producto("Pastel", 40, 1));
        //pedidos.add(new Pedido(productos));
    }


}
