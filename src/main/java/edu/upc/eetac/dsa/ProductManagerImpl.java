package edu.upc.eetac.dsa;

import java.util.*;

import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager{
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    //We implement the façade using a Singleton pattern
    private static ProductManager instance;

    protected List<Productos> productos;
    protected LinkedList<Pedido> pedidos;
    //Initialize the hashmap(key: string; value: Usuarios) of users
    protected HashMap<String, Usuarios> usuarios;

    private ProductManagerImpl(){
        this.productos = new ArrayList<>();
        this.pedidos = new LinkedList<>();
        this.usuarios = new HashMap<>();
    }

    public static ProductManager getInstance(){
        if(instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public List<Productos> getAllProductsSortedByPrice(){
        //We create a copy of the list
        List<Productos> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Productos>() {
            @Override
            public int compare(Productos o1, Productos o2) {
                //Ascending order
                return (int)(o1.getPrice()-o2.getPrice());
            }
        });

        return ret;
    }

    public List<Productos> getAllProductsSortedByNumberOfSales(){
        //We create a copy of the list
        List<Productos> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Productos>() {
            @Override
            public int compare(Productos o1, Productos o2) {
                //Descending order
                return (-1)*(o1.getSales()-o2.getSales());
            }
        });

        return ret;
    }

    public LinkedList<Pedido> getAllOrdersOfAUser(String user) throws UserNotFoundException{
        LinkedList<Pedido> pedidos = null;

        //We want to find the given user
        Usuarios theUser = this.usuarios.get(user);

        if(theUser!=null){
            pedidos = theUser.Pedidos();
        }
        else throw new UserNotFoundException();

        return pedidos;

    }

    public void placeAnOrder(String user, Pedido p){
        //We want to know if the user exists or not
        Usuarios theUser = this.usuarios.get(user);
        LinkedList<Pedido> ret = theUser.Pedidos();

        if(theUser!=null){
            ret.add(p);
        }
        else{
            this.usuarios.put(user, new Usuarios(user, theUser.Pedidos()));
            ret.add(p);
        }

        double quantity =
        /*LinkedList<Pedido> pedidos = null;
        Usuarios theUser = this.usuarios.get(user);

        if(theUser!=null){
            pedidos = theUser.Pedidos();
            pedidos.add(p);
        }
        else{
            this.usuarios.put(user, new Usuarios(user, this.pedidos));
            pedidos.add(p);
        }*/

    }
    public void serveAnOrder(){

    }

}
