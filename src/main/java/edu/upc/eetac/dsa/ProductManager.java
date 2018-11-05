package edu.upc.eetac.dsa;

import java.util.LinkedList;
import java.util.List;

public interface ProductManager {
    //Methods
    //Ascending order
    List<Productos> getAllProductsSortedByPrice();
    void placeAnOrder(String user, Pedido p);
    void serveAnOrder();
    LinkedList<Pedido> getAllOrdersOfAUser(String user) throws UserNotFoundException;
    //Descending order
    List<Productos> getAllProductsSortedByNumberOfSales();
}
