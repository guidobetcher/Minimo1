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
import java.util.HashMap;
import java.util.List;

@Api(value="/orders", description = "Endpoint to Order Service")
@Path("/orders")
public class OrdersService {

    final static Logger log = Logger.getLogger(OrdersService.class.getName());

    private GameManager gm;

    public OrdersService() {
        this.gm = GameManagerImpl.getInstance();
        if(gm.getNumUsuarios() == 0){
            Objeto producto1 = new Objeto("Manzana",1.5);
            Objeto producto2 = new Objeto("Pastel",10);
            Objeto producto3 = new Objeto("Cerveza",2.5);
            Objeto producto4 = new Objeto("Calamares",5);
            Usuario user = new Usuario("Pepe", "Bichuela");
            String id = user.getId();
            //user.addObjeto(producto1);
            gm.addUser(new Usuario("Pepe", "Bichuela"));

            gm.addObject(gm.getUsuarios().get(id), producto1);
            gm.addObject(gm.getUsuarios().get(id), producto2);
            gm.addObject(gm.getUsuarios().get(id), producto3);
            gm.addObject(gm.getUsuarios().get(id), producto4);
        }
    }

    @GET
    @ApiOperation(value = "get all users in the list", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer = "List of Users")
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        HashMap<String, Usuario> users  = this.gm.getUsuarios();

        GenericEntity<HashMap<String, Usuario>> entity = new GenericEntity<HashMap<String, Usuario>>(users){};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all objects of a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer = "List of Objects"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects(@PathParam("user") String id) {
        List<Objeto> objetos;
        try {
            objetos = this.gm.getUsuarioObjetos(this.gm.getUsuarios().get(id));
            log.info("Objetos: " +objetos);
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetos){};
            return Response.status(201).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }





    @POST
    @ApiOperation(value = "place an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/inventaObjeto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeAnOrder(Pedido p) throws ProductNotFoundException {

        String userName = p.getUser().getUsername();

        try {
            this.gm.placeAnOrder(userName, p);
            return Response.status(201).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "serve an Order", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer = "Pedido")
    })
    @Path("/serveanorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response serveAnOrder(){
        Pedido pedido = this.gm.serveAnOrder();

        return Response.status(201).entity(pedido).build();
    }

    @POST
    @ApiOperation(value = "add Product", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Producto p){
        gm.addProducto(p);

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "add User", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/adduser/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(@PathParam("user") String u){
        gm.addUser(u);

        return Response.status(201).build();
    }

}
