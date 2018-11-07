package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderTest {
    //Logger
    final static Logger log = Logger.getLogger(OrderTest.class.getName());

    //We have to create and object of type ProductManager, for then use its methods
    private ProductManager pm;

    private Usuario u1, u2;

    private Producto producto;

    private Pedido pedido1, pedido2, pedido3;

    private List<Pedido.LProducto> lp1, lp2, lp3;

    private HashMap<String, Usuario> usuarios;

    //Before the tests we have to add Users and some orders
    @Before
    public void setUp(){
        //Because ProductManagerImpl implements ProductManager, we can create on top of pm, new ProductManagerImpl
        this.pm = ProductManagerImpl.getInstance();
        lp1 = new ArrayList<>();
        lp2 = new ArrayList<>();
        lp3 = new ArrayList<>();
        usuarios = new HashMap<>();
        //We create the products
        producto = new Producto("Manzana",1.5);
        producto = new Producto("Pastel",10);
        producto = new Producto("Cerveza",2.5);
        producto = new Producto("Calamares",5);
        producto = new Producto("Patatas",4);
        producto = new Producto("Fanta",2);

        //Place new orders
        Pedido.LProducto l1 = new Pedido.LProducto();
        l1.producto = "Manzana";
        l1.q = 3;
        Pedido.LProducto l2 = new Pedido.LProducto();
        l2.producto = "Pastel";
        l2.q = 5;
        lp1.add(l1);
        lp1.add(l2);
        pedido1 = new Pedido(lp1);
        u1 = new Usuario("Pepe");
        u1.addOrder(pedido1);

        Pedido.LProducto l3 = new Pedido.LProducto();
        l3.producto = "Cerveza";
        l3.q = 3;
        Pedido.LProducto l4 = new Pedido.LProducto();
        l4.producto = "Calamares";
        l4.q = 5;
        lp2.add(l3);
        lp2.add(l4);
        pedido2 = new Pedido(lp2);
        u1.addOrder(pedido2);

        Pedido.LProducto l5 = new Pedido.LProducto();
        l5.producto = "Patatas";
        l5.q = 3;
        Pedido.LProducto l6 = new Pedido.LProducto();
        l6.producto = "Fanta";
        l6.q = 5;
        lp3.add(l1);
        lp3.add(l2);
        pedido3 = new Pedido(lp3);
        u2 = new Usuario("Juan");
        u2.addOrder(pedido3);

        //Create the list of users
        usuarios.put("Pepe", u1);
        usuarios.put("Juan", u2);
        usuarios.put("Carla", new Usuario("Carla"));
        usuarios.put("Sara", new Usuario("Sara"));
    }

    //When the test ends, it's properly to erase the contents added in @Before
    @After
    public void tearDown(){
        this.pm = null;
    }

    //We verify if we place an order properly
    @Test
    public void placeAnOrder() {
        Pedido.LProducto l1 = new Pedido.LProducto();
        l1.producto = "Manzana";
        l1.q = 4;
        Pedido.LProducto l2 = new Pedido.LProducto();
        l2.producto = "Pastel";
        l2.q = 9;
        lp1.add(l1);
        lp1.add(l2);
        Pedido pedido = new Pedido(lp1);

        u1.addOrder(pedido);
    }

    @Test
    public void getAllOrderOfAUser(){
        try {
            LinkedList<Pedido> l = this.pm.getAllOrdersOfAUser("Pepe");

            assertEquals(l.get(0), "Manzana");
            assertEquals(l.get(1), "Pastel");
            assertEquals(l.get(2), "Cerveza");
        }
        catch(UserNotFoundException e){
            log.warn("El usuario del cual intenta visualizar su historial de pedidos NO existe en la lista! " +e.getMessage());
        }
    }


}
