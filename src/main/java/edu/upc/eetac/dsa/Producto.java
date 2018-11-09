package edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    //Atributes
    String name;
    double price;
    private int sales;

    List<Producto> productos;

    public Producto(String name, double price) {
        this.name = name;
        this.price = price;
        this.productos = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void addProducto (Producto p){
        this.productos.add(p);
    }

    public void addSales(int q) {
        sales = sales + q;
    }

    @Override
    public String toString() {
        return "Producto [Name = " + name + ", Precio = " + price +"]";
    }
}
