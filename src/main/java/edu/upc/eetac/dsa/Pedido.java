package edu.upc.eetac.dsa;

import java.util.List;

public class Pedido {
    //Attributes
    private List<Productos> products;
    int quantity;

    public Pedido(List<Productos> products, int quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public List<Productos> Products() {
        return this.products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
