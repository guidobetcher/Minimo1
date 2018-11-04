package edu.upc.eetac.dsa;

public class Productos {
    //Atributes
    String name;
    double price;
    int sales;

    public Productos(String name, double price, int sales) {
        this.name = name;
        this.price = price;
        this.sales = sales;
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

    public void setSales(int sales) {
        this.sales = sales;
    }
}
