package edu.upc.eetac.dsa;

import java.util.LinkedList;

public class Usuarios {
    //Attributes
    String username;

    private LinkedList<Pedido> pedidos;

    public Usuarios(String username, LinkedList<Pedido> pedidos) {
        this.username = username;
        this.pedidos = pedidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LinkedList<Pedido> Pedidos() {
        return this.pedidos;
    }
}
