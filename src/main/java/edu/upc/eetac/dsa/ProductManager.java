package edu.upc.eetac.dsa;

import java.util.LinkedList;
import java.util.List;

public interface ProductManager {
    //Methods
    List<Productos> getAllProductsSortedByPrice();
    void placeAnOrder(String user, Pedido p);
    void serveAnOrder();
    LinkedList<Pedido> getAllOrdersOfAUser(String user);
    List<Productos> getAllProductsSortedByNumberOfSales();
}
