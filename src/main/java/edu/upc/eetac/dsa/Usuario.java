package edu.upc.eetac.dsa;

import java.util.LinkedList;

public class Usuario {
    //Attributes
    String username;

    private LinkedList<Pedido> pedidos;

    public Usuario(String username) {
        this.username = username;
        this.pedidos = new LinkedList<Pedido>();
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

    public void addOrder(Pedido p) {
        this.pedidos.add(p);
    }
}
