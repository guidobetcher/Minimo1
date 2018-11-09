package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value="/orders", description = "Endpoint to Order Service")
@Path("/orders")
public class OrdersService {

    private ProductManager pm;

    List<Pedido.LProducto> lp1, lp2, lp3;

    final static Logger log = Logger.getLogger(OrdersService.class.getName());

    public OrdersService() throws UserNotFoundException {
        this.pm = ProductManagerImpl.getInstance();
        if(pm.size()==0){
            lp1 = new ArrayList<>();
            lp2 = new ArrayList<>();
            lp3 = new ArrayList<>();
            Producto producto1 = new Producto("Manzana",1.5);
            Producto producto2 = new Producto("Pastel",10);
            Producto producto3 = new Producto("Cerveza",2.5);
            Producto producto4 = new Producto("Calamares",5);
            pm.addProducto(producto1);
            pm.addProducto(producto2);
            pm.addProducto(producto3);
            pm.addProducto(producto4);
            pm.addUser("Pepe");
            pm.addUser("Juan");
            pm.addUser("Carla");
            pm.addUser("Paula");
            Pedido.LProducto l1 = new Pedido.LProducto();
            l1.producto = "Manzana";
            l1.q = 3;
            Pedido.LProducto l2 = new Pedido.LProducto();
            l2.producto = "Pastel";
            l2.q = 5;
            lp1.add(l1);
            lp1.add(l2);
            Pedido pedido1 = new Pedido(lp1);
            pm.placeAnOrder("Pepe", pedido1);
        }
    }

    @GET
    @ApiOperation(value = "get all products in the list", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer = "List of Products")
    })
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        List<Producto> productos  = this.pm.allProducts();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos){};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all orders of a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer = "List of Orders"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@PathParam("user") String user) {
        List<Pedido> pedidos;
        try {
            pedidos = this.pm.getAllOrdersOfAUser(user);
            GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(pedidos){};
            return Response.status(201).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get all products sorted by price", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer = "List of Products")
    })
    @Path("/sortedbyprice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSortedByPrice() {
        List<Producto> productos  = this.pm.getAllProductsSortedByPrice();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos){};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all products sorted by number of sales", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer = "List of Products")
    })
    @Path("/sortedbysales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        List<Producto> productos  = this.pm.getAllProductsSortedByNumberOfSales();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos){};
        return Response.status(201).entity(entity).build();
    }

    @DELETE
    @ApiOperation(value = "serve an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer = "Pedido")
    })
    @Path("/serveanorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response serveAnOrder(){
        Pedido pedido = this.pm.serveAnOrder();

        return Response.status(201).entity(pedido).build();
    }

}
