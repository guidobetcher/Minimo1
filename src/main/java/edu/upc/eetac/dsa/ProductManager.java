package edu.upc.eetac.dsa;

import java.util.LinkedList;
import java.util.List;

public interface ProductManager {
    //Methods
    //Ascending order
    List<Producto> getAllProductsSortedByPrice();
    void placeAnOrder(String user, Pedido p);
    Pedido serveAnOrder();
    LinkedList<Pedido> getAllOrdersOfAUser(String user) throws UserNotFoundException;
    //Descending order
    List<Producto> getAllProductsSortedByNumberOfSales();
}
