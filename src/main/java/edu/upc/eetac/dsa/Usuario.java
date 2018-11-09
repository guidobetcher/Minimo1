package edu.upc.eetac.dsa;

import java.util.LinkedList;

public class Usuario {
    //Attributes
    String username;

    private LinkedList<Pedido> pedidos;

    //In the constructor we only pass the username value, not the LinkedList pedidos
    public Usuario(String username) {
        this.username = username;
        this.pedidos = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LinkedList<Pedido> pedidos() {
        return this.pedidos;
    }

    //We add addOrder function in Usuario, because we need to know which order has the user made to serveAnOrder
    public void addOrder(Pedido p) {
        this.pedidos.add(p);
    }
}
