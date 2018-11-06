package edu.upc.eetac.dsa;

import java.util.*;

import org.apache.log4j.Logger;

import static com.sun.tools.javac.jvm.ByteCodes.ret;

public class ProductManagerImpl implements ProductManager{
    //We call the log4j properties file
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    //We implement the façade using a Singleton pattern
    private static ProductManager instance;

    protected List<Producto> productos;
    protected LinkedList<Pedido> pedidos;
    //Initialize the hashmap(key: string; value: Usuario) of users
    protected HashMap<String, Usuario> usuarios;

    private ProductManagerImpl(){
        this.productos = new ArrayList<>();
        this.pedidos = new LinkedList<>();
        this.usuarios = new HashMap<>();
    }

    public static ProductManager getInstance(){
        if(instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public List<Producto> getAllProductsSortedByPrice(){
        //We create a copy of the list
        log.info("List before changes: " + this.productos);
        List<Producto> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                //Ascending order
                return (int)(o1.getPrice()-o2.getPrice());
            }
        });
        log.info("List after changes: " + ret);
        return ret;
    }

    public List<Producto> getAllProductsSortedByNumberOfSales(){
        //We create a copy of the list
        log.info("List before changes: " + this.productos);
        List<Producto> ret = new ArrayList<>();
        ret.addAll(this.productos);

        //We have to tell to the sort method, which criteria we want to apply
        Collections.sort(ret, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                //Descending order
                return (-1)*(o1.getSales()-o2.getSales());
            }
        });
        log.info("List after changes: " + ret);
        return ret;
    }

    public LinkedList<Pedido> getAllOrdersOfAUser(String user) throws UserNotFoundException{
        LinkedList<Pedido> pedidos = null;

        //We want to find the given user
        Usuario theUser = this.usuarios.get(user);

        if(theUser!=null){
            log.info("User: " + theUser);
            pedidos = theUser.pedidos();
        }
        else {
            log.error("User not found: " + theUser);
            throw new UserNotFoundException();
        }

        return pedidos;

    }

    public void placeAnOrder(String user, Pedido p){
        //We want to know if the user exists or not
        Usuario theUser = this.usuarios.get(user);
        log.info("List before add new order: " + ret);

        if(theUser!=null){
             // throw ///
        }
        else{
            p.setUser(theUser);
            this.pedidos.add(p);
        }

        log.info("List after add new order: " + ret);

        /*LinkedList<Pedido> pedidos = null;
        Usuario theUser = this.usuarios.get(user);

        if(theUser!=null){
            pedidos = theUser.pedidos();
            pedidos.add(p);
        }
        else{
            this.usuarios.put(user, new Usuario(user, this.pedidos));
            pedidos.add(p);
        }*/

    }
    public Pedido serveAnOrder(){
        Pedido p = this.pedidos.pop();

        process(p);

        Usuario usuario = p.getUser();
        usuario.addOrder(p);

        return p;

    }

    private void process(Pedido p) {
        List<Pedido.LProducto> l = p.getProducts();
        Producto producto;
        int q;

        for (Pedido.LProducto lp: l) {
       //     producto = this.getProducto(lp.producto);
        //    producto.addSales(lp.q);
        }
    }

}
