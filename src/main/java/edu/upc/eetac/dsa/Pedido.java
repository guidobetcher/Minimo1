package edu.upc.eetac.dsa;

import java.util.List;

public class Pedido {
    //Attributes
    List<Productos> products;
    int quantity;

    public Pedido(List<Productos> products, int quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public List<Productos> getProducts() {
        return products;
    }

    public void setProducts(List<Productos> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
